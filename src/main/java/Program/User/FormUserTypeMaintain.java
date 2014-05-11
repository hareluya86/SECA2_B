/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.User.UserService;
import Entity.User.UserType;
import Program.Util.FacesMessenger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.exception.JDBCConnectionException;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author KH
 */
@Named("userTypeMaintain")
@RequestScoped
public class FormUserTypeMaintain implements Serializable {
    
    private List<UserType> userTypes;
    private LazyDataModel lazyUserTypes;
    private final String formName = "maintainUserTypes";
    
    @EJB private UserService userService;
    
    @PostConstruct
    public void init(){
        /*try{
            userTypes = userService.getUserTypes(0, 99);
        }catch(JDBCConnectionException jdbcex){
            //FacesMessenger.setFacesMessage(formName,
            //        FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
            userTypes = new ArrayList<UserType>();
        }*/
        //lazyUserTypes = new LazyUserTypeDataModel(userService);
    }
    
    public void onEdit(RowEditEvent event) {
        Object edited = event.getObject();
        if(edited != null && edited.getClass() == UserType.class){
            UserType editedUserType = (UserType)edited;
            try{
                userService.modifyUserType(editedUserType);
            } catch(JDBCConnectionException jdbcex){
                FacesMessenger.setFacesMessage(formName,
                        FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
            } catch(RuntimeException rtex){
                FacesMessenger.setFacesMessage(formName,
                        FacesMessage.SEVERITY_ERROR,rtex.getMessage(),null);
            }
        }
                
    }
    
    

    public List<UserType> getUserTypes() {
        try{
            userTypes = userService.getUserTypes(0, 99);
        }catch(JDBCConnectionException jdbcex){
            //FacesMessenger.setFacesMessage(formName,
            //        FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
            userTypes = new ArrayList<UserType>();
        }
        
        return userTypes;
    }

    public void setUserTypes(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public LazyDataModel getLazyUserTypes() {
        return lazyUserTypes;
    }

    public void setLazyUserTypes(LazyDataModel lazyUserTypes) {
        this.lazyUserTypes = lazyUserTypes;
    }
    
    
}
