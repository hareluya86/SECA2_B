/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.ComponentOperation;
import Bootstrap.Program;
import Component.Entity.Manage.AbstractEntityManage;
import Component.Entity.Search.AbstractEntitySearch;

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
        
        //EnterpriseUnit selectedEnterpriseUnit = (EnterpriseUnit) program.getPROGRAM_PARAM().get(selectedResultName);
        
        //manageComponent.setEntity(selectedEnterpriseUnit);
    }

    
}
