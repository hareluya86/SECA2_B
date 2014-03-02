/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.Component;
import Bootstrap.ComponentFactory;
import Component.Entity.Entity;

/**
 *
 * @author KH
 */
public class ComponentFactoryDemo extends ComponentFactory {

    private final String DEFAULT_COMPONENT = "ENTITY";
    
    @Override
    public Component getComponent() {
        return getComponent(DEFAULT_COMPONENT);
    }

    @Override
    public Component getComponent(String component) {
        if("Entity".equalsIgnoreCase(component)){
            return new Entity();
        }
        
        else{
            throw new RuntimeException("Component "+component+" not recognized!");
        }
    }
    
    
}
