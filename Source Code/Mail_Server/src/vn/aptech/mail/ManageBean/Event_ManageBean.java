package vn.aptech.mail.ManageBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import vn.aptech.mail.DAO.Event_DAO;
import vn.aptech.mail.Entities.Attachs;
import vn.aptech.mail.Entities.Events;
import vn.aptech.mail.utils.HttpUtils;

@ManagedBean(name="eventBean")
public class Event_ManageBean {
	
	private Events event;
	private Date date;
	private Part part;
	private String statusMessage;
	
	
	
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	
	
	public Event_ManageBean() {
		super();
		event=new Events();
	}

	public List<Events> getFindAll() {
		
		List<Events> listEvents = Event_DAO.getInstance().findAll();
		return listEvents;
		
	}
	
	public Events getEventById(){
		return null;
	}
	
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("***** partHeader: " + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		  return null;
	}
	
	

	public String uploadFile() throws IOException {

	
		String fileName = getFileName(part);
		System.out.println("***** fileName: " + fileName);

		String basePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "images" + "\\";
		System.out.println(basePath);
		
		File outputFilePath = new File(basePath + fileName);
		System.out.println(outputFilePath);

		// Copy uploaded file to destination path
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = part.getInputStream();
			outputStream = new FileOutputStream(outputFilePath);

			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			HttpUtils.putToSession("savePartname",getFileName(part) );
			statusMessage = "File upload successfull !!";
		} catch (IOException e) {
			e.printStackTrace();
			statusMessage = "File upload failed !!";
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return null; // return to same page
	}
	
	
	public String createEvent()
	{
		String msg;
		Attachs attachs =new Attachs();
		String parts=(String) HttpUtils.getFromSession("savePartname");
		event.setCreateDate(date);
		event.setPicture(parts);
		if(parts!=null)
		{
			if(Event_DAO.getInstance().insertEvent(event))
			{
				msg = "Created Successfully!";
			}else
			{
				msg = "fail!";
			}
		}else
		{
			msg="Please chose imaage";
		}
		
		  FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
	      FacesContext.getCurrentInstance().addMessage(null, massage);
		return null;
	}
	
}
