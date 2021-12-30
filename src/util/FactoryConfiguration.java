package util;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties = new Properties();
       try{
           properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
       }catch (Exception e){
           System.out.println("cannot load properties file.");
       }
        sessionFactory = new Configuration().mergeProperties(properties).addAnnotatedClass(Customer.class).buildSessionFactory();

    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
