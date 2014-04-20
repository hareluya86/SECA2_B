/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.User.UserAccountLockedException;
import Component.User.UserService;
import Entity.User.UserEntity;
import Program.Util.FacesMessenger;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.exception.JDBCConnectionException;
import org.joda.time.DateTime;

/**
 *
 * @author KH
 */

public class FormUserLogin implements Serializable {
    
    private String username;
    private String password;
    
    private String sSessionId; //secure server side sessionid, do not allow access by client
    //private String cSessionId; //passed to client
    
    private String loginboxTitle;
    
    private DateTime sessionStarttime; //time that the login session starts
    private String messageBoxId = "login-form";
    
    @EJB private UserService userService;
    
    @PostConstruct
    public void init(){
        username = "";
    }
    
    public void login(){
        //Check if username and password are present
        if(username == null || username.isEmpty()){
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR,
                    "Please enter username", null);
            return;
        }
        if(password == null || password.isEmpty()){
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR,
                    "Please enter password", null);
            return;
        }
        UserEntity user = null;
        try {
            //use UserService to login
            user = userService.login(username, password);
        } catch (UserAccountLockedException ex) {
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR,
                    "Oops...Your account has been locked. Please contact administrator to unlock it.", null);
            return;
        } catch(EJBException ejbex){
            String message = ejbex.getCause().getMessage();
            if(ejbex.getCause() instanceof JDBCConnectionException){
                message = "There was a problem connecting to the database. Please try again later.";
            }
            
            FacesMessenger.setFacesMessage(messageBoxId,FacesMessage.SEVERITY_ERROR,message,null);
            return;
        }
        
        if(user == null){ 
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR,
                    "Wrong credentials. Are you sure you entered the correct credentials?", 
                    "Alternatively, would you like to created a new account? ");
            return;
        }
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        HttpServletResponse resp = (HttpServletResponse) ec.getResponse();
        
        HttpSession session = req.getSession(true);
        session.setAttribute("user", true);
        sSessionId = session.getId();
        sessionStarttime = new DateTime();
        System.out.println("Session "+sSessionId+" started at "+sessionStarttime);
        password = "";
        username = "";
    }
    
    public boolean checkSessionActive(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getId() == null){
            sSessionId = "";
            sessionStarttime = null;
            return false;
        }else{
            if(session.getId().equals(sSessionId)){ //session still active
                session.setAttribute("user", true);
                return true;
            }else{ //session no longer active
                sSessionId = "";
                sessionStarttime = null;
                return false;
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

    public DateTime getSessionStarttime() {
        return sessionStarttime;
    }

    public void setSessionStarttime(DateTime sessionStarttime) {
        this.sessionStarttime = sessionStarttime;
    }

    public String getLoginboxTitle() {
        return loginboxTitle;
    }

    public void setLoginboxTitle(String loginboxTitle) {
        this.loginboxTitle = loginboxTitle;
    }
    
    
}
