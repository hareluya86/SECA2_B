package Component.Entity.Search.Demo;

import Component.Entity.Search.EntitySearch;
import Component.Entity.Search.EntitySearchFactory;
import EDS.BusinessUnit.EnterpriseUnit;
import EDS.BusinessUnit.Test.TestUnit;
import EDS.BusinessUnit.Test.TestUnit_;
import SECA2.File.FileEntity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KH
 */
public class EntitySearchFactoryDemo extends EntitySearchFactory {

    @Override
    public EntitySearch getEntitySearch(String entityUnitType) {
        
        //Testing using TestUnit
        if(entityUnitType.equalsIgnoreCase("File")){
            EntitySearch<FileEntity> result = new EntitySearchDemo<FileEntity>();
            //result.setMetaClass(TestUnit_.class);
            return result;
        }
        else{
            throw new RuntimeException("Entity "+entityUnitType+" not found!");
        }
    }

    
    
}
