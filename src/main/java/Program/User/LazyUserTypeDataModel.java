/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import Component.User.UserService;
import Entity.User.UserType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * This is just an implementation for the sake of showcasing, it might not be 
 * good enough for production use.
 * 
 * @author KH
 */
public class LazyUserTypeDataModel extends LazyDataModel<UserType> {
    
    private List<UserType> datasource;
    @EJB UserService userService;
    
    public LazyUserTypeDataModel(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserType getRowData(String userTypeName) {
        for(UserType type : datasource){
            if(type.getUSERTYPENAME().equals(userTypeName))
                return type;
        }
        return null;
    }


    /*@Override
    public List<UserType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<UserType> results = new ArrayList<UserType>();
        
        //ignore filters and sortings for the time being
        //results = datasource.subList(first, Math.min(first+pageSize-1,datasource.size()));
        results = userService.getUserTypes(first, first+pageSize-1);
        return results;
    }*/
    
    public List<UserType> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<UserType> datasource) {
        this.datasource = datasource;
    }

    
    
    
}
