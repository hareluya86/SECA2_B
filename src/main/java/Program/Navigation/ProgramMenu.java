/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.Navigation;

import Bootstrap.Program;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author KH
 */
@Named("ProgramMenu")
@RequestScoped
public class ProgramMenu extends Program{

    @PostConstruct
    @Override
    public void init() {
        this.PROGRAM_NAME = "Menu";
        this.PROGRAM_XHTML = "/programs/menu/top_menu.xhtml";
    }
    
    
}
