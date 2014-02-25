/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Template;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author KH
 */
@ManagedBean(name="template")
public class Template {
    
    private String template = "templates/mytemplate/layout.xhtml";
    
    public Template(){
        
    }

    public String getTemplate() {
        return template;
        //return "templates/mytemplate/layout.xhtml";
    }

    public void setTemplate(String template) {
        this.template = template;
    }
    
    
}
