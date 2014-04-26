/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.EntitySearch;

import Component.Data.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
    
    private T type;
    private List<Object> results; //no attributes in Stateless beans
    private Criteria criteria;
    @Inject private HibernateUtil hibernateUtil;
    private Session session;
    /**
    * Components should not have to deal with database connections. Reason:
    * - Each frontend program uses many components and it is not necessary for a
    * single user to open up multiple database connections at the same time. 
    * 
    * UPDATE 20140425 - Depends on the usage of database of the application, if 
    * it's a "sparse" app, only EJBs should be injected with the DAO. 
    * 
    */
    
    @PostConstruct
    public void init(){
        session = hibernateUtil.getSession();
        criteria = session.createCriteria(type.getClass());
    }
    
    public void addCriterion(String operand, String value, SearchOp operator){
        Criterion newCriterion = null;
        switch(operator){
            case EQUALS     :   newCriterion = Restrictions.eq(operand, value);
                                break;
            case GREATER    :   newCriterion = Restrictions.gt(operand, value);
                                break;
            
        }
        if(criteria != null){
            
        }
    }
    
    
    public void reset(){
        
    }
    
}
