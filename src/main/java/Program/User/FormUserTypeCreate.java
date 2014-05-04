/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program.User;

import Component.User.UserService;
import Component.User.UserTypeException;
import Program.Util.FacesMessenger;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author KH
 */
public class FormUserTypeCreate implements Serializable {

    private String userType;
    private String description;
    private boolean portalAccess;
    private boolean wsAccess;

    private String formName = "createUserTypes";

    @EJB
    private UserService userService;

    public void create() {

        try {
            userService.createUserType(userType, description);
            FacesMessenger.setFacesMessage(formName, FacesMessage.SEVERITY_INFO, "Usertype created.", null);

            this.userType = "";
            this.description = "";
        } catch (UserTypeException ex) {
            FacesMessenger.setFacesMessage(formName, FacesMessage.SEVERITY_ERROR, ex.getMessage(), null);
        }
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPortalAccess() {
        return portalAccess;
    }

    public void setPortalAccess(boolean portalAccess) {
        this.portalAccess = portalAccess;
    }

    public boolean isWsAccess() {
        return wsAccess;
    }

    public void setWsAccess(boolean wsAccess) {
        this.wsAccess = wsAccess;
    }

}
