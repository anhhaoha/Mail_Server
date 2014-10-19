package vn.aptech.mail.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;

import vn.aptech.mail.utils.HibernateUtil;
import vn.aptech.mail.Entities.Users;

public class User_DAO {

	private static User_DAO instance;

	public static User_DAO getInstance() {
		if (instance == null) {
			instance = new User_DAO();
		}
		return instance;
	}

	private User_DAO() {

	}

	

	public  Users login_Student(Users u) {
		  Users user = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		  try {
			  session.beginTransaction();
		Query query = session.createQuery("from Users where username =:name and password =:pass");
		query.setParameter("name", u.getUsername());
		query.setParameter("pass", u.getPassword());

		user = (Users) query.uniqueResult();
		session.getTransaction().commit();
	} catch (Exception ex) {
        ex.printStackTrace();
        session.getTransaction().commit();
    } finally {
        session.close();
    }
		return user;
	}

	public List<Users> findAllStudent() {
		List<Users> list = new ArrayList<Users>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{

		session.beginTransaction();

		Query query = session.createQuery(" from Users ");
		list = query.list();
		session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;

	}

	public List<Users> findAllStaff() {
		List<Users> list = new ArrayList<Users>();

		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{

		session.beginTransaction();

		Query query = session.createQuery(" from Users");
		list = query.list();
		session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}

		return list;

	}

	public  boolean insert_User(Users u) {
		boolean insertResult;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();
		
		u.setStatus(false);
		session.save(u);
		insertResult=true;
		session.getTransaction().commit();
	
		
		}catch( Exception ex ){
			ex.printStackTrace();
			insertResult=false;
		}finally{
			session.close();
		}
		return false;

	}
	
	public boolean update_User(Users u) {
		boolean updateResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
		session.beginTransaction();

		session.update(u);
		updateResult=true;
		session.getTransaction().commit();
		return true;
		}catch( Exception ex ){
			ex.printStackTrace();
			updateResult=false;
		}finally{
			session.close();
		}
		return false;
	}
	
	public  Users findById(String AccountID)
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		Query query = session.createQuery(" from Users where accountId=:ID ");
		query.setParameter("ID", AccountID);
		
		Users u = (Users) query.uniqueResult();
		
		session.getTransaction().commit();
		return u;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;

	}
	
	public  Users findIdByUser(String usernames)
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		Query query = session.createQuery(" from Users where username=:names ");
		query.setParameter("names", usernames);
		
		Users u = (Users) query.uniqueResult();
		
		session.getTransaction().commit();
		return u;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;

	}
	


}
