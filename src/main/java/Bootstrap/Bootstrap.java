/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;


/**
 * Factory class that initializes all application parameters and launch application 
 * components based on request parameters. 
 * 
 * @author KH
 */
public abstract class Bootstrap {
    
    public static Bootstrap bootstrap(){
        return new BootstrapDemo();
    }
}
