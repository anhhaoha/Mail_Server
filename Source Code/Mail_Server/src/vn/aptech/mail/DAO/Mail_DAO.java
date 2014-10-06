package vn.aptech.mail.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import vn.aptech.mail.Entities.Mail;
import vn.aptech.mail.utils.HibernateUtil;

public class Mail_DAO {
	
	private static Mail_DAO instance;

    public static Mail_DAO getInstance() {
        if (instance == null) {
            instance = new Mail_DAO();
        }
        return instance;
    }

    private Mail_DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Mail> findAll() {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Mail");
			listMail = query.list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listMail;
	}
	
	public Mail findMailById(int mailId) {
		Mail mail = new Mail();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Mail m WHERE m.MailId = ? ");
			query.setParameter(0, mailId);
			
			mail = (Mail) query.uniqueResult();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return mail;
	}
	
	public List<Mail> findMailByAccountSendId(String accountSendId) {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Mail m WHERE m.AccountSendId = ? ");
			query.setParameter(0, accountSendId);
			
			listMail = query.list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listMail;
	}
	
	public List<Mail> findMailByAccountReceiveId(String accountReceiveId) {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Mail m WHERE m.AccountReceiveId = ? ");
			query.setParameter(0, accountReceiveId);
			
			listMail = query.list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listMail;
	}
	
	public boolean insertMail(Mail mail) {
		boolean insertResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.save(mail);
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
	
	public boolean updateMail(Mail mail) {
		boolean updateResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(mail);
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
	
	public boolean deleteMail(Mail mail) {
		boolean deleteResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.delete(mail);
			deleteResult = true;
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
			deleteResult = false;
		}finally{
			session.close();
		}
		return deleteResult;
	}

}
