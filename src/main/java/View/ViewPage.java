/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.util.List;
import java.util.Map;

/**
 *
 * @author KH
 */
public interface ViewPage {
    
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
