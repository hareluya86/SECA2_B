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
import org.primefaces.event.CloseEvent;

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
    
    private String cid;
    
    @PostConstruct
    public void init(){
        if(cid==null) cid="";
        if(conversation.isTransient()){
            String newCID = conversation.getId(); //debug
            if(conversation.getId() == null){
                conversation.begin();
                cid = conversation.getId();
            }
        }
        System.out.println("FormCombined init called: "+conversation.toString());       
    }
    
    public void onDialogClose(CloseEvent event){
        if(conversation != null && !conversation.isTransient())
            conversation.end();
        System.out.println("FormCombined close called: "+conversation.toString());
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

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    
    
}
