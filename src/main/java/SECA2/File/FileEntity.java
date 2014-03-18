/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SECA2.File;

import EDS.BusinessUnit.EnterpriseUnit;
import java.util.List;
import java.util.Map;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;

/**
 * An entity class that represents a File
 * <p>
 * 
 * 
 * Note: We will be using EDS, but this would not utilize the full features of 
 * EDS. The following features will not be implemented from EDS:
 * - EnterpriseData 
 * @author KH
 */
@Entity
@Table(name="FILEENTITY")
@DiscriminatorValue("FILEENTITY")
public class FileEntity extends EnterpriseUnit {

    private String FILENAME;
    private long BYTE_SIZE;
    private long SEQUENCE_SIZE;
    private FILE_STATUS STATUS;
    
    private List<FileSequence> sequences;

    public static enum FILE_STATUS{
        INCOMPLETE,
        COMPLETED
    }
    
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

    public long getBYTE_SIZE() {
        return BYTE_SIZE;
    }

    public void setBYTE_SIZE(long BYTE_SIZE) {
        this.BYTE_SIZE = BYTE_SIZE;
    }

    public long getSEQUENCE_SIZE() {
        return SEQUENCE_SIZE;
    }

    public void setSEQUENCE_SIZE(long SEQUENCE_SIZE) {
        this.SEQUENCE_SIZE = SEQUENCE_SIZE;
    }

    @OneToMany(fetch=FetchType.LAZY)
    public List<FileSequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<FileSequence> sequences) {
        this.sequences = sequences;
    }
    
    @Override
    public Object key() {
        return this.getOBJECTID();
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
        Map<String, Object> map = super.exportAsMap();
        
        map.put("FILENAME", FILENAME);
        map.put("BYTE_SIZE",BYTE_SIZE);
        map.put("SEQUENCE_SIZE",SEQUENCE_SIZE);
        map.put("FILE_STATUS",STATUS);    
        
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
    
}
