/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search.Demo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author KH
 * @param <T>
 */
public class EntitySearchDemo implements Serializable{

    private List<Object> results;

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }
    
    public void search(){
        
    }
    
    public void reset(){
        
    }
}
