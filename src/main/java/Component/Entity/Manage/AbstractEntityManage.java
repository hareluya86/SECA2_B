/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Entity.Manage;

import Bootstrap.Component;
import EDS.BusinessUnit.EnterpriseUnit;
import EDS.Data.DAO;
import EDS.Data.EnterpriseKey;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 * @param <T>
 */
public abstract class AbstractEntityManage<T extends EnterpriseUnit> extends Component implements EntityManage{

    protected T entity;

}
