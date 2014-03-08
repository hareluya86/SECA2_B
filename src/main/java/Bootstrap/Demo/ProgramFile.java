/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.ComponentOperation;
import Bootstrap.Program;
import Component.Entity.Search.EntitySearch;
import Component.Entity.Search.EntitySearchFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 */
public class ProgramFile extends Program {
    
    @Override
    public String className() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> exportAsMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String exportAsString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List exportAsList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        //Instatiante all objects
        this.setPROGRAM_PARAM(new HashMap<String,Object>());
        this.setCOMP_OPERATIONS(new HashMap<String,ComponentOperation>());
        
        this.setPROGRAM_XHTML("/programs/file/layout.xhtml");
        this.getPROGRAM_PARAM().put("subprogram", "");
        //this.getPROGRAM_PARAM().put("test-el-comment","This is a test of whether EL expressions are commented out by <!--");
        
        //Create required Components
        EntitySearchFactory esf = EntitySearchFactory.getEntitySearchFactory();
        EntitySearch entitySearch = esf.getEntitySearch("File"); //Default to "File" first
        this.getPROGRAM_PARAM().put("EntitySearch", entitySearch);
        
        //Create required ComponentOperations
    }
    
    
 }
