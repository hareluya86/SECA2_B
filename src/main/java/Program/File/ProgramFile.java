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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author KH
 */
@Named("ProgramFile")
@SessionScoped
public class ProgramFile extends Program {
    
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
    
    /**
     * ============== New UI action methods/listeners ==========================
     */
    public void manageEntity(){
        subprogram = subprogram.concat(".xhtml");
        newEntityManage.setEntity(selectedEU);
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

    
 }
