/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import java.util.Map;

/**
 * A Program is a broad overview of a set of functionalities. For example,
 * the Entity component contains a set of functionalities to manipulate entities;
 * the Authorization component contains a set of functionalities to control 
 * access by users, the User component contains a set of functionalities to 
 * control user accounts.
 * <p>
 * @author KH
 */
public abstract class Program /*implements EnterpriseObject*/ {
    
    protected long PROGRAM_ID;
    protected String PROGRAM_NAME;
    protected String PROGRAM_DIRECTORY;
    protected String PROGRAM_XHTML;
    protected Map<String,Object> PROGRAM_PARAM;
    
    /**
     * Must be called after constructor.
     * <p>
     * This method initializes all program parameters. It can be annotated with 
     * @PostConstruct in an EJB context.
     */
    public abstract void init();
    
    /**
     * Executes a component operation in the program.
     * <p>
     * Implementation is done here instead of subclasses so that subclasses only
     * need to declare which operations they want to execute and inject/instantiate
     * those ComponentOperation classes.
     * 
     * @param operation 
     */
    
    //@Override
    public String tableName(){
        return "PROGRAM";
    }

    public long getPROGRAM_ID() {
        return PROGRAM_ID;
    }

    public void setPROGRAM_ID(long PROGRAM_ID) {
        this.PROGRAM_ID = PROGRAM_ID;
    }

    public String getPROGRAM_NAME() {
        return PROGRAM_NAME;
    }

    public void setPROGRAM_NAME(String PROGRAM_NAME) {
        this.PROGRAM_NAME = PROGRAM_NAME;
    }

    public String getPROGRAM_DIRECTORY() {
        return PROGRAM_DIRECTORY;
    }

    public void setPROGRAM_DIRECTORY(String PROGRAM_DIRECTORY) {
        this.PROGRAM_DIRECTORY = PROGRAM_DIRECTORY;
    }

    public String getPROGRAM_XHTML() {
        return PROGRAM_XHTML;
    }

    public void setPROGRAM_XHTML(String PROGRAM_XHTML) {
        this.PROGRAM_XHTML = PROGRAM_XHTML;
    }

    public Map<String, Object> getPROGRAM_PARAM() {
        return PROGRAM_PARAM;
    }

    public void setPROGRAM_PARAM(Map<String, Object> PROGRAM_PARAM) {
        this.PROGRAM_PARAM = PROGRAM_PARAM;
    }
}
