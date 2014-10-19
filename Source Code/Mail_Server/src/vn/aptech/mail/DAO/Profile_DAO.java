package vn.aptech.mail.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import vn.aptech.mail.Entities.Profiles;
import vn.aptech.mail.Entities.Users;
import vn.aptech.mail.utils.HibernateUtil;

public class Profile_DAO {

	
	
	private static Profile_DAO instance;

	private Profile_DAO()
	{
		
	}

	public static Profile_DAO getInstance() {
		
		if (instance == null) {
			instance = new Profile_DAO();
		}
		
		return instance;
	}
	
	public List<Profiles> fidAllProfile()
	{
		List<Profiles> list=new ArrayList<Profiles>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();

		Query query = session.createQuery(" from Profiles");
		list = query.list();
		session.getTransaction().commit();
		}catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().commit();

			} finally {
			session.close();
			}
		return list;
	
	}
	
	
	public List<Profiles> fidStudentClass(String clas)
	{
		List<Profiles> list=new ArrayList<Profiles>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();

		Query query = session.createQuery(" from Profiles where class_=:clas");
		query.setParameter("clas", clas);
		list = query.list();
		session.getTransaction().commit();
		}catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().commit();

			} finally {
			session.close();
			}
		return list;
	
	}
	
	
	public List<Profiles> fidProfileClass()
	{
		List<Profiles> list=new ArrayList<Profiles>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		session.beginTransaction();

		Query query = session.createQuery(" select class_ from Profiles ");
		list = query.list();
		session.getTransaction().commit();
		}catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().commit();

			} finally {
			session.close();
			}
		return list;
	
	}
	
	

	public static boolean insert_Profile(Profiles p) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		session.save(p);
		session.getTransaction().commit();
		return true;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return false;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	}
	
	public static boolean update_User(Profiles p) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		session.update(p);
		session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return true;
	}
	public  Profiles finUserId(String AccountID)
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		Query query = session.createQuery(" from Profiles where accountId =: ID ");
		query.setParameter("ID", AccountID);
		Profiles pro = (Profiles) query.uniqueResult();
		session.getTransaction().commit();
		return pro;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;
		

	}

}
