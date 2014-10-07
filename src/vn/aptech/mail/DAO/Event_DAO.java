package vn.aptech.mail.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import vn.aptech.mail.Entities.Events;
import vn.aptech.mail.utils.HibernateUtil;

public class Event_DAO {
	
	private static Event_DAO instance;

    public static Event_DAO getInstance() {
        if (instance == null) {
            instance = new Event_DAO();
        }
        return instance;
    }

    private Event_DAO() {
		// TODO Auto-generated constructor stub
	}

	public List<Events> findAll() {
		List<Events> listEvent = new ArrayList<Events>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Events");
			listEvent = query.list();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return listEvent;
	}
	
	public Events findEventById(int eventId) {
		Events event = new Events();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("FROM Events e WHERE e.eventId = :eventId ");
			query.setParameter("eventId", eventId);
			
			event = (Events) query.uniqueResult();
			
			session.getTransaction().commit();
		}catch( Exception ex ){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return event;
	}
	
	public boolean insertEvent(Events event) {
		boolean insertResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.save(event);
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
	
	public boolean updateEvent(Events event) {
		boolean updateResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(event);
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
	
	public boolean deleteEvent(Events event) {
		boolean deleteResult = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			session.delete(event);
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
