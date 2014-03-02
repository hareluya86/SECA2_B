/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SECA2.File;

import EDS.BusinessUnit.EnterpriseUnit;
import EDS.Data.EnterpriseKey;
import java.util.List;
import java.util.Map;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;

/**
 *
 * @author KH
 */
public class FileEntity extends EnterpriseUnit {

    private String FILENAME;

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    
    @Override
    public EnterpriseKey enterpriseKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void randInit() {
        DateMidnight dm = new DateMidnight();
        LocalDate ld = new LocalDate();
        java.sql.Date sqlDate = new java.sql.Date(ld.toDate().getTime());
        int user = (int)(Math.random()*12345);
        
        this.setDATE_CREATED(sqlDate);
        this.setCREATED_BY("User "+user);
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
