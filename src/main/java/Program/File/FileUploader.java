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
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.JDBCConnectionException;
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
    private FileEntity holdingFile;
    
    private String startUpload;
    
    private String insertButtonValue;
    private boolean showInsertButton;
    private boolean disableInsertButton;
    private boolean showRetryButton;
    
    @PostConstruct
    public void init(){
        insertButtonValue = "";
        showInsertButton = false;
        disableInsertButton = true;
    }

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

    public FileEntity getHoldingFile() {
        return holdingFile;
    }

    public void setHoldingFile(FileEntity holdingFile) {
        this.holdingFile = holdingFile;
    }

    public String getInsertButtonValue() {
        return insertButtonValue;
    }

    public void setInsertButtonValue(String insertButtonValue) {
        this.insertButtonValue = insertButtonValue;
    }

    public boolean isDisableInsertButton() {
        return disableInsertButton;
    }

    public void setDisableInsertButton(boolean disableInsertButton) {
        this.disableInsertButton = disableInsertButton;
    }

    public boolean isShowInsertButton() {
        return showInsertButton;
    }

    public void setShowInsertButton(boolean showInsertButton) {
        this.showInsertButton = showInsertButton;
    }

    public boolean isShowRetryButton() {
        return showRetryButton;
    }

    public void setShowRetryButton(boolean showRetryButton) {
        this.showRetryButton = showRetryButton;
    }
    
    public void startFileUpload(){
        this.startUpload = "File upload has started";
        System.out.println(this.startUpload);
    }
    
    /**
     * 1) Validate file:
     *      - Make sure each line is of the same length
     *      - Compute checksum
     *      - Compute size (both number of bytes and sequences)
     *      - Compare with existing file and determine if insert button should be a "Insert new file"/"Resume existing file"/"File already uploaded"
     * 2) Create/retrieve file reference from database to be used later for insertion of sequences
     * 3) 
     * @param event 
     */
    public void uploadFile(FileUploadEvent event){
        /*uploadedFile = event.getFile();
        
        //1) Validate file
        //- Make sure each line is of the same length
        //- Compute MD5 checksum at the same time
        //- Compute byte and sequence size at the same time
        String md5Checksum = "";
        int lineNum = 0;
        long fileSize = uploadedFile.getSize();
        */
        
        try {
            /*MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream is = uploadedFile.getInputstream();
            DigestInputStream dis = new DigestInputStream(is, md);
            
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dis));
            String line = new String();
             
            int prevLineSize = 0;
            while((line=bReader.readLine())!=null){
                lineNum++;
                if(prevLineSize > 0 && prevLineSize != line.length()){ //last line is empty although gvim shows no last line
                    System.out.println(line);
                    throw new InvalidFileException("File "+uploadedFile.getFileName()+" does not contain equal length sequences!");
                }
                prevLineSize = line.length();
            }
            byte[] digest = md.digest();// dis.getMessageDigest().digest();
            for(int i=0; i<digest.length; i++){
                System.out.print(digest[i]);
            }
            md5Checksum = String.format("%032x", new BigInteger(digest));
            System.out.println("MD5 hash: "+md5Checksum);
            
            //initialize temp FileEntity set all variables to proceed with the remaining checks
            holdingFile = this.createNewFile(uploadedFile.getFileName());
            holdingFile.setBYTE_SIZE(fileSize);
            holdingFile.setSEQUENCE_SIZE(lineNum);
            holdingFile.setMD5_HASH(md5Checksum);*/
            FileEntity checkedLengthAndChecksum = checkLengthAndComputeChecksum(event.getFile());
            if(checkedLengthAndChecksum == null)
                throw new InvalidFileException("File "+event.getFile().getFileName()+" does not contain equal length sequences!");
            //Compare with existing file and determine if insert button should be a "Insert new file"/"Resume existing file"/"File already uploaded"
            FileEntity existingFile = this.checkFileExists(hibernateUtil.getSession(), checkedLengthAndChecksum);
            if(existingFile != null){
                System.out.println("Existing file "+existingFile.getFILENAME()+" found");//debug
                switch(existingFile.getSTATUS()){
                    case INCOMPLETE :   setFacesMessage(FacesMessage.SEVERITY_INFO,
                                            "File \""+existingFile.getFILENAME()+"\" has been uploaded before."
                                            ,"");
                                        this.insertButtonValue = "Resume upload for file \""+existingFile.getFILENAME()+"\"";
                                        this.showInsertButton = true;
                                        this.disableInsertButton = false;
                                        break;
                    case COMPLETED  :   setFacesMessage(FacesMessage.SEVERITY_INFO,
                                            "Uploading has already completed for this file \""+existingFile.getFILENAME()+"\""
                                            ,"");
                                        this.showInsertButton = false;
                                        this.disableInsertButton = true;
                                        break;
                    default         :   break;
                }
            } else {
                System.out.println("No existing file found");//debug
                this.insertButtonValue = "Insert new file \""+event.getFile().getFileName()+"\"";
                this.showInsertButton = true;
                this.disableInsertButton = false;
            }
            this.holdingFile = existingFile;
            
        }  catch (InvalidFileException ifex){
            setFacesMessage(FacesMessage.SEVERITY_ERROR,ifex.getMessage(),"");
            this.showInsertButton = false;
        } catch(JDBCConnectionException jdbcex){
            setFacesMessage(FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
            this.showInsertButton = false;
        } catch (Exception ex) {
            setFacesMessage(FacesMessage.SEVERITY_ERROR,ex.getMessage(),"");
            this.showInsertButton = false;
        } 
    }
    /**
     * Returns an initialized FileEntity object if it passes both length and checksum validation.
     * Else returns null.
     * <p>
     * @param uploadedFile
     * @return
     * @throws Exception 
     */
    public FileEntity checkLengthAndComputeChecksum(UploadedFile uploadedFile) throws Exception{
        FileEntity checkedFile;
        String md5Checksum = "";
        int lineNum = 0;
        long fileSize = uploadedFile.getSize();
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream is = uploadedFile.getInputstream();
        DigestInputStream dis = new DigestInputStream(is, md);

        BufferedReader bReader = new BufferedReader(new InputStreamReader(dis));
        String line = new String();

        int prevLineSize = 0;
        while((line=bReader.readLine())!=null){
            lineNum++;
            if(prevLineSize > 0 && prevLineSize != line.length()){ //last line is empty although gvim shows no last line
                System.out.println(line);
                return null;
            }
            prevLineSize = line.length();
        }
        byte[] digest = md.digest();// dis.getMessageDigest().digest();
        for(int i=0; i<digest.length; i++){
            System.out.print(digest[i]);
        }
        md5Checksum = String.format("%032x", new BigInteger(digest));
        System.out.println("MD5 hash: "+md5Checksum);
            
        //initialize temp FileEntity set all variables to proceed with the remaining checks
        checkedFile = this.createNewFile(uploadedFile.getFileName());
        checkedFile.setBYTE_SIZE(fileSize);
        checkedFile.setSEQUENCE_SIZE(lineNum);
        checkedFile.setMD5_HASH(md5Checksum);
        
        return checkedFile;
    }
    
    public void setFacesMessage(FacesMessage.Severity level, String headline, String description){
        FacesMessage msg = new FacesMessage(level,headline,description);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void insertFileAndSequences() {
        System.out.println(holdingFile.getFILENAME());
        
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
    
    /**
     * Based on the following criteria, return an existing FileEntity or null if it doesn't fulfill ALL criteria:
     * <p>
     * <strong>1) Hash value</strong>
     * <ul>
     * <li>If no existing file with given hash value, return null</li>
     * <li>If existing files with given hash value, proceed to the next check</li>
     * </ul>
     * <strong>2) File size</strong>
     * <ul>
     * <li>If no existing files with given file size, return null</li>
     * <li>If existing files with given file size, proceed to the next check</li>
     * </ul>
     * <strong>3) Number of sequences</strong>
     * <ul>
     * <li>If no existing files with given number of sequences, return null</li>
     * <li>If existing files with given file number of sequences, proceed to the next check</li>
     * </ul>
     * <strong>4) Filename</strong>
     * <ul>
     * <li>If no existing files with given filename, return null</li>
     * <li>If existing files with given filename, proceed to the next check</li>
     * </ul>
     * <p>
     * WARNING: This is not a 100% method, there is a chance that a file with different content 
     * but with the same hash, size, number of sequences and name is returned. If multiple files 
     * are found that fulfills all the above conditions as the given file, the first result will
     * be returned.
     * 
     * @param session
     * @param file
     * @return 
     */
    public FileEntity checkFileExists(Session session, FileEntity file){
        List<FileEntity> results;
        //1) Hash value
        String hashValueQuery = "SELECT file "
                                + "FROM FileEntity file "
                                + "WHERE "
                                    + "file.MD5_HASH = '"+file.getMD5_HASH()+"' AND "
                                    + "file.BYTE_SIZE = "+file.getBYTE_SIZE()+" AND "
                                    + "file.SEQUENCE_SIZE = "+file.getSEQUENCE_SIZE()+" AND "
                                    + "file.FILENAME = '"+file.getFILENAME()+ "'";
        Query q = session.createQuery(hashValueQuery);
        results = q.list();
        if(results.size() <= 0 ){
            return null;
        }
        else{
            return results.get(0);
        }
    }
    
    /**
     * Cancels the entire process and clears all temp variables/storage.
     * 
     * @throws IOException 
     */
    public void cancel() throws IOException{
        if(this.partFile!=null) this.partFile.delete();
        this.partFile = null;
        this.uploadedFile = null;
        this.holdingFile = null;
        this.disableInsertButton = true;
        this.showInsertButton = false;
        
        System.gc();
    }
    
    public static String bytesToStringUTFCustom(byte[] bytes) {
        char[] buffer = new char[bytes.length >> 1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            char c = (char) (((bytes[bpos] & 0x00FF) << 8) + (bytes[bpos + 1] & 0x00FF));
            buffer[i] = c;
        }
        return new String(buffer);
    }
}
