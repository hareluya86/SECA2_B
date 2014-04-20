/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Bootstrap.Program;
import java.io.Serializable;
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
    
    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FormUserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(FormUserLogin userLogin) {
        this.userLogin = userLogin;
    }
    
    
}
