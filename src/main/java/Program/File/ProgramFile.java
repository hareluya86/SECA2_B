/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import Bootstrap.ComponentOperation;
import Bootstrap.Program;
import Component.Entity.Manage.EntityManage;
import Component.Entity.Manage.EntityManageFactory;
import Component.Entity.Search.EntitySearch;
import Component.Entity.Search.EntitySearchFactory;
import EDS.BusinessUnit.EnterpriseUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author KH
 */
@Named("ProgramFile")
@SessionScoped
public class ProgramFile extends Program implements Serializable {
    
    @Override
    public String className() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> exportAsMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String exportAsString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List exportAsList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostConstruct
    public void init() {
        //Instatiante all objects
        this.setPROGRAM_PARAM(new HashMap<String,Object>());
        this.setCOMP_OPERATIONS(new HashMap<String,ComponentOperation>());
        //this.setSubprogram("manage.xhtml");
        
        this.setPROGRAM_XHTML("/programs/file/layout.xhtml");
        //this.getPROGRAM_PARAM().put("subprogram", "");
        this.setSearchTableID(":search-result-list");
        //this.getPROGRAM_PARAM().put("test-el-comment","This is a test of whether EL expressions are commented out by <!--");
        
        //Create all required Component factories
        EntitySearchFactory esf = EntitySearchFactory.getEntitySearchFactory();
        EntityManageFactory emf = EntityManageFactory.getEntityManageFactory();
        
        //For the new framework
        newEntitySearch = esf.getEntitySearch("File");
        newEntityManage = emf.getEntityManage("File");
        
    }
    
    /**
     * New UI components
     */
    private EntitySearch newEntitySearch;
    private EntityManage newEntityManage;
    private String subprogram;
    private String subprogramTitle;
    private EnterpriseUnit selectedEU;
    private String searchTableID;
    private Part file;
    
    /**
     * ============== New UI action methods/listeners ==========================
     */
    public void manageFileAndSequences(){
        subprogram = subprogram.concat(".xhtml");
        newEntityManage.setEntity(selectedEU);
    }
    
    public void generateSequences(){
        subprogram = subprogram.concat(".xhtml");
        
    }
    
    public void uploadFile(FileUploadEvent event){
        UploadedFile uploadedFile = event.getFile();
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(uploadedFile.getInputstream()));
            String lineSequence = new String();
            int lineNum = 0;
            while((lineSequence=bReader.readLine())!=null){
                if(lineNum++%1000000 == 0)
                    System.out.println(lineSequence);
            }
        } catch (IOException ex) {
            //Logger.getLogger(ProgramFile.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void uploadFile(){
        try {
            //String fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
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
    
    public void updateEntity(){
        
        //Update entity
        newEntityManage.update(); //dummy
        
        //Find the EnterpriseUnit instance in newEntitySearch and update it
        Iterator<EnterpriseUnit> i = newEntitySearch.getResults().iterator();
        while(i.hasNext()){
            EnterpriseUnit eu = i.next();
            if(eu.getOBJECTID() == selectedEU.getOBJECTID()){
                eu = selectedEU;
            }
        }
        System.out.println("updateEntity is called!");
    }
    /**
     * New UI component setters and getters
     */
    public EntitySearch getNewEntitySearch() {
        return newEntitySearch;
    }

    public void setNewEntitySearch(EntitySearch newEntitySearch) {
        this.newEntitySearch = newEntitySearch;
    }

    public EntityManage getNewEntityManage() {
        return newEntityManage;
    }

    public void setNewEntityManage(EntityManage newEntityManage) {
        this.newEntityManage = newEntityManage;
    }

    public String getSubprogram() {
        return subprogram;
    }

    public void setSubprogram(String subprogram) {
        this.subprogram = subprogram;
    }

    public EnterpriseUnit getSelectedEU() {
        return selectedEU;
    }

    public void setSelectedEU(EnterpriseUnit selectedEU) {
        this.selectedEU = selectedEU;
    }

    public String getSearchTableID() {
        return searchTableID;
    }

    public void setSearchTableID(String searchTableID) {
        this.searchTableID = searchTableID;
    }

    public String getSubprogramTitle() {
        return subprogramTitle;
    }

    public void setSubprogramTitle(String subprogramTitle) {
        this.subprogramTitle = subprogramTitle;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    
 }
