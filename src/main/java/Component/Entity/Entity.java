/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity;

import Bootstrap.Component;
import Bootstrap.Program;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.inject.Named;

/**
 * This is just an implementation of the Component class. It is 
 * @author KH
 */
@Named("entity")
@URLMapping(id="entity", pattern="/entity",viewId="/faces/index.xhtml")
public class Entity extends Component {

    public Entity(){
        this.COMPONENT_DIRECTORY = "/components/entity/layout.xhtml";
        System.out.println("Entity is called!");
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
    
}
