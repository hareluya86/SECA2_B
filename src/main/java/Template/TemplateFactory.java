/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Template;

import Template.Demo.TemplateFactoryDemo;

/**
 *
 * @author KH
 */
public abstract class TemplateFactory {
    
    protected String DEFAULT_TEMPLATE = "mytemplate";
    
    public static TemplateFactory getTemplateFactory(){
        return new TemplateFactoryDemo();
    }
    
    public abstract Template getTemplate();
    
    public abstract Template getTemplate(String template);
}
