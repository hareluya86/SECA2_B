/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Template;

import EDS.Data.EnterpriseObject;
import java.util.Map;


/**
 *
 * @author KH
 */
public abstract class Template implements EnterpriseObject {
    
    protected String TEMPLATE_ID;
    protected String TEMPLATE_NAME;
    protected String TEMPLATE_DIRECTORY;
    protected String TEMPLATE_XHTML;
    protected Map<String,Object> TEMPLATE_PARAMS;

    @Override
    public String tableName(){
        return "TEMPLATE";
    }

    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    public String getTEMPLATE_NAME() {
        return TEMPLATE_NAME;
    }

    public void setTEMPLATE_NAME(String TEMPLATE_NAME) {
        this.TEMPLATE_NAME = TEMPLATE_NAME;
    }

    public String getTEMPLATE_DIRECTORY() {
        return TEMPLATE_DIRECTORY;
    }

    public void setTEMPLATE_DIRECTORY(String TEMPLATE_DIRECTORY) {
        this.TEMPLATE_DIRECTORY = TEMPLATE_DIRECTORY;
    }

    public String getTEMPLATE_XHTML() {
        return TEMPLATE_XHTML;
    }

    public void setTEMPLATE_XHTML(String TEMPLATE_XHTML) {
        this.TEMPLATE_XHTML = TEMPLATE_XHTML;
    }

    public Map<String, Object> getTEMPLATE_PARAMS() {
        return TEMPLATE_PARAMS;
    }

    public void setTEMPLATE_PARAMS(Map<String, Object> TEMPLATE_PARAMS) {
        this.TEMPLATE_PARAMS = TEMPLATE_PARAMS;
    }
    
    
}
