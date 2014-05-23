/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Component.User.UserService;
import Entity.User.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author vincent.a.lee
 */
@Named("CheckInstaller")
@ApplicationScoped
public class CheckInstaller implements Serializable {
    
    private boolean installed;
    
    @EJB private UserService userService;
    
    
    @PostConstruct
    public void init(){
        //check if there are any users in the database, if yes, it means app is 
        //installed
        List<UserEntity> results = userService.searchUserByName("*");
        this.installed = results.size() > 0;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }
    
    
}
