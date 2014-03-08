/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.tabmenu.TabMenu;
import org.primefaces.component.tabmenu.TabMenuRenderer;
import org.primefaces.model.menu.MenuItem;

/**
 *
 * @author vincent.a.lee
 */
@ManagedBean(name="navigator")
public class NavigatorBean {
    
    private List<String> moduleList;
    private TabMenu menu;

    public NavigatorBean() {
        moduleList = new ArrayList<String>();
        moduleList.add("File");
        moduleList.add("User");
        moduleList.add("MySetting");
        
        menu = new TabMenu();
        
        for(String module:moduleList){
            UIMenuItem item = new UIMenuItem();item.setValue(module); item.setUrl("#"); menu.getChildren().add(item);
        }
    }
 
    public List<String> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<String> moduleList) {
        this.moduleList = moduleList;
    }

    public TabMenu getMenu() {
        return menu;
    }

    public void setMenu(TabMenu menu) {
        this.menu = menu;
    }
    
    
    
    
}
