/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.File.FileEntity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.joda.time.DateTime;

/**
 *
 * @author KH
 */
public class DateCreatedListener {
    
    @PreUpdate
    @PrePersist
    public void setDATE_CREATED(FileEntity fe){
        if(fe.getDATE_CHANGED() == null){
            DateTime today = new DateTime();
            java.sql.Date sqlDate = new java.sql.Date(today.getMillis());
            fe.setDATE_CREATED(sqlDate);
        }
        
    }
    
}
