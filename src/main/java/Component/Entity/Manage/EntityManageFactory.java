/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Manage;

import Component.Entity.Manage.Demo.EntityManageFactoryDemo;

/**
 *
 * @author KH
 */
public abstract class EntityManageFactory {
    
    public static EntityManageFactory getEntityManageFactory(){
        return new EntityManageFactoryDemo();
    }
    
    public abstract EntityManage getEntityManage();
    
    public abstract EntityManage getEntityManage(String name);
}
