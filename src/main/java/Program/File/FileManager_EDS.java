/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import SECA2.File.FileEntity;
import SECA2.File.FileSequence;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;


/**
 * Utility class that processes file and sequence objects
 * <p>
 * 
 * @author vincent.a.lee
 */
//@Named("FileManager")
@SessionScoped
public class FileManager_EDS implements Serializable {
    /*
    private Part partFile;
    private UploadedFile uploadedFile;
    @Inject @DAOFactoryType("HIBERNATE")
    private DAOFactory daoFactory;
    
    public Part getFile() {
        return partFile;
    }

    public void setFile(Part file) {
        this.partFile = file;
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

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public void uploadFile(){
        try {
            //String fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(partFile.getInputStream()));
            
            String lineSequence = new String();
            int lineNum = 0;
            while((lineSequence=bReader.readLine())!=null){
                if(lineNum++%1000000 == 0)
                    System.out.println(lineSequence);
            }
          } catch (IOException e) {
            // Error handling
          }
    }
    
    public void uploadFile(FileUploadEvent event){
        uploadedFile = event.getFile();
    }
    
    public void insertFileAndSequences() {
        //UploadedFile uploadedFile = event.getFile();
        String filename = uploadedFile.getFileName();
        FileEntity newFile = this.createFile(filename);
        
        
        
        try {
            DAO dao = daoFactory.getDAO();
            //Create file and persist first
            dao.start();
            //dao.insertEntity(newFile);
            dao.commit();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(uploadedFile.getInputstream()));
            String lineSequence = new String();
            int lineNum = 0;
            dao.start();
            while((lineSequence=bReader.readLine())!=null){
                FileSequence nextSequence = this.addSequence(newFile, lineSequence);
                //dao.insertEntity(nextSequence);
                if(lineNum++%100000 == 0){
                    System.out.println("Commit at: "+lineSequence);
                    dao.commit();
                    dao.start();
                }
            }
            dao.commit();
        } catch (IOException ex) {
            //Logger.getLogger(ProgramFile.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (DBConnectionException dbex){
            //do recovery action
            
            FacesMessage msg = new FacesMessage(dbex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    /**
     * Search for a particular sequence
     * <p>
     * Returns sequence that contains matches searchString case-sensitively.
     * 
     * @param searchString
     * @return FileSequence
     */
    public FileSequence searchSequence(String searchString){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Search for a list of sequences
     * <p>
     * Returns a list of sequences that contains the substring searchString 
     * case-sensitively.
     * 
     * @param searchString
     * @return List
     */
    public List<FileSequence> searchSequences(String searchString){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    };
    
    public FileEntity createFile(String filename){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFILENAME(filename);
        fileEntity.setNUM_OF_SEQUENCE(0);
        fileEntity.setFILE_SIZE_BYTE(0);
        fileEntity.setUPLOAD_STATUS(FileEntity.FILE_STATUS.INCOMPLETE);
        
        return fileEntity;
    };
    
    public FileSequence addSequence(FileEntity file, FileSequence sequence){
        
        sequence.setFILE(file);
        file.getSequences().add(sequence);
        file.setNUM_OF_SEQUENCE(file.getNUM_OF_SEQUENCE()+1);
        return sequence;
    };
    
    public FileSequence addSequence(FileEntity file, String sequenceContent){
        FileSequence sequence = new FileSequence();
        sequence.setSEQUENCE_CONTENT(sequenceContent);
        
        return addSequence(file,sequence);
    }
    
    /**
     * ================ Database operations ======= needed? ===================
     */
    /**
     * 
     * @param file
     * @param dao
     * @return
     * @throws DBConnectionException 
     */
    /*
    public FileEntity persistFile(FileEntity file, DAO dao) throws DBConnectionException{
        dao.init();
        //dao.insertEntity(file);
        dao.commit();
        dao.close();
        
        return file;
    }
    
    public FileSequence persistSequence(FileSequence sequence, DAO dao) throws DBConnectionException {
        dao.init();
        //dao.insertEntity(sequence);
        dao.commit();
        dao.close();
        
        return sequence;
    }
    */
}
