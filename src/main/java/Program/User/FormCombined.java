/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program.User;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TabCloseEvent;

/**
 *
 * @author vincent.a.lee
 */
@Named("FormCombined")
@ConversationScoped
public class FormCombined implements Serializable{
    
    @Inject private FormUserCreate userCreate;
    @Inject private FormUserTypeMaintain userTypeMaintain;
    @Inject private FormUserTypeCreate userTypeCreate;
    
    @Inject private Conversation conversation;
    
    @PostConstruct
    public void init(){
        
    }
    
    public void onTabClose(TabCloseEvent event){
        conversation.end();
    }
    
    public FormUserCreate getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(FormUserCreate userCreate) {
        this.userCreate = userCreate;
    }

    public FormUserTypeMaintain getUserTypeMaintain() {
        return userTypeMaintain;
    }

    public void setUserTypeMaintain(FormUserTypeMaintain userTypeMaintain) {
        this.userTypeMaintain = userTypeMaintain;
    }

    public FormUserTypeCreate getUserTypeCreate() {
        return userTypeCreate;
    }

    public void setUserTypeCreate(FormUserTypeCreate userTypeCreate) {
        this.userTypeCreate = userTypeCreate;
    }
}
