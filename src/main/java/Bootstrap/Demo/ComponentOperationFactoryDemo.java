/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.ComponentOperation;
import Bootstrap.ComponentOperationFactory;

/**
 *
 * @author KH
 */
public class ComponentOperationFactoryDemo extends ComponentOperationFactory{

    
    @Override
    public ComponentOperation getComponentDependency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComponentOperation getComponentDependency(String componentOperation) {
        if("SearchAndManageOperation".equalsIgnoreCase(componentOperation))
            return new SearchAndManageOperation();
        else
            throw new RuntimeException("ComponentOperation "+componentOperation+" not recognized.");
    }
    
}
