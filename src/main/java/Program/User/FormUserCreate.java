/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.Data.HibernateUtil;
import Component.User.UserRegistrationException;
import Component.User.UserService;
import Entity.User.UserType;
import Program.Util.FacesMessenger;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author KH
 */
public class FormUserCreate implements Serializable {
    
    private String username;
    private String password;
    
    private String formName = "createUser";
    
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
        return userService.getUserTypes(firstResult, maxResult);
    }

    public void registerNewUser(String username, String password){
        try{
            userService.registerNewUser(username, password);
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_INFO, "User "+username+" created successfully!", null);
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
    
    
}
