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
public class ViewPageFile extends ViewPage {

    @Override
    public void init(){
        super.init();
        root = "/programs/file/layout.xhtml";
        name = "file";
        components.add("search");
        components.add("subprogram");
    }
}
