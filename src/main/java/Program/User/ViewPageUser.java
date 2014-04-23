/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import View.ViewPage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KH
 */
public class ViewPageUser implements ViewPage{

    private String root;
    private String name;
    private List<String> components;
    
    @Override
    public void init() {
        root = "/programs/user/layout.xhtml";
        name = "User";
        components = new ArrayList<String>();
        components.add("search");
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
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<String> getComponents() {
        return this.getComponents();
    }

    @Override
    public void setComponents(List<String> components) {
        this.setComponents(components);
    }
    
}
