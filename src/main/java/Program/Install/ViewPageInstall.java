/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.Install;

import View.ViewPage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KH
 */
public class ViewPageInstall extends ViewPage {

    @Override
    public void init() {
        super.init();
        root = "/programs/install/install.xhtml";
        name = "Install";
        components = new ArrayList<String>();
    }
    
}
