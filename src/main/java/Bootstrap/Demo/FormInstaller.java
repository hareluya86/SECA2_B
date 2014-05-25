/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap.Demo;

import Component.Data.DBConnection;
import Component.User.UserRegistrationException;
import Component.User.UserService;
import Component.User.UserTypeException;
import Entity.User.UserType;
import Program.Messenger.FacesMessenger;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author vincent.a.lee
 */
@Named("Installer")
@RequestScoped
public class FormInstaller implements Serializable {

    private DBConnection connSettings;
    private final String formName = "installer";

    private String username;
    private String password;
    private final String selectedUsertype = "HUMAN";

    //@Inject private FormUserCreate userCreate; forms shouldn't use forms
    @Inject
    private CheckInstaller checkInstaller;
    @EJB
    private UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("installer is instantiated");
    }

    public void install() {

        try {
            //Create first usertype if doesn't exist
            UserType userType = userService.getUserTypeByName(selectedUsertype);
            if (userType == null) {
                userService.createUserType(selectedUsertype, "The first ever type of users.");
            }
            userService.registerNewUser(username, password, selectedUsertype);
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_INFO, "User " + username + " created successfully!", null);
            this.username = "";
            this.password = "";
        } catch (UserTypeException utex) {
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_ERROR, utex.getMessage(), null);
            return;
        } catch (UserRegistrationException urex) {
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_ERROR, urex.getMessage(), null);
            return;
        } catch (EJBException ejbex) {
            String message = ejbex.getCause().getMessage();
            if (ejbex.getCause() instanceof JDBCConnectionException) {
                FacesMessenger.setFacesMessage(formName, FacesMessage.SEVERITY_ERROR,
                        "DB Connection error", ejbex.getMessage());
            } else {
                FacesMessenger.setFacesMessage(formName, FacesMessage.SEVERITY_ERROR, message, null);
            }
            return;
        }
        //Set CheckInstaller's installed indicator once installation is successful
        this.checkInstaller.setStatus(CheckInstaller.INSTALL_STATUS.INSTALLED);
        System.out.println("Installation successful");//debug
    }
    
    public boolean renderInstallForm(){
        if(checkInstaller.getStatus() != CheckInstaller.INSTALL_STATUS.INSTALLED){
            return true;
        }
        return false;
    }
    
    public boolean renderWelcome(){
        return !renderInstallForm();
    }

    public DBConnection getConnSettings() {
        return connSettings;
    }

    public void setConnSettings(DBConnection connSettings) {
        this.connSettings = connSettings;
    }

    public CheckInstaller getCheckInstaller() {
        return checkInstaller;
    }

    public void setCheckInstaller(CheckInstaller checkInstaller) {
        this.checkInstaller = checkInstaller;
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

}
