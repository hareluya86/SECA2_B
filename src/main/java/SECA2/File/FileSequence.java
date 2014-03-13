/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SECA2.File;

import EDS.BusinessUnit.EnterpriseData;
import EDS.Data.EnterpriseKey;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 */
public class FileSequence extends EnterpriseData {

    private long ORIGINAL_LINE_NUM;
    private String SEQUENCE_LINE;

    public long getORIGINAL_LINE_NUM() {
        return ORIGINAL_LINE_NUM;
    }

    public void setORIGINAL_LINE_NUM(long ORIGINAL_LINE_NUM) {
        this.ORIGINAL_LINE_NUM = ORIGINAL_LINE_NUM;
    }

    public String getSEQUENCE_LINE() {
        return SEQUENCE_LINE;
    }

    public void setSEQUENCE_LINE(String SEQUENCE_LINE) {
        this.SEQUENCE_LINE = SEQUENCE_LINE;
    }
    
    @Override
    public EnterpriseKey enterpriseKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void randInit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String tableName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
