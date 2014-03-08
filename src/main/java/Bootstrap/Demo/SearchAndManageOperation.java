/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.Component;
import Bootstrap.ComponentOperation;
import Bootstrap.Program;
import Component.Entity.Manage.AbstractEntityManage;
import Component.Entity.Manage.EntityManageFactory;
import Component.Entity.Search.AbstractEntitySearch;
import EDS.BusinessUnit.EnterpriseUnit;

/**
 *
 * @author KH
 */
public class SearchAndManageOperation implements ComponentOperation {

    private String selectedResultName = "selectedEnterpriseUnit";

    @Override
    public void execute(Program program) {
        AbstractEntitySearch searchComponent = (AbstractEntitySearch)program.getPROGRAM_PARAM().get("EntitySearch");
        AbstractEntityManage manageComponent = (AbstractEntityManage)program.getPROGRAM_PARAM().get("EntityManage");
        
        program.getPROGRAM_PARAM().put("subprogram","manage.xhtml");
        
        //EnterpriseUnit selectedEnterpriseUnit = searchComponent.get
        manageComponent.setEntity(null);
    }

    
}
