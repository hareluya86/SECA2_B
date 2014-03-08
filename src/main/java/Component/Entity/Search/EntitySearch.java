/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search;

import EDS.BusinessUnit.EnterpriseUnit;
import EDS.BusinessUnit.EnterpriseUnit_;
import java.util.Collection;

/**
 *
 * @author KH
 * @param <T>
 */

public interface EntitySearch<T extends EnterpriseUnit> {
    
    /**
     * Executes the search action
     * <p>
     * This method is a listener method which can be called when either an action is
     * executed or some form of update has been done on the client side.
     */
    public void search();
    
    /**
     * Returns the result list
     * <p>
     * If search() has been called multiple times, this method returns the latest
     * retrieved result list from the last search() call. It does not append the 
     * result list each time search() is called. If search() has not been called, 
     * this will return an empty list.
     * 
     * @return  Returns the most updated result list
     */
    public Collection<T> getResults();
    
    /**
     * Returns the underlying metaclass.
     * <p>
     * 
     * @return EnterpriseUnit_ The underlying metaclass.
     */
    public EnterpriseUnit_ getMetaClass();
    
    /**
     * Sets the underlying metaclass.
     * <p>
     * Stores only 1 metaclass. If this is called multiple times, getMetaClass()
     * will return the latest metaclass set by this method. If null is passed as
     * a parameter, the underlying metaclass will be assigned as null.
     * 
     * @param EnterpriseUnit_   The underlying metaclass to be set.
     */
    public void setMetaClass(Class EnterpriseUnit_);
    
    public String getResult();
    
}
