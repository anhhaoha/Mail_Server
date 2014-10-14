package vn.aptech.mail.DAO;

import org.hibernate.Query;
import org.hibernate.Session;

import vn.aptech.mail.Entities.Roles;
import vn.aptech.mail.Entities.Users;
import vn.aptech.mail.utils.HibernateUtil;

public class Roles_DAO {
		
	private static Roles_DAO instance;

	public static Roles_DAO getInstance() {
		if (instance == null) {
			instance = new Roles_DAO();
		}
		return instance;
	}
	
	public Roles finRoleById(String RoleId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();

		Query query = session.createQuery(" from Roles where roleId=:ID ");
		query.setParameter("ID", RoleId);
		
		Roles role = (Roles) query.uniqueResult();
		
		session.getTransaction().commit();
		
		return role;
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
	
}
