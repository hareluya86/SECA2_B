/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search.Demo;

import Component.Entity.Search.AbstractEntitySearch;
import EDS.BusinessUnit.EnterpriseUnit;
import EDS.BusinessUnit.EnterpriseUnit_;
import EDS.Data.EnterpriseKey;
import SECA2.File.FileEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 * @param <T>
 */
public class EntitySearchDemo<T extends EnterpriseUnit> extends AbstractEntitySearch {

    private int resultSize = 10;
    
    @Override
    public void search() {
        super.results = new ArrayList<>();
        for(int i=0;i<resultSize;i++){
            FileEntity file = new FileEntity();
            file.randInit();
            file.setFILENAME("File "+i);
            super.results.add(file);
        }
        System.out.println("Result list: "+super.results.size());
        super.result = "Found";
    }

    @Override
    public Collection<T> getResults() {
        return super.results;
    }

    @Override
    public EnterpriseUnit_ getMetaClass() {
        return super.entityUnitMetaClass;
    }

    @Override
    public void setMetaClass(Class EnterpriseUnit_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getResult() {
        return super.result;
    }

    @Override
    public EnterpriseKey enterpriseKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void randInit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String tableName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    public long getCOMPONENT_ID() {
        return COMPONENT_ID;
    }

    public void setCOMPONENT_ID(long COMPONENT_ID) {
        this.COMPONENT_ID = COMPONENT_ID;
    }

    public String getCOMPONENT_NAME() {
        return COMPONENT_NAME;
    }

    public void setCOMPONENT_NAME(String COMPONENT_NAME) {
        this.COMPONENT_NAME = COMPONENT_NAME;
    }

    public String getCOMPONENT_DIRECTORY() {
        return COMPONENT_DIRECTORY;
    }

    public void setCOMPONENT_DIRECTORY(String COMPONENT_DIRECTORY) {
        this.COMPONENT_DIRECTORY = COMPONENT_DIRECTORY;
    }

    public String getCOMPONENT_XHTML() {
        return COMPONENT_XHTML;
    }

    public void setCOMPONENT_XHTML(String COMPONENT_XHTML) {
        this.COMPONENT_XHTML = COMPONENT_XHTML;
    }

    public Map<String, Object> getCOMPONENT_PARAM() {
        return COMPONENT_PARAM;
    }

    public void setCOMPONENT_PARAM(Map<String, Object> COMPONENT_PARAM) {
        this.COMPONENT_PARAM = COMPONENT_PARAM;
    }
    
    
    
}
