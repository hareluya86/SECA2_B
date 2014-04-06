/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.User;

import Entity.User.UserEntity;
import Data.HibernateUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

/**
 * A Stateless service for User operations
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
    
    public UserEntity createUser(String username, String password){
        
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
    private boolean checkIfUserExist(String username, Session session){

        List results = session.createCriteria(UserEntity.class)
                .add(Restrictions.eq("USERNAME", username))
                .list();
        if(results.size() < 1 ) return false;//no such user
        
        return true;
    }
}
