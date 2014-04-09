/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import Component.EntitySearch.EntitySearchDemo;
import Entity.File.FileEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author vincent.a.lee
 */
public class ProgramFileSearch implements Serializable {
    
    //UI elements
    private List<Criterion> criteria;
    private List<FileEntity> results;
    
    //Dependencies
    @Inject private EntitySearchDemo entitySearch;
    
    @PostConstruct
    public void init(){
        criteria = new ArrayList<Criterion>();
        results = new ArrayList<FileEntity>();
        //Initialize required criteria
        
    }
    
    public void search(){
        
    }

    public List<FileEntity> getResults() {
        return results;
    }

    public void setResults(List<FileEntity> results) {
        this.results = results;
    }

}
