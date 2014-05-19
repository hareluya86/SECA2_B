/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KH
 */
public abstract class ViewPage implements Serializable{
    
    protected String root;
    protected String name;
    protected List<String> components;
    
    public void init(){
        root = "";
        name = "";
        components = new ArrayList<String>();
    }

    /**
     *
     * Getters and Setters
     */
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    
}
