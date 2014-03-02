/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import EDS.Data.EnterpriseObject;
import java.util.Collection;
import java.util.Map;

/**
 * A Component is a broad overview of a set of functionalities. For example,
 * the Entity component contains a set of functionalities to manipulate entities;
 * the Authorization component contains a set of functionalities to control 
 * access by users, the User component contains a set of functionalities to 
 * control user accounts.
 * <p>
 * @author KH
 */
public abstract class Component implements EnterpriseObject {
    
    protected long COMPONENT_ID;
    protected String COMPONENT_NAME;
    protected String COMPONENT_DIRECTORY;
    protected Map<String,Object> COMPONENT_PARAM;
    protected Collection<Program> COMPONENT_PROGRAMS;

    @Override
    public String tableName(){
        return "COMPONENT";
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

    public Map<String, Object> getCOMPONENT_PARAM() {
        return COMPONENT_PARAM;
    }

    public void setCOMPONENT_PARAM(Map<String, Object> COMPONENT_PARAM) {
        this.COMPONENT_PARAM = COMPONENT_PARAM;
    }

    public Collection<Program> getCOMPONENT_PROGRAMS() {
        return COMPONENT_PROGRAMS;
    }

    public void setCOMPONENT_PROGRAMS(Collection<Program> COMPONENT_PROGRAMS) {
        this.COMPONENT_PROGRAMS = COMPONENT_PROGRAMS;
    }
    
    
}
