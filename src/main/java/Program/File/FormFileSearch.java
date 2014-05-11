/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import Component.Data.HibernateUtil;
import Entity.File.FileEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vincent.a.lee
 */
public class FormFileSearch implements Serializable {
    
    //UI elements
    private String searchName;
    private Date searchStartDate;
    private Date searchEndDate;
    private List<FileEntity> results;
    
    //Dependencies
    //@Inject private EntitySearchDemo entitySearch;
    @Inject private HibernateUtil hibernateUtil;
    
    @PostConstruct
    public void init(){
        results = new ArrayList<FileEntity>();
        //Initialize required criteria
        
    }
    
    public void search(){
        Session session = hibernateUtil.getSession();
        results = session.createCriteria(FileEntity.class)
                .add(Restrictions.ilike("FILENAME", "%"+searchName+"%"))
                //.add(Restrictions.ge("DATE_CREATED", searchStartDate))
                //.add(Restrictions.le("DATE_CREATED", searchEndDate))
                .list();
    }

    public List<FileEntity> getResults() {
        return results;
    }

    public void setResults(List<FileEntity> results) {
        this.results = results;
    }

    public void reset(){
        this.searchName = "";
        this.searchStartDate = null;
        this.searchEndDate = null;
        this.results = new ArrayList<FileEntity>();
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Date getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(Date searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public Date getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(Date searchEndDate) {
        this.searchEndDate = searchEndDate;
    }
    
    
}
