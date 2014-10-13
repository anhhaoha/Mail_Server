package vn.aptech.mail.ManageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import org.hibernate.tool.hbmlint.Detector;

import vn.aptech.mail.DAO.Mail_DAO;
import vn.aptech.mail.Entities.Mail;

@ManagedBean(name="mailBean")
public class Mail_ManageBean {
	
	private Mail mail;
	private List<Mail> listMailInbox;
	private Long countMailReceived;
	private RepeatPaginator paginator;
	private RepeatPaginator paginatorSendMail;
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	
	public Map<Long, Boolean> getChecked() {
		return checked;
	}
	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}
	public RepeatPaginator getPaginatorSendMail() {
		return paginatorSendMail;
	}
	public Long getCountMailReceived() {
		return countMailReceived;
	}
	public void setCountMailReceived(Long countMailReceived) {
		this.countMailReceived = countMailReceived;
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
		return paginator;
	}
	public List<Mail> getListMailInbox() {
		return listMailInbox;
	}

	public Mail_ManageBean() {
		// TODO Auto-generated constructor stub	
		listMailInbox = Mail_DAO.getInstance().findMailByAccountReceiveId("FAV63625");
		paginator = new RepeatPaginator(listMailInbox);
		//count mail
		countMailReceived = Mail_DAO.getInstance().countMailReceived("FAV63625");
	}
	
		
	public List<Mail> getViewMailInBox(){	
		
		return paginator.getModel();
	}
	public String getNext(){
		paginator.next();
		
		return "Inbox";
	}
	public String getPrev(){
		paginator.prev();
		
		return "Inbox";
	}

	public List<Mail> getViewSendMail() {
		List<Mail> listSendMail = Mail_DAO.getInstance().findMailByAccountSendId("FAV63625");
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
	
	
	public String getDeleteMail() {	
		List<Mail> checkedMail = new ArrayList<Mail>();

        for (Mail mail : listMailInbox) {
            if (checked.get(mail.getMailId())) {
            	checkedMail.add(mail);
            }
        }

        checked.clear();
        boolean deleteMail = false;
        for (Mail mail : checkedMail) {
			deleteMail = Mail_DAO.getInstance().deleteMail(mail);
		}
        if(deleteMail = true){
        	System.out.println("success");
        }
		
		return "Inbox";
	}
	
//	public String getAbc() {
//		System.out.println(mailSelected.length);
//		return null;
//	}
	
}
