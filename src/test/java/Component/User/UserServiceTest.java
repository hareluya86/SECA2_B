/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Component.User;

import Entity.User.UserEntity;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KH
 */
public class UserServiceTest {
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class UserService.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String username = "";
        String password = "";
        Map properties = new HashMap(); 
        properties.put(EJBContainer.MODULES, 
                new File("C:\\Users\\KH\\Documents\\GitHub\\SECA2\\target\\SECA2-1.0-SNAPSHOT.war"));
        properties.put("org.glassfish.ejb.embedded.glassfish.instance.root",
               "target/classes/instance-root");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        UserEntity expResult = null;
        UserEntity result = instance.login(username, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() throws Exception {
        System.out.println("createUser");
        String username = "";
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        UserEntity expResult = null;
        UserEntity result = instance.registerNewUser(username, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePassword method, of class UserService.
     */
    @Test
    public void testChangePassword() throws Exception {
        System.out.println("changePassword");
        String username = "";
        String oldPassword = "";
        String newPassword = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        UserEntity expResult = null;
        UserEntity result = instance.changePassword(username, oldPassword, newPassword);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
