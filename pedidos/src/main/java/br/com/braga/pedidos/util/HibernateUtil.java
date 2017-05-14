package br.com.braga.pedidos.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			StandardServiceRegistry standardServiceRegistry = 
					new StandardServiceRegistryBuilder().configure().build();
			Metadata metadata = 
					new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			
		} catch (Throwable th) {
			System.err.println("Erro na criação do SessionFactory" + th);
			throw new ExceptionInInitializerError(th);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
