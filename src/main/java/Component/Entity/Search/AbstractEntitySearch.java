/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Search;

import Bootstrap.Component;
import EDS.BusinessUnit.EnterpriseUnit;
import EDS.BusinessUnit.EnterpriseUnit_;
import java.util.Collection;

/**
 *
 * @author KH
 * @param <T>
 */
public abstract class AbstractEntitySearch<T extends EnterpriseUnit> extends Component implements EntitySearch {
    /**
     * 
     */
    protected Collection<T> results;
    protected EnterpriseUnit_ entityUnitMetaClass;
    protected String result;
    
    /**
     * Keyword for searching
     */
    protected Collection<String> keywords;
}
