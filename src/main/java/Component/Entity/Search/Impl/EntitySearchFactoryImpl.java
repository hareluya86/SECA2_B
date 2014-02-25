package Component.Entity.Search.Impl;

import Component.Entity.Search.EntitySearch;
import Component.Entity.Search.EntitySearchFactory;
import EDS.BusinessUnit.EnterpriseUnit;
import EDS.BusinessUnit.Test.TestUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KH
 */
public class EntitySearchFactoryImpl extends EntitySearchFactory {

    @Override
    public EntitySearch getEntitySearch(String entityUnitType) {
        
        //Testing using TestUnit
        if(entityUnitType.equalsIgnoreCase("TestUnit")){
            EntitySearch<TestUnit> result = new EntitySearchImpl<TestUnit>();
            
            return result;
        }
        else{
            throw new RuntimeException("Entity "+entityUnitType+" not found!");
        }
    }

    
    
}
