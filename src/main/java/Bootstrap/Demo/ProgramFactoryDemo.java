/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import Bootstrap.Program;
import Bootstrap.ProgramFactory;

/**
 *
 * @author KH
 */
public class ProgramFactoryDemo extends ProgramFactory {

    private final String DEFAULT_PROGRAM = "FILE";
    
    @Override
    public Program getProgram() {
        return getProgram(DEFAULT_PROGRAM);
    }

    @Override
    public Program getProgram(String program) {
        Program p;
        if("File".equalsIgnoreCase(program)){
            p = new ProgramFile();
        }
        else{
            throw new RuntimeException("Program "+program+" not recognized!");
        }
        p.init();
        return p;
    }
    
    
}
