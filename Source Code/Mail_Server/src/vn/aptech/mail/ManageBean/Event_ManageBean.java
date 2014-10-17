package vn.aptech.mail.ManageBean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import vn.aptech.mail.DAO.Event_DAO;
import vn.aptech.mail.Entities.Events;

@ManagedBean(name="eventBean")
public class Event_ManageBean {
	
	public List<Events> getFindAll() {
		
		List<Events> listEvents = Event_DAO.getInstance().findAll();
		return listEvents;
		
	}
	
	public Events getEventById(){
		
		
		return null;
		
	}
}
