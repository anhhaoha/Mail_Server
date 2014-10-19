package vn.aptech.mail.ManageBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




import vn.aptech.mail.DAO.Roles_DAO;
import vn.aptech.mail.DAO.User_DAO;
import vn.aptech.mail.Entities.Roles;
import vn.aptech.mail.Entities.Users;
import vn.aptech.mail.utils.HttpUtils;

@ManagedBean(name = "userBean")
@RequestScoped
public class User_ManagerBean {

	Users user;
	String oldPass;
	String enterPass;
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public User_ManagerBean() {
		super();
		user = new Users();
		

	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getEnterPass() {
		return enterPass;
	}

	public void setEnterPass(String enterPass) {
		this.enterPass = enterPass;
	}

	public String login_Student() {
		String msg;

		if (User_DAO.getInstance().login_Student(user) != null)

		{

			user = User_DAO.getInstance().login_Student(user);
			int roleId = user.getRoles().getRoleId();
			HttpUtils.putToSession("users", user);

			if (roleId == 2) {

				return "/template/Staff/Inbox";

			} else {
				if (roleId == 3)

					return "/template/Student/Inbox"; 	

			}

			if (roleId == 1) {

				return "/template/Admin/Inbox";
			}

			msg = "Success!";
		} else {
			msg = "Username or Password fail!!";

		}
		FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, massage);
		return null;

	}

	public String creatUser() {
		String msg;
		if (User_DAO.getInstance().insert_User(user)) {
			msg = "Created Successfully!";
		} else {
			msg = "Error. Please check again!";

		}
		FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, massage);
		return null;
	}

	public String update_User() {
		String msg;
		
//		user=User_DAO.getInstance().update_User(user);
//		user =(Users) HttpUtils.getFromSession("users");
		Users u=new Users();
		
		u =(Users) HttpUtils.getFromSession("users");
		
		
		
		

		if(oldPass.equals(u.getPassword()))
		{
			if(enterPass.equals(user.getPassword()))
			{
				u.setPassword(user.getPassword());
				if (User_DAO.getInstance().update_User(u)) {
					msg = "Created Successfully!";
				} else {
					msg = "Error. Please check again!";

				}
			}
			else{
				msg = "Error.Enter password does not match!";
			}
		}
		else {
			msg = "Error. Mat khau cu khong dung";
		}
		
		FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, massage);

		return null;

	}

	public String changeProfile() {
		String msg;
		
//		user=User_DAO.getInstance().update_User(user);
//		user =(Users) HttpUtils.getFromSession("users");
		Users u=new Users();
		
		u =(Users) HttpUtils.getFromSession("users");
		
		
		u.setDisplayName(user.getDisplayName());
		u.setEmail(user.getEmail());
		

		if (User_DAO.getInstance().update_User(u)) {
			msg = "Created Successfully!";
		} else {
			msg = "Error. Please check again!";

		}
		FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, massage);

		return null;

	}
	
	
	public String logout()
	{
		HttpUtils.deleteFromSession("users");
		System.out.println(HttpUtils.getFromSession("users"));
		return "/template/Home/Index";
		
	}
	
	@FacesConverter(forClass = Roles.class)
	public class UserConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext, UIComponent arg1,
				String key) {
			return Roles_DAO.getInstance().finRoleById(key);
		}

		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1,
				Object value) {
			// TODO Auto-generated method stub
			Roles role = (Roles) value;
			return "" + role.getRoleId();
		}
	}
}
