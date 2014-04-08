/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author KH
 */
public interface ViewPage extends Serializable{
    
    public void init();
    /**
     * 
     * @return 
     */
    public String getRoot();
    
    public void setRoot(String root);
    
    public String getName();
    
    public void setName(String name);
    
    public List<String> getComponents();
    
    public void setComponents(List<String> components);
}
