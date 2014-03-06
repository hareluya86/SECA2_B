/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import Bootstrap.Demo.ComponentOperationFactoryDemo;

/**
 *
 * @author KH
 */
public abstract class ComponentOperationFactory {
    
    public static ComponentOperationFactory getComponentDependencyFactory(){
        return new ComponentOperationFactoryDemo();
    }
    
    public abstract ComponentOperation getComponentDependency();
    
    public abstract ComponentOperation getComponentDependency(String componentDependency);
}
