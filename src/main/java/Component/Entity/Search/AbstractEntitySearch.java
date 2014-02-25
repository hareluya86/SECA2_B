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
public abstract class AbstractEntitySearch<T extends EnterpriseUnit> implements EntitySearch {
    /**
     * 
     */
    protected Collection<T> resultList;
    protected EnterpriseUnit_ entityUnitMetaClass;
    
}
