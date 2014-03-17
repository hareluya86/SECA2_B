/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SECA2.File;

import EDS.BusinessUnit.EnterpriseData;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.joda.time.LocalDate;

/**
 *
 * @author KH
 */
@Entity
@Table(name="FILESEQUENCE")
public class FileSequence extends EnterpriseData {

    private long CURRENT_LINE_NUM;
    private String SEQUENCE_CONTENT;

    public long getORIGINAL_LINE_NUM() {
        return CURRENT_LINE_NUM;
    }

    public void setORIGINAL_LINE_NUM(long ORIGINAL_LINE_NUM) {
        this.CURRENT_LINE_NUM = ORIGINAL_LINE_NUM;
    }

    public String getSEQUENCE_LINE() {
        return SEQUENCE_CONTENT;
    }

    public void setSEQUENCE_LINE(String SEQUENCE_LINE) {
        this.SEQUENCE_CONTENT = SEQUENCE_LINE;
    }

    @Override
    public void randInit() {
        LocalDate ld = new LocalDate();
        java.sql.Date sqlDate = new java.sql.Date(ld.toDate().getTime());
        int user = (int)(Math.random()*12345);
        
        this.setCREATED_BY("User "+user);
        this.setCREATED_BY(CREATED_BY);
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
        Map<String, Object> map = super.exportAsMap();
        
        map.put("CURRENT_LINE_NUM", CURRENT_LINE_NUM);
        map.put("SEQUENCE_CONTENT", SEQUENCE_CONTENT);
        
        return map;
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
    public Object key() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
