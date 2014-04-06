/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.User;

import Data.HibernateUtil;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author KH
 */
public class Auth {
    
    private String username;
    private String password;
    
    public void init(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    }
    
    @Inject private HibernateUtil hibernateUtil;
    
    public void login() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        
        try {
            request.login(username, password);
            //User user = userService.find(username, password);
            //externalContext.getSessionMap().put("user", user);
            //externalContext.redirect(originalURL);
        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
            //context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }
}
