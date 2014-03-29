/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import SECA2.File.FileEntity;
import SECA2.File.FileSequence;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author vincent.a.lee
 */
public class HibernateUtil implements Serializable {
    
    public Session getSession(){
        Configuration cfg = createFullConfig();
        cfg.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder ().applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        
        return session;
    }
    
    public Session getSession(List<Class> entities){
        Configuration cfg = createFullConfig();
        cfg.configure();
        
        for(Object e:entities){
            cfg.addAnnotatedClass(e.getClass());
        }
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder ().applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        
        return session;
    }
    
    public Session getSession(Object entity){
        Configuration cfg = createFullConfig();
        cfg.configure().addAnnotatedClass(entity.getClass());
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder ().applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        
        return session;
    }
    
    private Configuration createFullConfig(){
        Configuration cfg = createPartialConfig();
        cfg.setProperty("hibernate.current_session_context_class", "thread");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/seca2");
        cfg.setProperty("hibernate.connection.username","seca2");
        cfg.setProperty("hibernate.connection.password","seca2");
        cfg.setProperty("hibernate.c3p0.min_size","5");
        cfg.setProperty("hibernate.c3p0.max_size","20");
        cfg.setProperty("hibernate.c3p0.timeout","30");
        cfg.setProperty("hibernate.c3p0.max_statements","100");
        cfg.setProperty("hibernate.hbm2ddl.auto","update");
        cfg.setProperty("hibernate.archive.autodetection","class, hbm");
        cfg.setProperty("hibernate.show_sql","true");
        cfg.setProperty("hibernate.connection.autocommit","false");
        
        //cfg.setProperty("hibernate.session_factory_name","hi");
        
        return cfg;
    }
    
    private Configuration createPartialConfig(){
        Configuration cfg = new Configuration();
        return cfg;
    }
}
