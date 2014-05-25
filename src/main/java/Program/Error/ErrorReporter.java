/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.Error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author vincent.a.lee
 */
@Named("ErrorReporter")
@RequestScoped
public class ErrorReporter {
    
    public String getErrorTitle(){
        // Get the current JSF context
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestMap();
 
        // Fetch the exception
        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");
        if(ex != null) return ex.getMessage();
        return null;
    }
    
    public String getStackTrace(){
        // Get the current JSF context
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestMap();
 
        // Fetch the exception
        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");
 
        // Create a writer for keeping the stacktrace of the exception
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
 
        // Fill the stack trace into the write
        if(ex != null) ex.printStackTrace(pw);
 
        return writer.toString();
    }
}
