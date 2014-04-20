/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.File;

import View.ViewPage;
import View.ViewPageType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KH
 */
@ViewPageType("ViewPageFile")
public class ViewPageFile implements ViewPage {

    private String root;
    private String name;
    private List<String> components;
    
    @Override
    public void init(){
        root = "/programs/file/layout.xhtml";
        name = "file";
        components = new ArrayList<String>();
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
