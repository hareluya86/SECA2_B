/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import Component.Entity.Search.EntitySearch;
import Component.Entity.Search.EntitySearchFactory;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author KH
 */

@ManagedBean(name="bootstrap")
@URLMappings(mappings={
    @URLMapping(id="component", pattern="/#{bootstrap.component}",viewId="/faces/index.xhtml"),
})
public class BootstrapDemo extends Bootstrap {
    
    private String component;
    private String template;
    private String program;
    
    private Map<String,Object> elements;
    private EntitySearch entitySearch;
    
    public BootstrapDemo(){
        elements = new HashMap<String,Object>();
        elements.put("header", "this is the header from the map object");
        
        EntitySearchFactory esf = EntitySearchFactory.getEntitySearchFactory();
        entitySearch = esf.getEntitySearch("File");
    }
    /**
     * Decides which component to load
     * <p>
     * This method will return the directory of the current component.
     * @return //Must return something in order for the rest of the application to work?
     */
    @URLAction(mappingId="component", onPostback=false)
    public String loadComponent2(){
        //1. Get request parameter "component"
        
        //2. Search database for which xhtml file to load
        
        //3. Return xhtml directory
        ComponentFactory cf = ComponentFactory.getComponentFactory();
        Component c;
        
        if(component == null || component.isEmpty()){
            c = cf.getComponent();
        }else{
            c = cf.getComponent(component);
        }
        elements.put("component", c);
        return c.getCOMPONENT_DIRECTORY();
        //return "/components/entity/layout.xhtml";
    }
    
    public String loadProgram(){
        return "/components/"+component+"/"+program+".xhtml";
    }
    
    public String getComponent(){
        return this.component;
    }
    
    public void setComponent(String component){
        this.component = component;
    }
    
    public String loadTemplate(){
        //1. Get request parameter "user"
        
        //2. Search database for user settings
        
        //3. Return the directory of the template layout.xhtml file
        return "/templates/mytemplate/layout.xhtml";
    }
    
    public String getTemplate(){
        return this.template;
    }
    
    public void setTemplate(String template){
        this.template = template;
    }

    public Map<String, Object> getElements() {
        return elements;
    }

    public void setElements(Map<String, Object> elements) {
        this.elements = elements;
    }

    public EntitySearch getEntitySearch() {
        return entitySearch;
    }

    public void setEntitySearch(EntitySearch entitySearch) {
        this.entitySearch = entitySearch;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    
    
}
