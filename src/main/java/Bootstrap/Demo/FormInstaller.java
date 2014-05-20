/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Component.Data.DBConnection;
import Entity.User.UserEntity;
import Program.User.FormUserCreate;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vincent.a.lee
 */
@Named("Installer")
@SessionScoped
public class FormInstaller implements Serializable {
    private DBConnection connSettings;
    private final String formName = "installer-form";
    
    @Inject private FormUserCreate userCreate;
    
    public void install(){
        //Create the first user
        this.userCreate.registerNewUser();
        System.out.println("install was called");//debug
    }

    public DBConnection getConnSettings() {
        return connSettings;
    }

    public void setConnSettings(DBConnection connSettings) {
        this.connSettings = connSettings;
    }

    public FormUserCreate getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(FormUserCreate userCreate) {
        this.userCreate = userCreate;
    }
    
    
}
