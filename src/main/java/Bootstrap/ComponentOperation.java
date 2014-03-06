/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap;

/**
 *
 * @author KH
 * @param <C1>
 * @param <C2>
 */
public interface ComponentOperation {
    
    public void load(Component thisDependsOnThat, Component thatDependsOnThis);
}
