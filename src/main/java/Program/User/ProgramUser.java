/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

/**
 *
 * @author KH
 */
@Named("ProgramUser")
//@RequestScoped
@SessionScoped
public class ProgramUser implements Serializable {
    
    private String username;
    private String password;
    
    private String sSessionId; //secure server side sessionid, do not allow access by client
    private String cSessionId; //passed to client
    
    private DateTime sessionStarttime; //time that the login session starts
    
    @PostConstruct
    public void init(){
        username = "";
    }
    
    public void login(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        
        HttpSession session = req.getSession(true);
        session.setAttribute("user", true);
        sSessionId = cSessionId = session.getId();
        sessionStarttime = new DateTime();
        password = "";
    }
    
    public void checkSessionActive(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getId() == null){
            cSessionId = "";
            sSessionId = "";
            sessionStarttime = null;
        }else{
            if(cSessionId.equals(sSessionId)){ //session still active
                
            }else{ //session no longer active
                cSessionId = "";
                sSessionId = "";
                sessionStarttime = null;
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcSessionId() {
        return cSessionId;
    }

    public void setcSessionId(String cSessionId) {
        this.cSessionId = cSessionId;
    }

    public DateTime getSessionStarttime() {
        return sessionStarttime;
    }

    public void setSessionStarttime(DateTime sessionStarttime) {
        this.sessionStarttime = sessionStarttime;
    }
    
    
}
