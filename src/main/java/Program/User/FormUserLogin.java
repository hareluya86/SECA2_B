/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.User;

import Bootstrap.Demo.CheckInstaller;
import Component.User.UserAccountLockedException;
import Component.User.UserService;
import Entity.User.UserEntity;
import Program.Messenger.FacesMessenger;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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

    private String previousURI;

    private String sSessionId; //secure server side sessionid, do not allow access by client
    //private String cSessionId; //passed to client

    private String loginboxTitle;
    private String loginBlockXHTML;

    private DateTime sessionStarttime; //time that the login session starts
    private final String messageBoxId = "login-form";

    @Inject
    private CheckInstaller checkInstaller;
    @EJB
    private UserService userService;

    @PostConstruct
    public void init() {
        username = "";
        password = "";
        loginBlockXHTML = "/programs/user/login_block.xhtml";

    }

    public void login() throws IOException {
        //Check if application was installed by calling CheeckInstaller everytime someone logs in.
        if (this.checkInstaller.getStatus() != CheckInstaller.INSTALL_STATUS.INSTALLED) {
            return;
        }

        //Check if username and password are present
        if (username == null || username.isEmpty()) {
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR,
                    "Please enter username", null);
            return;
        }
        if (password == null || password.isEmpty()) {
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
        } catch (EJBException ejbex) {
            String message = ejbex.getCause().getMessage();
            if (ejbex.getCause() instanceof JDBCConnectionException) {
                message = "There was a problem connecting to the database. Please try again later.";
            }

            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR, message, null);
            return;
        }

        if (user == null) {
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_ERROR,
                    "Wrong credentials. Are you sure you entered the correct credentials?",
                    "Alternatively, would you like to created a new account? ");
            return;
        }

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        HttpServletResponse resp = (HttpServletResponse) ec.getResponse();

        HttpSession session = req.getSession(true);
        session.setAttribute("user", 1);
        sSessionId = session.getId();
        sessionStarttime = new DateTime();
        System.out.println("Session " + sSessionId + " started at " + sessionStarttime);
        password = "";
        username = "";

        //do a redirect to refresh the view
        if (this.previousURI != null && !this.previousURI.isEmpty()) {
            ec.redirect(this.previousURI);
        } else {
            ec.redirect(ec.getRequestContextPath());//go to home
        }

    }

    public void checkSessionActive() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();

        HttpSession session = req.getSession(false);
        if (session == null) {

        } else {
            if (sSessionId != null && sSessionId.equals(session.getId())) {
                //hide login block
                session.setAttribute("user", 1);
            } else {
                //pop up login block
                session.setAttribute("user", 0);
                //store this current requestURI for redirection after login
                String originalURI = (String) req.getAttribute("javax.servlet.forward.request_uri");
                if (originalURI != null || !originalURI.isEmpty()) {
                    this.previousURI = originalURI;
                }
                //Check if application was installed by calling CheeckInstaller.
                if (checkInstaller.getStatus() != CheckInstaller.INSTALL_STATUS.INSTALLED) {
                    FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_INFO,
                            "Application is not installed yet, "
                            + "click here to <a href='/install/'>install</a> now", null);
                }
            }
            fc.getPartialViewContext().getRenderIds().add("login-form:loginbox-container");
        }
    }

    public void logout() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();

        ec.invalidateSession();

        ec.redirect(ec.getRequestContextPath());//go to home
        FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_INFO,
                "You have logged out successfully.", null);
    }

    /**
     * Helper class
     */
    private boolean checkInstalled() {
        if (checkInstaller.getStatus() != CheckInstaller.INSTALL_STATUS.INSTALLED) {
            FacesMessenger.setFacesMessage(messageBoxId, FacesMessage.SEVERITY_INFO,
                    "Application is not installed yet, "
                    + "click here to <a href='/install/'>install</a> now", null);
            return false;
        }
        return true;
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

    public String getLoginBlockXHTML() {
        return loginBlockXHTML;
    }

    public void setLoginBlockXHTML(String loginBlockXHTML) {
        this.loginBlockXHTML = loginBlockXHTML;
    }
}
