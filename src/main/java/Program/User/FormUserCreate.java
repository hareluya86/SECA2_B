/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.User.UserRegistrationException;
import Component.User.UserService;
import Entity.User.UserType;
import Program.Messenger.FacesMessenger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author KH
 */
@Named("userCreate")
@RequestScoped
public class FormUserCreate implements Serializable {
    
    private String username;
    private String password;
    private String selectedUsertype;
    
    private final String formName = "createUser";
    
    @EJB private UserService userService;
    
    @PostConstruct
    public void init(){
        
    }
    
    /**
     * Shell method.
     * 
     * @param firstResult
     * @param maxResult
     * @return 
     */
    public List<UserType> getUserTypes(int firstResult, int maxResult){
        List<UserType> results = new ArrayList<UserType>();
        try{
            results = userService.getUserTypes(firstResult, maxResult);
        }catch(JDBCConnectionException jdbcex){
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
        }
        return results;
    }

    public void registerNewUser(){
        try{
            userService.registerNewUser(username, password, selectedUsertype);
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_INFO, "User "+username+" created successfully!", null);
            this.username = "";
            this.password = "";
        }catch(UserRegistrationException urex){
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_ERROR, urex.getMessage(), null);
        }catch(JDBCConnectionException jdbcex){
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
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

    public String getSelectedUsertype() {
        return selectedUsertype;
    }

    public void setSelectedUsertype(String selectedUsertype) {
        this.selectedUsertype = selectedUsertype;
    }

    public String getFormName() {
        return formName;
    }
    
    
}
