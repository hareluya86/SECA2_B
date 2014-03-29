/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Manage.Demo;

import Component.Entity.Manage.EntityManage;
import Component.Entity.Manage.EntityManageFactory;

/**
 *
 * @author KH
 */
public class EntityManageFactoryDemo extends EntityManageFactory {

    @Override
    public EntityManage getEntityManage() {
        return getEntityManage("File");
    }

    @Override
    public EntityManage getEntityManage(String name) {
        if("File".equalsIgnoreCase(name))
            return new EntityManageDemo();
        else
            throw new RuntimeException("EntityManage "+name+" is not recognized.");
    }
    
}
