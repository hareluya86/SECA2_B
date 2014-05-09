/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.User;

import Entity.User.UserEntity;
import Component.Data.HibernateUtil;
import Entity.User.UserType;
import Program.Util.FacesMessenger;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

/**
 * A Stateless service for User operations.
 * 20140502 - this is probably the only necessary case where an EJB should be used
 * because the user database could be somewhere else.
 * 
 * @author KH
 */
@Stateless
public class UserService {
    
    private static final String HASH_KEY = "33150291203315029120";
    private static final int MAX_UNSUCCESS_ATTEMPTS = 3;
    
    @Inject private HibernateUtil hibernateUtil;
    
    /**
     * Returns the UserEntity object if authentication passes. If authentication
     * passes but UserEntity is locked, throw a UserAccountLockedException. If 
     * authentication fails, return null and let the client code handle.
     * <p>
     * 
     * @param username
     * @param password
     * @return
     * @throws UserAccountLockedException 
     */
    public UserEntity login(String username, String password) throws UserAccountLockedException{
        
        String secureHash = this.getPasswordHash(username, password, HASH_KEY);
        Session session = hibernateUtil.getSession();
        List results = session.createCriteria(UserEntity.class)
                .add(Restrictions.eq("USERNAME", username))
                .list();
        if(results.size() < 1 ) return null;//no such user
        
        UserEntity result = (UserEntity) results.get(0);
        if(secureHash.equals(result.getPASSWORD())){ //authentication passes
            if(!result.isUSER_LOCKED()){
                //reset unlock flag and counters
                result.setUNSUCCESSFUL_ATTEMPTS(0);
                result = (UserEntity) session.save(result);
                
            }else{
                throw new UserAccountLockedException(username);
            }
        }else{ //authentication fails
            //increment unsuccessful counter and set lock flag
            result.setLAST_UNSUCCESS_ATTEMPT((new DateTime()).toDate());
            result.setUNSUCCESSFUL_ATTEMPTS(result.getUNSUCCESSFUL_ATTEMPTS()+1);
            if(result.getUNSUCCESSFUL_ATTEMPTS()>=MAX_UNSUCCESS_ATTEMPTS){
                result.setUSER_LOCKED(true);
            }
            session.save(result);
            result = null;
        }
        return result;
    }
    
    public UserEntity registerNewUser(String username, String password) throws UserRegistrationException{
        /**
         * Validate existence of both username and passwords as you will also 
         * need to validate at the frontend forms.
         */
        if(username == null || username.isEmpty()){
            throw new UserRegistrationException("Username cannot be empty.");
        }
        if(password == null || password.isEmpty()){
            throw new UserRegistrationException("Password cannot be empty.");
        }
        
        /**
         * This is where container-managed transactions are useful! A method of 
         * a class calling another method of the same class with the same injected
         * instance of DAO.
         */
        if(this.checkIfUserExist(username)){ 
            throw new UserRegistrationException("Username already exist. Please choose a different name.");
        }
        
        UserEntity newUser = new UserEntity();
        newUser.setUSERNAME(username);
        newUser.setPASSWORD(this.getPasswordHash(username, password, HASH_KEY));
        
        Session session = hibernateUtil.getSession();
        newUser = (UserEntity) session.save(newUser);
        
        return newUser;
    }
    
    public UserEntity changePassword(String username, String oldPassword, String newPassword) throws UserAccountLockedException{
        Session session = hibernateUtil.getSession();
        UserEntity changeForUser = null;
        //authenticate old password first
        changeForUser = this.login(username, oldPassword);
        
        if(changeForUser == null){ //authentication failed
            return null;
        }
        String newHashedPassword = this.getPasswordHash(username, newPassword, HASH_KEY);
        changeForUser.setPASSWORD(newHashedPassword);
        changeForUser = (UserEntity) session.save(changeForUser);
        return changeForUser;
    }
    
    private String getPasswordHash(String username, String password, String exraHash){
        String secureHash = password.concat(username).concat(exraHash);
        MessageDigest md;
        byte[] hash;
        try {
            md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(secureHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException("Error encountered in login method.");
        }
        String hashedPassword = String.format("%032x", new BigInteger(hash));
        
        return hashedPassword;
    }
    
    /**
     * Returns true or false. Note: Do not return any UserEntity object without 
     * a password! Use sparingly.
     * <p>
     * @param username
     * @return 
     */
    public boolean checkIfUserExist(String username){
        Session session = hibernateUtil.getSession();
        List results = session.createCriteria(UserEntity.class)
                .add(Restrictions.eq("USERNAME", username))
                .list();
        if(results.size() < 1 ) return false;//no such user
        
        return true;
    }
    
    public boolean checkIfUserTypeExist(String usertype){
        Session session = hibernateUtil.getSession();
        List results = session.createCriteria(UserType.class)
                .add(Restrictions.eq("USERTYPE", usertype))
                .list();
        if(results.size() < 1 ) return false;//no such user type
        
        return true;
    }
    
    public List<UserType> getUserTypes(int firstResult, int maxResult){
        Session session = hibernateUtil.getSession();
        Criteria selectAll = session.createCriteria(UserType.class).setFirstResult(firstResult)
                .setMaxResults(maxResult);
        List<UserType> result = selectAll.list();
        
        return result;
    }
    
    public void createUserType(String usertype, String description) throws UserTypeException{
        if(usertype == null || usertype.isEmpty()){
            throw new UserTypeException("Usertype cannot be empty.");
        }
        if(this.checkIfUserTypeExist(usertype)){
            throw new UserTypeException("Usertype already exists.");
        }
        UserType newType = new UserType();
        newType.setUSERTYPE(usertype);
        newType.setDESCRIPTION(description);
        
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(newType);
        session.getTransaction().commit();
    }
    
    public void modifyUserType(UserType userType){
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(userType);
        session.getTransaction().commit();
    }
}
