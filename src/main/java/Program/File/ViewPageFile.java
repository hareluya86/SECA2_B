/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import View.ViewPage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KH
 */
public class ViewPageFile implements ViewPage {

    private String root = "/programs/file/layout.xhtml";
    private String name = "file";
    private List<String> components = new ArrayList<String>();
    
    @Override
    public void init(){
        components.add("search");
        components.add("subprogram");
    }
    
    @Override
    public String getRoot() {
        return root;
    }

    @Override
    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getComponents() {
        return components;
    }

    @Override
    public void setComponents(List<String> components) {
        this.components = components;
    }
    
}
