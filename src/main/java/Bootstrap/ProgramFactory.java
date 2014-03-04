/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import Bootstrap.Demo.ComponentFactoryDemo;

/**
 *
 * @author KH
 */
public abstract class ComponentFactory {
    
    public static ComponentFactory getComponentFactory(){
        return new ComponentFactoryDemo();
    }
    
    public abstract Component getComponent();
    
    public abstract Component getComponent(String component);
}
