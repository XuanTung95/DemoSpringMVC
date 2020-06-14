package demo.controllers.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	  private static SessionFactory sessionFactory;
	  
	  // Hibernate 5:
	  private static SessionFactory buildSessionFactory() {
		  long start = System.currentTimeMillis();
		  SessionFactory newSession = null;
	      try {
	          // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
	          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
	                  .configure("hibernate.cfg.xml").build();
	 
	          // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
	          Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	 
	          newSession = metadata.getSessionFactoryBuilder().build();
	      } catch (Throwable ex) {
	      
	          System.err.println("Initial SessionFactory creation failed." + ex);
	          throw new ExceptionInInitializerError(ex);
	      }
	      long end = System.currentTimeMillis();
	      System.out.println("Created SessionFactory time: "+(end - start));

	      return newSession;
	  }
	 
	  public SessionFactory getSessionFactory() {
		  if(sessionFactory == null) {
			  sessionFactory = buildSessionFactory();
		  }
	      return sessionFactory;
	  }
	 
	  public void shutdown() {
	      // Giải phóng cache và Connection Pools.
	      getSessionFactory().close();
	      sessionFactory = null;
	  }
}
