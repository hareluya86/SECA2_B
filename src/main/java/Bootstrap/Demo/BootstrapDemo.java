/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.Bootstrap;
import Program.User.FormUserLogin;
import Template.Template;
import Template.TemplateFactory;
import View.ViewPage;
import View.ViewPageFactory;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Just a dispatcher
 * @author KH
 */


@URLMappings(mappings={
    @URLMapping(id="home", pattern="/",viewId="/program/index.xhtml"),
    @URLMapping(id="program", pattern="/program/#{bootstrap.program}/",viewId="/program/index.xhtml")
})
@URLBeanName("bootstrap")
@Named("bootstrap")
@SessionScoped
public class BootstrapDemo extends Bootstrap implements Serializable {
    
    private String module;
    private String template;
    private String program;
    
    private Map<String,Object> elements;
    
    @Inject private FormUserLogin programUserLogin;
    
    @PostConstruct
    public void init(){
        elements = new HashMap<String,Object>();
        elements.put("header", "this is the header from the map object");
        
        System.out.println("Bootstrap is called from @PostConstruct! "+module);
    }
    
    
    /**
     * Decides which module to load
     * <p>
     */
    @URLActions(actions={
        @URLAction(mappingId="home", onPostback=false),
        @URLAction(mappingId="program", onPostback=false)
    })
    public void loadView(){
        //Only load views and let views load their own program dependencies!
        ViewPageFactory vpf = ViewPageFactory.getViewPageFactory();
        ViewPage vp;
        
        if(program == null || program.isEmpty()){
            vp = vpf.getViewPage(); //get the default program
        }else{
            vp = vpf.getViewPage(program);
        }
        elements.put("viewpage", vp);
        
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        //HttpSession sess = (HttpSession)ec.getSession(true);
        //sess.setAttribute("viewpage", vp.getRoot());
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
        
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        //HttpSession sess = (HttpSession)ec.getSession(true);
        //sess.setAttribute("template", t.getTEMPLATE_XHTML());
    }
    
    @URLActions(actions={
        @URLAction(mappingId="home", onPostback=true),
        @URLAction(mappingId="program", onPostback=true)
    })
    public void checkLogin(){
        elements.put("renderLogin", !programUserLogin.checkSessionActive());
    }
    
    public void login(){
        
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

    public FormUserLogin getProgramUserLogin() {
        return programUserLogin;
    }

    public void setProgramUserLogin(FormUserLogin programUserLogin) {
        this.programUserLogin = programUserLogin;
    }
    
    
}
