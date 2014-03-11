/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Program.File.ViewPageFile;
import View.ViewPage;
import View.ViewPageFactory;

/**
 *
 * @author KH
 */
public class ViewPageFactoryDemo extends ViewPageFactory {

    private final String DEFAULT_VIEWPAGE = "FILE";
    
    @Override
    public ViewPage getViewPage() {
        return this.getViewPage(DEFAULT_VIEWPAGE);
    }

    @Override
    public ViewPage getViewPage(String name) {
        ViewPage vp;
        if("File".equalsIgnoreCase(name)){
            vp = new ViewPageFile();
        }
        else{
            //return null; vp = null;
            throw new RuntimeException(this.getClass().getName()+": ViewPage "+name+ "not found.");
        }
        vp.init(); //container-managed post-construct method!
        
        return vp;
    }
    
}
