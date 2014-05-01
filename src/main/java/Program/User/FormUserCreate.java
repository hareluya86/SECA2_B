/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.Data.HibernateUtil;
import Entity.File.FileEntity;
import Entity.User.UserEntity;
import Entity.User.UserType;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author KH
 */
public class FormUserCreate implements Serializable {
    
    private String username;
    private String password;
    
    @Inject private HibernateUtil hibernateUtil;
    
    @PostConstruct
    public void init(){
        
    }
    
    public List<UserType> getUserTypes(int firstResult, int maxResult){
        Session session = hibernateUtil.getSession();
        Criteria selectAll = session.createCriteria(UserEntity.class).setFirstResult(firstResult)
                .setMaxResults(maxResult);
        List<UserType> result = selectAll.list();
        
        return result;
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
