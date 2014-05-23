/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Component.Data.DBConnection;
import Program.User.FormUserCreate;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vincent.a.lee
 */
@Named("Installer")
@RequestScoped
public class FormInstaller implements Serializable {
    private DBConnection connSettings;
    private final String formName = "installer-form";
    
    @Inject private FormUserCreate userCreate;
    @Inject private CheckInstaller checkInstaller;
    
    @PostConstruct
    public void init(){
        System.out.println("installer is instantiated");
    }
    
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

    public CheckInstaller getCheckInstaller() {
        return checkInstaller;
    }

    public void setCheckInstaller(CheckInstaller checkInstaller) {
        this.checkInstaller = checkInstaller;
    }
    
    
}
