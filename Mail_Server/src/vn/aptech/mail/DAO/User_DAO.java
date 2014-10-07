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

	

	public static Users login_Student(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction ts = (Transaction) session.beginTransaction();

		Query query = session
				.createQuery("from Users where username = :name and password = :pass and RoleId=1003");
		query.setParameter("name", username);
		query.setParameter("pass", password);

		Users u = (Users) query.uniqueResult();
		session.getTransaction().commit();
		return u;

	}

	public List<Users> findAllStudent() {
		List<Users> list = new ArrayList<Users>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{

		session.beginTransaction();

		Query query = session.createQuery(" from Users where roles=3");
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

		Query query = session.createQuery(" from Users where roles=2");
		list = query.list();
		session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}

		return list;

	}

	public static boolean insert_User(Users u) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		session.save(u);
		session.getTransaction().commit();
		return true;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;

	}
	
	public static boolean update_User(Users u) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
		session.beginTransaction();

		session.update(u);
		session.getTransaction().commit();
		return true;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}
	
	public static boolean finUserId(String AccountID)
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		Query query = session.createQuery(" from Users where accountId =: ID ");
		query.setParameter("ID", AccountID);
		
		session.getTransaction().commit();
		return true;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;

	}
	


}
