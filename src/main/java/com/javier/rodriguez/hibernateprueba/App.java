package com.javier.rodriguez.hibernateprueba;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App {
	
	static SessionFactory sessionFactoryObj;
	static Session sessionObject;
	
	private static SessionFactory buildSessionFactory() {
		Configuration confObj = new Configuration();
		confObj.configure("hibernate.cfg.xml");
		confObj.addAnnotatedClass(Actores.class);
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(confObj.getProperties()).build();
		return sessionFactoryObj = confObj.buildSessionFactory(serviceRegistryObj);
		
	}
	
	
    public static void main( String[] args )
    {
    	
    	try {
    		sessionObject= buildSessionFactory().openSession();
    		sessionObject.beginTransaction();
    		
    		//Recuperar un sólo actor.
    		Actores actor = (Actores) sessionObject.get(Actores.class, 1);
    		System.out.println(actor.getFirst_name());
    		sessionObject.delete(actor);
    		
    		//Listado
//    		List<Actores> actoresList = sessionObject.createQuery("from Actores").getResultList();
//    		for (Actores actor: actoresList)
//    		{
//    		 System.out.println(actor.getFirst_name());
//    		}
    		
    		//Crear actores.
//    		Actores actoresObj = new Actores();
//    		actoresObj.setFirst_name("Xavi");
//    		actoresObj.setLast_name("Rodriguez");
//    		sessionObject.save(actoresObj);
    		sessionObject.getTransaction().commit();
    	
    	}catch(Exception sqlException) {
    		if(null!=sessionObject.getTransaction()) sessionObject.getTransaction().rollback();
    		sqlException.printStackTrace();
    	}finally {
    		if( sessionObject !=null) sessionObject.close();
    	}
        
    	
    	System.out.println( "Hello World!" );
    }
}
