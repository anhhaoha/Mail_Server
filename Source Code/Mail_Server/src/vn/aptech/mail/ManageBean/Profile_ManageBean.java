package vn.aptech.mail.ManageBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import vn.aptech.mail.DAO.Profile_DAO;
import vn.aptech.mail.Entities.Profiles;

@ManagedBean(name = "Profile")
@SessionScoped
public class Profile_ManageBean {
	public List<Profiles> getLsProfiles() {
		List<Profiles> ls = Profile_DAO.getInstance().fidAllProfile();
		return ls;
	}
}
