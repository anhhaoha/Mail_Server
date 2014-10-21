package test;

import java.util.ArrayList;
import java.util.List;

import vn.aptech.mail.DAO.Event_DAO;
import vn.aptech.mail.DAO.Mail_DAO;
import vn.aptech.mail.Entities.Events;
import vn.aptech.mail.Entities.Mail;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Mail> listMail=new ArrayList<Mail>();
		listMail= Mail_DAO.getInstance().searchMailByUsername("William");
		for(Mail m:listMail)
		{
			
			System.out.println(m.getMailId());
		}
	}

}
