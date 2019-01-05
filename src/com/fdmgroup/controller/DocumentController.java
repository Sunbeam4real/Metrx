package com.fdmgroup.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.daoJpa.ActivityDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.T_User;
@Controller
//session_attribute
@SessionAttributes("loggedinuser")
public class DocumentController {
	@RequestMapping(value="/upload_action", method = RequestMethod.POST)

	public ModelAndView upload (HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		
		//security check for enter web address directly:

		if (session.getAttribute("loggedinuser")==null) {
			mv.setViewName("index.jsp");
			return mv;
		}
		if (!(((T_User)session.getAttribute("loggedinuser")).getPrivileges().containsKey(Privilege.UploadDoc)) || ((T_User)session.getAttribute("loggedinuser")).getPrivileges().get(Privilege.UploadDoc)==0){
			mv.setViewName("home");
			return mv;
		}
		
		
		mv.setViewName("upload_doc.jsp");
		System.out.println("diudiu");
		String file_name = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			mv.addObject("error", "error_1");
			return mv;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List < FileItem > fields = upload.parseRequest(request);
			Iterator < FileItem > it = fields.iterator();
			if (!it.hasNext()) {
				mv.addObject("error", "error_2");
				return mv;
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if (file_name == null) {
						if (fileItem.getFieldName().equals("file_name")) {
							file_name = fileItem.getString();
						}
					}
				} else {
					if (fileItem.getSize() > 0) {
						System.out.println(fileItem.getName());
						System.out.println(fileItem.getName().matches(".*xlsx"));
					
						if (!fileItem.getName().matches(".*\\\\.*")) {
							System.out.println("fack");
							file_name="wrongB";
						}
						else if (!fileItem.getName().matches(".*xlsx")) {
							file_name="error";
						}
						else {
						
						FileInputStream fis = new FileInputStream(new File(fileItem.getName()));
						XSSFWorkbook wb = new XSSFWorkbook(fis);
						
						
						XSSFSheet sheet = wb.getSheetAt(0);
						
						Iterator<Row> rowIt = sheet.iterator();
						
						ActivityDAOImpl aDaoImpl =new ActivityDAOImpl();
						UserDAOImpl uDaoImpl = new UserDAOImpl();
						 
						ArrayList<Activity> acList = new ArrayList<Activity>();
						String[] temp = new String[3];
						int temp_index = 0;
						Row row = rowIt.next();
						while(rowIt.hasNext()) {
							row = rowIt.next();
							
							Iterator<Cell> cellIt = row.cellIterator();
							while (cellIt.hasNext()) {
								Cell cell = cellIt.next();
								temp[temp_index] = cell.toString();
								temp_index++;
								
							}
							temp_index=0;
								T_User user = uDaoImpl.findByUsername(temp[1]);
								DateTimeFormatter formatter;
								if (temp[2].matches(".*[a-z].*")) {
									 formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
								}
								else {
									 formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								}
						
								
								//convert String to LocalDate
								LocalDate localDate = LocalDate.parse(temp[2], formatter);
								
								Activity newActity = new Activity();
								newActity.setActivityDate(localDate);
								newActity.setUser(user);
								ActivityType aType=null;
								if (temp[0].equals("Call")) {
									aType=ActivityType.Call;
								}
								else if (temp[0].equals("Email")) {
									aType=ActivityType.Email;
								}
								else if (temp[0].equals("Client Academy Visit")) {
									aType=ActivityType.ClientVisit;
								}
								else {
									aType=ActivityType.Meeting;
								}
								List<Activity> aim = aDaoImpl.readByUserAndDay(user, localDate);
								if (aim.isEmpty()) {
									newActity.setActivitiesDone(new HashMap<ActivityType, Integer>());
									newActity.getActivitiesDone().put(aType, 1);
									aDaoImpl.create(newActity);
								}
								
								else if (aim.get(0).getActivitiesDone().containsKey(aType)) {
									newActity.getActivitiesDone().put(aType, aim.get(0).getActivitiesDone().get(aType) + 1);
									aim.get(0).setActivitiesDone(newActity.getActivitiesDone());
									aDaoImpl.update(aim.get(0));
								}
								else {
									newActity.setActivitiesDone(aim.get(0).getActivitiesDone());
									newActity.getActivitiesDone().put(aType, 1);
									aim.get(0).setActivitiesDone(newActity.getActivitiesDone());
									aDaoImpl.update(aim.get(0));
								}
								
							}
						
						
						wb.close();
						fis.close();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.println("<script type='text/javascript'>");
			out.println("window.location.href='upload_doc.jsp?filename="+file_name+"'");
			out.println("</script>");
			out.close();
			
		}	
		return mv;
	}
}
