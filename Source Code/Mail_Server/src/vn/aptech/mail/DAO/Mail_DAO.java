package vn.aptech.mail.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listMail;
	}

	public Mail findMailById(int mailId) {
		Mail mail = new Mail();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("FROM Mail m WHERE m.mailId = :mailId ");
			query.setParameter("mailId", mailId);

			mail = (Mail) query.uniqueResult();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return mail;
	}

	public List<Mail> findMailByAccountSendId(String accountSendId) {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("FROM Mail m WHERE m.usersByAccountSendId.accountId = :accountSendId ORDER BY m.sendDate DESC ");
			query.setParameter("accountSendId", accountSendId);

			listMail = query.list();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listMail;
	}

	public List<Mail> findMailByAccountReceiveId(String accountReceiveId) {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("FROM Mail m WHERE m.usersByAccountReceiveId.accountId = :accountReceiveId ORDER BY m.sendDate DESC ");
			query.setParameter("accountReceiveId", accountReceiveId);

			listMail = query.list();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listMail;
	}

	public List<Mail> searchMailByUsername(String username) {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("FROM Mail m WHERE m.usersByAccountReceiveId.username = :username OR m.usersByAccountSendId.username = :username ORDER BY m.sendDate DESC ");
			query.setParameter("username", username);

			listMail = query.list();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listMail;
	}

	public Long countMailReceived(String accountReceiveId) {
		Long result = (long) 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("SELECT COUNT(m.mailId) FROM Mail m WHERE m.usersByAccountReceiveId.accountId = :accountReceiveId AND m.status = 0 ");
			query.setParameter("accountReceiveId", accountReceiveId);

			Long a = (Long) query.uniqueResult();
			result = a;

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public boolean insertMail(Mail mail) {
		boolean insertResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
//			mail.setReadDate(date);
			mail.setSendDate(date);
			mail.setStatus(false);
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
		} catch (Exception ex) {
			ex.printStackTrace();
			deleteResult = false;
		} finally {
			session.close();
		}
		return deleteResult;
	}
	public List<Mail> findMailByIdLast(int number) {
		List<Mail> listMail = new ArrayList<Mail>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Mail m ORDER BY m.mailId DESC  ");

			listMail = query.setMaxResults(number).list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listMail;
	}

}
