/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 */
public abstract class Component /*implements EnterpriseEntity*/{
    
    protected long COMPONENT_ID;
    protected String COMPONENT_NAME;
    protected String COMPONENT_DIRECTORY;
    protected String COMPONENT_XHTML;
    protected Map<String,Object> COMPONENT_PARAM;
    
    /**
     * == Getters and setters for properties exclusive to this abstract class==
     * @return 
     */
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
    
    /**
     * == Abstract getters and setters to be implemented in subclasses =========
     */
    //@Override
    public abstract void randInit();

    //@Override
    public abstract List exportAsList();

    //@Override
    public abstract String exportAsString();

    //@Override
    public abstract Map<String, Object> exportAsMap();

    //@Override
    public abstract String className();

    //@Override
    public abstract String tableName();

    
}
