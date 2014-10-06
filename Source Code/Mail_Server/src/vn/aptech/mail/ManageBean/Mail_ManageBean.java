package vn.aptech.mail.ManageBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import vn.aptech.mail.DAO.Mail_DAO;
import vn.aptech.mail.Entities.Mail;

@ManagedBean(name="mailBean")
public class Mail_ManageBean {
	
	private List<Mail> listMail;
	
	public List<Mail> getListMail() {
		return listMail;
	}
	public void setListMail(List<Mail> listMail) {
		this.listMail = listMail;
	}

	public List<Mail> viewMailInBox(){
		
		listMail = new ArrayList<Mail>();
		String accountReceiveId = "LAV35233";
		return listMail = Mail_DAO.getInstance().findMailByAccountReceiveId(accountReceiveId );
		
	}
	
}
