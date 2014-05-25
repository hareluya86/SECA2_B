/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bootstrap.Demo;

import Component.User.UserService;
import Entity.User.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.hibernate.exception.JDBCConnectionException;

/**
 * A class to check if application was installed. Criteria to consider the
 * applicatioon installed: 1) At least 1 user must have been created.
 * <p>
 * @author vincent.a.lee
 */
@Named("CheckInstaller")
@ApplicationScoped
public class CheckInstaller implements Serializable {

    private INSTALL_STATUS status;

    public static enum INSTALL_STATUS {

        INSTALLED,
        UNINSTALLED,
        DB_ERROR,
        OTHER_ERROR
    }
    @EJB
    private UserService userService;

    @PostConstruct
    public void init() {
        //check if there are any users in the database, if yes, it means app is 
        //installed
        setStatus();
    }

    public INSTALL_STATUS getStatus() {
        //if status was not completely set (INSTALLED or UNINSTALLED), try again
        /*if (status != INSTALL_STATUS.INSTALLED
                && status != INSTALL_STATUS.UNINSTALLED) {//try if there's any issue here
            setStatus();
        }*/

        return status;
    }

    public void setStatus(INSTALL_STATUS status) {
        this.status = status;
    }

    public void setStatus() {
        List<UserEntity> results = new ArrayList<UserEntity>();
        try {
            results = userService.searchUserByName("");
            if (results.size() > 0) {
                this.status = INSTALL_STATUS.INSTALLED;
            } else {
                this.status = INSTALL_STATUS.UNINSTALLED;
            }
        } catch (EJBException ejbex) {
            if (ejbex.getCause() instanceof JDBCConnectionException) {
                this.status = INSTALL_STATUS.DB_ERROR;
            } else {
                this.status = INSTALL_STATUS.OTHER_ERROR;
            }
        }

    }
}
