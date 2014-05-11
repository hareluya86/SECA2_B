/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Bootstrap.Program;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author KH
 */
@Named("ProgramUser")
@SessionScoped
public class ProgramUser extends Program implements Serializable {

    @Inject private FormUserLogin userLogin;
    @Inject private FormUserSearch userSearch;
    
    @Inject private FormUserCreate userCreate;
    @Inject private FormUserTypeMaintain userTypeMaintain;
    @Inject private FormUserTypeCreate userTypeCreate;
    
    @PostConstruct
    @Override
    public void init() {
        
    }
    
    public FormUserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(FormUserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public FormUserSearch getUserSearch() {
        return userSearch;
    }

    public void setUserSearch(FormUserSearch userSearch) {
        this.userSearch = userSearch;
    }
    
    public FormUserCreate getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(FormUserCreate userCreate) {
        this.userCreate = userCreate;
    }

    public FormUserTypeMaintain getUserTypeMaintain() {
        return userTypeMaintain;
    }

    public void setUserTypeMaintain(FormUserTypeMaintain userTypeMaintain) {
        this.userTypeMaintain = userTypeMaintain;
    }

    public FormUserTypeCreate getUserTypeCreate() {
        return userTypeCreate;
    }

    public void setUserTypeCreate(FormUserTypeCreate userTypeCreate) {
        this.userTypeCreate = userTypeCreate;
    }
}
