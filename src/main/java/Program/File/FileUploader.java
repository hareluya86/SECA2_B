/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import Data.HibernateUtil;
import SECA2.File.FileEntity;
import SECA2.File.FileSequence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


/**
 * Utility class that processes file and sequence objects
 * <p>
 * 
 * @author vincent.a.lee
 */
@Named("FileUploader")
@SessionScoped
public class FileUploader implements Serializable {
    
    private final long MAX_RECORD_FLUSH = 10000;
    private final long MAX_RECORD_COMMIT = 100000;
    
    @Inject
    private HibernateUtil hibernateUtil;
    
    private Part partFile;
    private UploadedFile uploadedFile;
    
    private String startUpload;

    public HibernateUtil getHibernateUtil() {
        return hibernateUtil;
    }

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public Part getPartFile() {
        return partFile;
    }

    public void setPartFile(Part partFile) {
        this.partFile = partFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getStartUpload() {
        return startUpload;
    }

    public void setStartUpload(String startUpload) {
        this.startUpload = startUpload;
    }
    
    public void startFileUpload(){
        this.startUpload = "File upload has started";
        System.out.println(this.startUpload);
    }
    
    /**
     * 1) Validate file:
     *      - Make sure each line is of the same length
     *      - Compute checksum
     *      - Compute length (both number of bytes and sequences)
     *      - Compare with existing file and determine if insert button should be a "Insert new file"/"Resume existing file"/"File already uploaded"
     * 2) Create/retrieve file reference from database to be used later for insertion of sequences
     * 3) 
     * @param event 
     */
    public void uploadFile(FileUploadEvent event){
        uploadedFile = event.getFile();
        
        try {
            //1) Validate file
            //- Make sure each line is of the same length
            //- Compute checksum at the same time
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream is = uploadedFile.getInputstream();
            DigestInputStream dis = new DigestInputStream(is, md);
            
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            String line = new String();
            long fileSize = uploadedFile.getSize(); 
            int prevLineSize = 0;
            int lineNum = 0;
            while((line=bReader.readLine())!=null){
                lineNum++;
                if(prevLineSize > 0 && prevLineSize != line.length()){ //last line is empty although gvim shows no last line
                    System.out.println(line);
                    throw new RuntimeException("File "+uploadedFile.getFileName()+" does not contain equal length sequences!");
                }
                prevLineSize = line.length();
            }
            
            byte[] digest = md.digest();
            System.out.println(digest);
            
        } catch (NoSuchAlgorithmException | IOException mdex) {
            //Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(mdex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (RuntimeException rex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,rex.getMessage(),"");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            
        }
    }
    
    public void insertFileAndSequences() {
        
        Session session = hibernateUtil.getSession();

        //Check if file exists
        String filename = uploadedFile.getFileName();
        FileEntity newFile = this.createNewFile(filename);
        
        FileEntity checkFile = this.checkFileExists(session, newFile);
        if(checkFile != null){ //file exists
            newFile = checkFile;
        }else{//file doesn't exist
            session.getTransaction().begin();
            session.save(newFile);
            //session.flush();
            session.getTransaction().commit();
        }
        
        //resume from the sequence number where the upload has stopped
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(uploadedFile.getInputstream()));
            String lineSequence = new String();
            long lineNum = newFile.getSEQUENCE_SIZE();
            
            //move bufferedreader to the last line read
            
            session.getTransaction().begin();
            
            
            while((lineSequence=bReader.readLine())!=null){
                FileSequence nextSequence = this.addSequence(newFile, lineSequence);
                session.save(nextSequence);
                if(lineNum++%MAX_RECORD_FLUSH == 0){
                    System.out.println("Flush at: "+lineSequence);
                    session.flush();
                }
                if(lineNum++%MAX_RECORD_COMMIT == 0){
                    System.out.println("Commit at: "+lineSequence);
                    session.getTransaction().commit();
                    session.getTransaction().begin();
                }
            }
            session.getTransaction().commit();
        } catch (IOException ex) {
            //Logger.getLogger(ProgramFile.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
    }
    
    public FileEntity createNewFile(String filename){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFILENAME(filename);
        fileEntity.setSEQUENCE_SIZE(0);
        fileEntity.setBYTE_SIZE(0);
        fileEntity.setSTATUS(FileEntity.FILE_STATUS.INCOMPLETE);
        
        return fileEntity;
    };
    
    public FileSequence addSequence(FileEntity file, FileSequence sequence){
        
        sequence.setFILE(file);
        file.getSequences().add(sequence);
        file.setSEQUENCE_SIZE(file.getSEQUENCE_SIZE()+1);
        return sequence;
    };
    
    public FileSequence addSequence(FileEntity file, String sequenceContent){
        FileSequence sequence = new FileSequence();
        sequence.setSEQUENCE_CONTENT(sequenceContent);
        
        return addSequence(file,sequence);
    }
    
    public FileEntity checkFileExists(Session session, FileEntity file){
        return (FileEntity) session.get(file.getClass(),file.getFILENAME());
    }
    
    public void clearFiles() throws IOException{
        this.partFile.delete();
        this.uploadedFile = null;
    }
    
}
