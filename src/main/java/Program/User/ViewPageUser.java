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
public class ViewPageUser extends ViewPage{

    @Override
    public void init() {
        super.init();
        root = "/programs/user/layout.xhtml";
        name = "User";
        components.add("search");
    }

}
