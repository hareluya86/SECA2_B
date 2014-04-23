/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bootstrap.Demo;

import javax.servlet.ServletContext;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Invoke;
import org.ocpsoft.rewrite.el.El;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

/**
 *
 * @author vincent.a.lee
 */
public class MyConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext t) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/").to("/program/index.xhtml"))
                    .perform(Invoke.binding(El.retrievalMethod("bootstrap.loadView")))
                    .perform(Invoke.binding(El.retrievalMethod("bootstrap.loadTemplate")))
                .addRule(Join.path("/program/{program}/").to("/program/index.xhtml"))
                    .perform(Invoke.binding(El.retrievalMethod("bootstrap.loadView")))
                    .perform(Invoke.binding(El.retrievalMethod("bootstrap.loadTemplate")))
                    .where("program").bindsTo(El.property("bootstrap.program"))
                    ;
    }

    @Override
    public int priority() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
