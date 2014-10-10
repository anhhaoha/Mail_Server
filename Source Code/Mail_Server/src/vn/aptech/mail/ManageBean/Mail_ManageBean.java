package vn.aptech.mail.ManageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import vn.aptech.mail.DAO.Mail_DAO;
import vn.aptech.mail.Entities.Mail;

@ManagedBean(name="mailBean")
public class Mail_ManageBean {
	
	private Mail mail;
	private List<Mail> listMailInbox;
	private Long countMailReceived;
	private RepeatPaginator paginatorInbox;
	private RepeatPaginator paginatorSendMail;
	
	
	public RepeatPaginator getPaginatorSendMail() {
		return paginatorSendMail;
	}
	public Long getCountMailReceived() {
		return countMailReceived;
	}
	public List<Mail> getListMail() {
		return listMailInbox;
	}
	public void setListMail(List<Mail> listMail) {
		this.listMailInbox = listMail;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	public RepeatPaginator getPaginator() {
		return paginatorInbox;
	}

	public Mail_ManageBean() {
		// TODO Auto-generated constructor stub	
		listMailInbox = Mail_DAO.getInstance().findMailByAccountReceiveId("LAV35233");	
		paginatorInbox = new RepeatPaginator(listMailInbox);
		
		
		//count mail
		countMailReceived = Mail_DAO.getInstance().countMailReceived("LAV35233");
			
	}
	
		
	public List<Mail> getViewMailInBox(){		
		return paginatorInbox.getModel();
	}

	public List<Mail> getViewSendMail() {
		List<Mail> listSendMail = Mail_DAO.getInstance().findMailByAccountSendId("LAV35233");
		paginatorSendMail = new RepeatPaginator(listSendMail);
		return paginatorSendMail.getModel();
	}
	
	public Mail getViewMailDetail() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int mailId = 0;
		if(map.get("id") != null){
			mailId = Integer.parseInt(map.get("id"));
		}		
		mail = Mail_DAO.getInstance().findMailById(mailId);
		if(mail.isStatus() == false) {
			mail.setStatus(true);
			Mail_DAO.getInstance().updateMail(mail);
		}
		return mail;
	}
	
}
