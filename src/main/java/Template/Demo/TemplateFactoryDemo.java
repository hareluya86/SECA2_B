/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Template.Demo;

import Template.Template;
import Template.TemplateFactory;

/**
 *
 * @author KH
 */
public class TemplateFactoryDemo extends TemplateFactory{

    @Override
    public Template getTemplate() {
        return getTemplate(DEFAULT_TEMPLATE);
    }

    @Override
    public Template getTemplate(String template) {
        if(template.equalsIgnoreCase("mytemplate")){
            return new TemplateDemo();
        }
        else{
            throw new RuntimeException("No such template: "+template);
        }
    }
    
}
