package vn.aptech.mail.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import vn.aptech.mail.Entities.Attachs;
import vn.aptech.mail.Entities.Mail;
import vn.aptech.mail.utils.HibernateUtil;

public class Attachs_DAO {

	private static Attachs_DAO instance;

    public static Attachs_DAO getInstance() {
        if (instance == null) {
            instance = new Attachs_DAO();
        }
        return instance;
    }

    private Attachs_DAO() {
		// TODO Auto-generated constructor stub
	}
    
    public List<Attachs> findAll() {
		List<Attachs> listAttachs = new ArrayList<Attachs>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Attachs");
			listAttachs = query.list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listAttachs;
	}
    
    public List<Attachs> findAttachsByMailId(int mailId) {
		List<Attachs> listAttachs = new ArrayList<Attachs>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Attachs a WHERE a.MailId = ? ");
			query.setParameter(0, mailId);
			
			listAttachs = query.list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listAttachs;
	}
    
    public boolean insertAttachs(Attachs attach) {
		boolean insertResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.save(attach);
			insertResult = true;
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
			insertResult = false;
		}finally{
			session.close();
		}
		return insertResult;
	}
	
	public boolean updateAttachs(Attachs attach) {
		boolean updateResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(attach);
			updateResult = true;
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
			updateResult = false;
		}finally{
			session.close();
		}
		return updateResult;
	}
	
}
