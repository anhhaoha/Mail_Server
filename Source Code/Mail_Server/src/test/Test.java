package test;

import vn.aptech.mail.DAO.Mail_DAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Mail_DAO.getInstance().findMailByAccountReceiveId("LAV35233"));
	}

}
