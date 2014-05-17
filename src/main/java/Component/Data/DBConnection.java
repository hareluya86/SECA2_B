/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.Data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author vincent.a.lee
 */
@Entity
@Table(name="DBCONNECTION")
@TableGenerator(name="DBCONNECTION_SEQ",initialValue=1,allocationSize=1,table="SEQUENCE")
public class DBConnection implements Serializable{
    
    private long DBCONNECTIONID;
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private boolean BATCH_STATEMENTS;
    private int MIN_CONN_SIZE;
    private int MAX_CONN_SIZE;
    private int MAX_STATEMENTS;

    @Id @GeneratedValue(generator="DBCONNECTION_SEQ",strategy=GenerationType.TABLE)
    public long getDBCONNECTIONID() {
        return DBCONNECTIONID;
    }

    public void setDBCONNECTIONID(long DBCONNECTIONID) {
        this.DBCONNECTIONID = DBCONNECTIONID;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }

    public String getDB_USERNAME() {
        return DB_USERNAME;
    }

    public void setDB_USERNAME(String DB_USERNAME) {
        this.DB_USERNAME = DB_USERNAME;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public boolean isBATCH_STATEMENTS() {
        return BATCH_STATEMENTS;
    }

    public void setBATCH_STATEMENTS(boolean BATCH_STATEMENTS) {
        this.BATCH_STATEMENTS = BATCH_STATEMENTS;
    }

    public int getMIN_CONN_SIZE() {
        return MIN_CONN_SIZE;
    }

    public void setMIN_CONN_SIZE(int MIN_CONN_SIZE) {
        this.MIN_CONN_SIZE = MIN_CONN_SIZE;
    }

    public int getMAX_CONN_SIZE() {
        return MAX_CONN_SIZE;
    }

    public void setMAX_CONN_SIZE(int MAX_CONN_SIZE) {
        this.MAX_CONN_SIZE = MAX_CONN_SIZE;
    }

    public int getMAX_STATEMENTS() {
        return MAX_STATEMENTS;
    }

    public void setMAX_STATEMENTS(int MAX_STATEMENTS) {
        this.MAX_STATEMENTS = MAX_STATEMENTS;
    }
    
    
}
