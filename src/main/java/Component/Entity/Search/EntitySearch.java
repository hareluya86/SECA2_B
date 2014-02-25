/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search;

import EDS.BusinessUnit.EnterpriseUnit;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;

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
    public Collection<EnterpriseUnit> getResults();
}
