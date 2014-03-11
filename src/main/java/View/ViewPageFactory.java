/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Bootstrap.Demo.ViewPageFactoryDemo;

/**
 *
 * @author KH
 */
public abstract class ViewPageFactory {
    
    public static ViewPageFactory getViewPageFactory(){
        return new ViewPageFactoryDemo();
    }
    
    public abstract ViewPage getViewPage();
    
    public abstract ViewPage getViewPage(String name);
}
