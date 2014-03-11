/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Manage;

import Bootstrap.Component;
import EDS.BusinessUnit.EnterpriseUnit;
import EDS.Data.DAO;

/**
 *
 * @author KH
 * @param <T>
 */
public interface EntityManage<T extends EnterpriseUnit> {
    
    /**
     * Returns the managed EnterpriseUnit object
     * <p>
     * I have no idea if this is going to be used yet...let's just keep it and
     * see what happens.
     * Oh! This is a facade class for the underlying EnterpriseUnit instance. When 
     * you produce a screen that edits a particular EnterpriseUnit object, for 
     * example, Customer1, you would not load the actual Customer1 instance on 
     * the screen because you will need:
     * <ul>
     * <li>Validations - not advisable to implement in persistence class</li>
     * <li>Conversions - Codes should be converted to descriptions.</li>
     * 
     * @return T
     */
    public T getEntity();
    public void setEntity(T entity);
    
    /**
     * Updates the underlying EnterpriseUnit object into the database
     * <p>
     * This method accepts an DAO instance and should call the update method of 
     * the DAO and pass in its underlying EnterpriseUnit object instance.
     * 
     * @param dao The DAO instance used to flush the EnterpriseUnit into the database.
     */
    public void update(DAO dao);
    /**
     * Test method
     */
    public void update();
    
}
