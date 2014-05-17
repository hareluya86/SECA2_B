/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.User.UserService;
import Entity.User.UserEntity;
import Entity.User.UserType;
import Program.Messenger.FacesMessenger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author KH
 */
public class FormUserSearch implements Serializable{
    
    private List<UserEntity> testResults;
    private List<UserEntity> searchResults;
    private String searchUsername;
    private long searchUsertypeID;
    
    private final String formName = "search-user-form";
    
    @EJB private UserService userService;
    
    @PostConstruct
    public void init(){
        testResults = new ArrayList<UserEntity>();
        searchResults = new ArrayList<UserEntity>();
        //Test data
        for(int i=0; i<10; i++){
            UserEntity user = new UserEntity();
            user.setUSERNAME("User"+i);
            testResults.add(user);
        }
        searchUsername = "";
    }
    
    public List<UserType> getUserTypes(){
        List<UserType> userTypes = new ArrayList<UserType>();
        try{
            userTypes = userService.getUserTypes(0, 99);
        }catch(JDBCConnectionException jdbcex){
            FacesMessenger.setFacesMessage(formName,
                    FacesMessage.SEVERITY_ERROR,"Database connection error!",jdbcex.getMessage());
        }
        return userTypes;
    }
    
    public void search(){
        
    }

    public List<UserEntity> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<UserEntity> testResults) {
        this.testResults = testResults;
    }

    public String getSearchUsername() {
        return searchUsername;
    }

    public void setSearchUsername(String searchUsername) {
        this.searchUsername = searchUsername;
    }

    public List<UserEntity> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<UserEntity> searchResults) {
        this.searchResults = searchResults;
    }

    public long getSearchUsertypeID() {
        return searchUsertypeID;
    }

    public void setSearchUsertypeID(long searchUsertypeID) {
        this.searchUsertypeID = searchUsertypeID;
    }
    
    
}
