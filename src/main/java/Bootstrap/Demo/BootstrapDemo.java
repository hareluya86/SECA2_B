/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.Bootstrap;
import Bootstrap.Program;
import Bootstrap.ProgramFactory;
import Component.Entity.Search.EntitySearch;
import Template.Template;
import Template.TemplateFactory;
import View.ViewPage;
import View.ViewPageFactory;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author KH
 */


@URLMappings(mappings={
    @URLMapping(id="home", pattern="/",viewId="/faces/index.xhtml"),
    @URLMapping(id="program", pattern="/#{bootstrap.program}",viewId="/faces/index.xhtml"),
})
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
    @URLActions(actions={
        @URLAction(mappingId="home", onPostback=false),
        @URLAction(mappingId="program", onPostback=false)
    })
    public void loadProgram(){
        ProgramFactory cf = ProgramFactory.getProgramFactory();
        Program p;
        
        if(program == null || program.isEmpty()){
            p = cf.getProgram(); //get the default program
        }else{
            p = cf.getProgram(program);
        }
        elements.put("program", p);
        //return c.getCOMPONENT_DIRECTORY();
        //return "/components/entity/layout.xhtml";
        
        ViewPageFactory vpf = ViewPageFactory.getViewPageFactory();
        ViewPage vp;
        
        if(program == null || program.isEmpty()){
            vp = vpf.getViewPage(); //get the default program
        }else{
            vp = vpf.getViewPage(program);
        }
        elements.put("viewpage", vp);
    }
    
    @URLActions(actions={
        @URLAction(mappingId="home", onPostback=false),
        @URLAction(mappingId="program", onPostback=false)
    })
    public void loadTemplate(){
        TemplateFactory tf = TemplateFactory.getTemplateFactory();
        Template t;
        
        //Decide which template to load base on User component
        if(template == null || template.isEmpty()){
            t = tf.getTemplate();
        }else{
            t = tf.getTemplate(template);
        }
        elements.put("template", t);
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
    
    public String getModule(){
        return this.module;
    }
    
    public void setModule(String module){
        this.module = module;
    }
}
