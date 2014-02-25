/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import Template.Template;
import java.util.Map;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author KH
 */
@ManagedBean(name="bootstrap")
public class Bootstrap {
    
    /**
     * Decides which component to load
     * <p>
     * This method will return the directory of the current component.
     * @return 
     */
    public String getComponent(){
        //1. Get request parameter "component"
        
        //2. Search database for which xhtml file to load
        
        //3. Return xhtml directory
        return "component/entity/layout.xhtml";
    }
    
    public String getTemplete(){
        //1. Get request parameter "user"
        
        //2. Search database for user settings
        
        //3. Return the directory of the template layout.xhtml file
        return "templates/mytemplate/layout.xhtml";
    }
}
