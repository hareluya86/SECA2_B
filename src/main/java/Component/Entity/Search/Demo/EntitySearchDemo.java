/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search.Demo;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author KH
 * @param <T>
 */
@Stateless
public class EntitySearchDemo<T> implements Serializable{
    
    private List<Object> results;
    /**
    * Components should not have to deal with database connections. Reason:
    * - Each frontend program uses many components and it is not necessary for a
    * single user to open up multiple database connections at the same time. 
    * 
    @Inject
    private HibernateUtil hibernateUtil;
    */
    
    @PostConstruct
    public void init(){
        
    }
    
    public Criterion getCriterion(String operand, String value, SearchOp operator){
        Criterion newCriterion = null;
        switch(operator){
            case EQUALS     :   newCriterion = Restrictions.eq(operand, value);
                                break;
            case GREATER    :   newCriterion = Restrictions.gt(operand, value);
                                break;
            
        }
        
        return newCriterion;
    }
    
    public List<Object> search(Session session, Criteria criteria){
        
        throw new UnsupportedOperationException("EntitySearch.search() not supported yet!");
        
    }
    
    public void reset(){
        
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }
    
}
