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
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author KH
 */


//@URLMappings(mappings={
    @URLMapping(id="module", pattern="/#{bootstrap.module}",viewId="/faces/index.xhtml")//,
//})
@Named("bootstrap")
@SessionScoped
public class BootstrapDemo extends Bootstrap implements Serializable {
    
    private String module;
    private String template;
    private String program;
    
    private Map<String,Object> elements;
    private EntitySearch entitySearch;
    
    public BootstrapDemo(){
        elements = new HashMap<String,Object>();
        elements.put("header", "this is the header from the map object");
        
        System.out.println("Bootstrap is called from constructor! "+module);
        
    }
    /**
     * Decides which module to load
     * <p>
 This method will return the directory of the current module.
     * @return //Must return something in order for the rest of the application to work?
     * if i return String then it is not called when searching
     * if i return void then it is called when searching
     */
    @URLAction(mappingId="module", onPostback=false)
    public void loadModule(){
        //1. Get request parameter "module"
        
        //2. Search database for which xhtml file to load
        
        //3. Return xhtml directory
        ComponentFactory cf = ComponentFactory.getComponentFactory();
        Component c;
        
        if(module == null || module.isEmpty()){
            c = cf.getComponent();
        }else{
            c = cf.getComponent(module);
        }
        elements.put("component", c);
        //return c.getCOMPONENT_DIRECTORY();
        //return "/components/entity/layout.xhtml";
        EntitySearchFactory esf = EntitySearchFactory.getEntitySearchFactory();
        entitySearch = esf.getEntitySearch("File");
        System.out.println("Bootstrap is called from load! "+module);
        
    }
    
    public String loadProgram(){
        return "/components/"+module+"/"+program+".xhtml";
    }
    
    public String getComponent(){
        return this.module;
    }
    
    public void setComponent(String component){
        this.module = component;
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
