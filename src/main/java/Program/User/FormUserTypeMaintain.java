/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.User.UserService;
import Entity.User.UserType;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author KH
 */
@Named("MaintainUserType")
@RequestScoped
public class FormUserTypeMaintain implements Serializable {
    
    private List<UserType> userTypes;
    private LazyDataModel lazyUserTypes;
    
    @EJB private UserService userService;
    
    @PostConstruct
    public void init(){
        userTypes = userService.getUserTypes(0, 99);
        lazyUserTypes = new LazyUserTypeDataModel(userService);
    }

    public List<UserType> getUserTypes() {
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
