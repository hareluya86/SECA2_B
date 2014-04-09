/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import Bootstrap.Program;
import Component.EntityManage.EntityManageDemo;
import Component.Data.HibernateUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Session;

/**
 *
 * @author KH
 */
@Named("ProgramFile")
@SessionScoped
public class ProgramFile extends Program implements Serializable {
    
    //@Override
    public String className() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public Map<String, Object> exportAsMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public String exportAsString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public List exportAsList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostConstruct
    public void init() {
        //Instatiante all objects
        this.setPROGRAM_PARAM(new HashMap<String,Object>());
        //this.setSubprogram("manage.xhtml");
        
        this.setPROGRAM_XHTML("/programs/file/layout.xhtml");
        
        /**
         * Manually injects the same session object into each SessionScoped 
         * subprogram object.
         * 
         * Is this a good method? Let's find out...
         */
    }
    
    //Inject Application components
    //@Inject private EntitySearchDemo entitySearch;
    //@Inject private EntityManageDemo entityManage;
    //@Inject private HibernateUtil hibernateUtil;
    
    //Utility components
    
    
    //Subprograms
    @Inject private ProgramFileUploader fileUploader;
    @Inject private ProgramFileSearch fileSearch;
    
    //UI components
    private String subprogram;
    private String subprogramTitle;
    private Object selectedEU;
    
    
    /**
     * ============== New UI action methods/listeners ==========================
     */
    public void manageFileAndSequences(){
        subprogram = subprogram.concat(".xhtml");
        //newEntityManage.setEntity(selectedEU);
    }
    
    public void generateSequences(){
        subprogram = subprogram.concat(".xhtml");
        
    }
    
    
    /**
     * New UI component setters and getters
     */

    public String getSubprogram() {
        return subprogram;
    }

    public void setSubprogram(String subprogram) {
        this.subprogram = subprogram;
    }

    public Object getSelectedEU() {
        return selectedEU;
    }

    public void setSelectedEU(Object selectedEU) {
        this.selectedEU = selectedEU;
    }

    public String getSubprogramTitle() {
        return subprogramTitle;
    }

    public void setSubprogramTitle(String subprogramTitle) {
        this.subprogramTitle = subprogramTitle;
    }

    public ProgramFileUploader getFileUploader() {
        return fileUploader;
    }

    public void setFileUploader(ProgramFileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    public ProgramFileSearch getFileSearch() {
        return fileSearch;
    }

    public void setFileSearch(ProgramFileSearch fileSearch) {
        this.fileSearch = fileSearch;
    }
    
    
 }
