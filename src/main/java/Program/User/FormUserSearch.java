/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Entity.User.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author KH
 */
public class FormUserSearch implements Serializable{
    
    private List<UserEntity> testResults;
    private String searchUsername;
    
    @PostConstruct
    public void init(){
        testResults = new ArrayList<UserEntity>();
        
        //Test data
        for(int i=0; i<10; i++){
            UserEntity user = new UserEntity();
            user.setUSERNAME("User"+i);
            testResults.add(user);
        }
        searchUsername = "";
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
    
    
}
