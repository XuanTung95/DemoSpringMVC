package demo.controllers.db;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import demo.models.User;

public class UserDb extends DataBaseUtils {
	
	public User getUser(String email) {
          long start = System.currentTimeMillis();
          List<User> users = null;
	      SessionFactory factory = getSessionFactory();
	      
	      Session session = factory.getCurrentSession();
	 
	      try {
	          // Tất cả các lệnh hành động với DB thông qua Hibernate
	          // đều phải nằm trong 1 giao dịch (Transaction)
	          session.getTransaction().begin();
	 
	          String sql = "FROM User as t WHERE t.email = ?1";
	 
	          // Tạo đối tượng Query.
	          Query<User> query = session.createQuery(sql);
	          query.setParameter(1, email);
	 
	          // Thực hiện truy vấn.
	          users = query.list();
	 
	          for (User usr : users) {
	              System.out.println("User: " + usr.toString());
	          }
	 
	          // Commit dữ liệu
	          session.getTransaction().commit();
	      } catch (Exception e) {
	          e.printStackTrace();
	          // Rollback trong trường hợp có lỗi xẩy ra.
	          session.getTransaction().rollback();
	      }
	      long end = System.currentTimeMillis();
	      System.out.println("Query time: "+(end-start));
	      
	      if(users==null || users.isEmpty()) {
	    	  return null;
	      } else if(users.size() != 1){
	    	  System.out.println("There are duplicate user: "+users.get(0).getEmail());
	    	  return null;
	      } else {
			return users.get(0);
		}
	}
	
	public boolean createUser(User newUser) {
		boolean ret = true;
		long start = System.currentTimeMillis();
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(newUser);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			ret = false;
		}
		long end = System.currentTimeMillis();
		System.out.println("Query time: "+(end-start));
		return ret;
	}
}
