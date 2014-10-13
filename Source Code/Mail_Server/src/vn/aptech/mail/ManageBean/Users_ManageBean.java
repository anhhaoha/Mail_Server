package vn.aptech.mail.ManageBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import vn.aptech.mail.DAO.User_DAO;
import vn.aptech.mail.Entities.Users;

@ManagedBean(name = "Users")
@SessionScoped
public class Users_ManageBean {
	public List<Users> getLsUser() {
		List list = User_DAO.getInstance().findAllStudent();
		return list;
	}
}
