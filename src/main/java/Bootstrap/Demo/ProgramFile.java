/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.Program;
import Component.Entity.Search.EntitySearch;
import Component.Entity.Search.EntitySearchFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 */
public class ProgramFile extends Program {
    
    public ProgramFile(){
        this.setPROGRAM_PARAM(new HashMap<String,Object>());
        
        this.setPROGRAM_XHTML("/programs/file/layout.xhtml");
        
        
        //Create required components
        EntitySearchFactory esf = EntitySearchFactory.getEntitySearchFactory();
        EntitySearch entitySearch = esf.getEntitySearch("File");
        this.getPROGRAM_PARAM().put("search", entitySearch);
    }
    
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
    
}
