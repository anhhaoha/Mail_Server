package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vn.aptech.mail.DAO.Mail_DAO;
import vn.aptech.mail.Entities.Mail;
import vn.aptech.mail.Entities.Users;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long a = Mail_DAO.getInstance().countMailReceived("LAV35233");
		System.out.println(a);
		
	}

}
