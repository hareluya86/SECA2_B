/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

import Bootstrap.Demo.ProgramFactoryDemo;

/**
 *
 * @author KH
 */
public abstract class ProgramFactory {
    
    public static ProgramFactory getProgramFactory(){
        return new ProgramFactoryDemo();
    }
    
    public abstract Program getProgram();
    
    public abstract Program getProgram(String program);
    
}
