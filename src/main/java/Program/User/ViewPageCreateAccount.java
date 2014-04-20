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
public class ViewPageCreateAccount implements ViewPage{

    private String root;
    private String name;
    private List<String> components;
    
    @Override
    public void init() {
        root = "/programs/user/create_account.xhtml";
        name = "CreateAccount";
        components = new ArrayList<String>();
        
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
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<String> getComponents() {
        return this.components;
    }

    @Override
    public void setComponents(List<String> components) {
        this.components = components;
    }
    
}
