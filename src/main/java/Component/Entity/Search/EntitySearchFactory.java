/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search;

import Component.Entity.Search.Impl.EntitySearchFactoryImpl;
import EDS.BusinessUnit.EnterpriseUnit;

/**
 *
 * @author KH
 */
public abstract class EntitySearchFactory {
    
    /**
     * Produces an EntitySearchFactory
     * <p>
     * This method will produce an EntitySearchFactory object.
     */
    public static EntitySearchFactory getEntitySearchFactory(){
        return new EntitySearchFactoryImpl();
    }
    
    public abstract EntitySearch getEntitySearch(String entityUnitType);
}
