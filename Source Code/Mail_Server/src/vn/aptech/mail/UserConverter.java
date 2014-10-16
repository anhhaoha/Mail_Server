package vn.aptech.mail;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import vn.aptech.mail.DAO.User_DAO;
import vn.aptech.mail.Entities.Users;

	@FacesConverter(value="UserConverter")
	public class UserConverter implements Converter {
		
		public UserConverter() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object getAsObject(FacesContext facesContext, UIComponent arg1,
				String key) {
			return User_DAO.getInstance().findById(key);

		}

		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1,
				Object value) {
			// TODO Auto-generated method stub

			Users user = (Users) value;
			return user.getAccountId();
		}
}
