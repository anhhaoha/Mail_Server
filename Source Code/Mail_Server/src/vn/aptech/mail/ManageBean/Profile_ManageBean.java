package vn.aptech.mail.ManageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import vn.aptech.mail.DAO.Profile_DAO;
import vn.aptech.mail.Entities.Mail;
import vn.aptech.mail.Entities.Profiles;
import vn.aptech.mail.utils.HttpUtils;

@ManagedBean(name = "Profile")
@SessionScoped
public class Profile_ManageBean {
	private Profiles selectedP;
	private String className;
	private List<Profiles> pro;
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	public Profile_ManageBean() {
		super();
		pro=new ArrayList<Profiles>();
	}
	
	
	public Map<Long, Boolean> getChecked() {
		return checked;
	}


	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}


	public List<Profiles> getLsProfiles() {
		List<Profiles> ls = Profile_DAO.getInstance().fidAllProfile();
		return ls;
	}
	public List<Profiles> getClassProfiles() {
		List<Profiles> ls = Profile_DAO.getInstance().fidProfileClass();
		return ls;
	}
	public Profiles getSelectedP() {
		return selectedP;
	}

	public void setSelectedP(Profiles selectedP) {
		this.selectedP = selectedP;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public List<Profiles> getPro() {
		return pro;
	}
	public void setPro(List<Profiles> pro) {
		this.pro = pro;
	}
	public String showStudent()
	{
		
		pro=Profile_DAO.getInstance().fidStudentClass(className);
		
		return null;
		
	}
	
	public String getSendMail()
	{
		List<Profiles> list=new ArrayList<Profiles>();
		
		for(Profiles profile:pro)
		{
			if(checked.get(profile.getAccountId()))
			{
				list.add(profile);
			}
		}
		HttpUtils.putToSession("listProfile", list);
		System.out.println(list.size());
		return "SendMailClass";
	}
	
}
