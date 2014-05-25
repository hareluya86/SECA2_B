/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vincent.a.lee
 */
public class CheckUserLogin {
    
    private String sSessionId;
    private String previousURI;
    private final String renderLoginBoxId = "login-form:loginbox-container";
    
    public void checkSessionActive(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        
        HttpSession session = req.getSession(false);
        if(session == null){
            
        }else{
            if(sSessionId != null && sSessionId.equals(session.getId())){
                //hide login block
                session.setAttribute("user", 1);
            }else{
                //pop up login block
                session.setAttribute("user", 0);
                //store this current requestURI for redirection after login
                String originalURI = (String) req.getAttribute("javax.servlet.forward.request_uri");
                if(originalURI != null || !originalURI.isEmpty()){
                    this.previousURI = originalURI;
                }
            }
            fc.getPartialViewContext().getRenderIds().add("renderLoginBoxId");
        }
    }
}
