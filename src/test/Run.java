package test;

import vn.aptech.mail.DAO.Event_DAO;
import vn.aptech.mail.Entities.Events;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Events e = Event_DAO.getInstance().findEventById(1);
		System.out.println(e.getTitle());
	}

}
