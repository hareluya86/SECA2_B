/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import EDS.Data.EnterpriseEntity;
import java.util.Map;

/**
 *
 * @author KH
 */
public abstract class Component implements EnterpriseEntity{
    
    protected long COMPONENT_ID;
    protected String COMPONENT_NAME;
    protected String COMPONENT_DIRECTORY;
    protected String COMPONENT_XHTML;
    protected Map<String,Object> COMPONENT_PARAM;
}
