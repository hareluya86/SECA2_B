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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;

/**
 *
 * @author KH
 */
public class FileEntity extends EnterpriseUnit {

    private String FILENAME;

    @Override
    public Object key() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static enum FILE_STATUS{
        INCOMPLETE,
        COMPLETED
    }
    
    private FILE_STATUS STATUS;
    

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    @Enumerated(EnumType.STRING)
    public FILE_STATUS getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(FILE_STATUS STATUS) {
        this.STATUS = STATUS;
    }

    
    @Override
    public EnterpriseKey getKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void randInit() {
        DateMidnight dm = new DateMidnight();
        LocalDate ld = new LocalDate();
        java.sql.Date sqlDate = new java.sql.Date(ld.toDate().getTime());
        int user = (int)(Math.random()*12345);
        if(Math.random() >0.5) this.setSTATUS(FILE_STATUS.COMPLETED);
        else this.setSTATUS(FILE_STATUS.INCOMPLETE);
        int filename = (user+6)/7;
        
        this.setFILENAME("File "+filename);
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
