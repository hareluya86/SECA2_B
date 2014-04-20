/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Template.Demo;

import Template.Template;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 */
public class TemplateDemo extends Template {

    @Override
    public void init() {
        this.setTEMPLATE_XHTML("/templates/mytemplate/layout.xhtml");
        this.TEMPLATE_PARAMS = new HashMap<String,Object>();
        this.TEMPLATE_PARAMS.put("logo", "/templates/mytemplate/logo.png");
        
    }
    
    //@Override
    public String className() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public Map<String, Object> exportAsMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public String exportAsString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public List exportAsList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
