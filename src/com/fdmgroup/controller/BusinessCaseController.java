package com.fdmgroup.controller;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.BusinessCaseDAO;
import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.daoJpa.BusinessCaseDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.model.BusinessCase;
import com.fdmgroup.model.T_User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class BusinessCaseController {
     
	// Done but must edit JSP options
     @RequestMapping("/sendBusinessCase")
     public String sendBusinessCase(HttpSession session,  @RequestParam("selectedRecord") String id, @RequestParam("subject") String subject, @RequestParam("message") String message,  @RequestParam("dateOfMessage") String date) {

         LocalDate localDate = LocalDate.parse(date);
         
         UserDAOI udao = new UserDAOImpl();
         BusinessCaseDAO bcDao = new BusinessCaseDAOImpl();
         long recId = Long.valueOf(id).longValue();
         T_User receiver = udao.readById(recId);
         
         T_User loggedInUser = (T_User) session.getAttribute("loggedinuser");
         long sendId = loggedInUser.getUserId();
         
         BusinessCase businessCase = new BusinessCase(sendId, receiver, localDate, subject, message, "Pending");
         bcDao.create(businessCase);
         ModelAndView mv = new ModelAndView();
         T_User user = (T_User) session.getAttribute("loggedinuser");
         List<BusinessCase> sentBusinessCases = ((BusinessCaseDAOImpl) bcDao).readBySentUser(user.getUserId());
         List<BusinessCase> receivedBusinessCases = ((BusinessCaseDAOImpl) bcDao).readByRecUser(user);
         mv.setViewName("viewBCMessages.jsp");
         return "redirect:goToBCMessages";
     }
     
     // No frontend
     @RequestMapping("/AddCommentBusinessCase")
     public String AddCommentBusinessCase(HttpSession session, HttpServletRequest req, HttpServletResponse resp, @RequestParam("comment") String comment) {
    
    	 String[] businessCaseId = req.getParameterValues("businessCaseId");
   	  	
    	 long businessCaseId2 = Long.valueOf(businessCaseId[0]); 
    	 
    	 BusinessCaseDAOImpl bcdao = new BusinessCaseDAOImpl();
    	 
    	 BusinessCase bc = bcdao.readById(businessCaseId2);
    	
    	 bc.setComments(comment);
    	
    	 bc.setProgress("Accepted");
    	 
    	 
    	 
    	 
    	 bcdao.update(bc);
    	
    	 ModelAndView mv = new ModelAndView();
    	 mv.setViewName("receivedBusinessCaseContent.jsp");
    	 mv.addObject("businessCase",bc);
    	 mv.addObject("Message", "Comment sent successfully");
    	 mv.addObject("Message1", "Approved");
    	 
    	 return "redirect:goToBCMessages";
     }
     
     @RequestMapping("/goToBusinessCase")
     public ModelAndView goToBusinessCase() {
          
          UserDAOI udao = new UserDAOImpl();
          List<T_User> users = udao.readAll();
          ModelAndView mv = new ModelAndView();
          mv.addObject("users",users);
          mv.setViewName("createBusinessCase.jsp");
          
          return mv;
          
     }
     
     @RequestMapping("/goToBCMessages")
     public ModelAndView goToBCMessagesCase(HttpSession session) {
    	 
    	 BusinessCaseDAOImpl bdao = new BusinessCaseDAOImpl();
    	 T_User user = (T_User) session.getAttribute("loggedinuser");
    	 List<BusinessCase> sentBusinessCases = bdao.readBySentUser(user.getUserId());
    	 List<BusinessCase> receivedBusinessCases = bdao.readByRecUser(user);
    	 ModelAndView mv = new ModelAndView();
    	 mv.addObject("sentBusinessCases", sentBusinessCases);
    	 mv.addObject("receivedBusinessCases", receivedBusinessCases);
    	 mv.setViewName("viewBCMessages.jsp");
    	 
    	 return mv;
    	 
     }
     
     
     // No frontend
     @RequestMapping("/BusinessCaseSentHistoryForNormalUser")
     public ModelAndView viewBusinessCaseSentHistoryForNormalUser(HttpSession session) {
          
          
          T_User loggedInUser = (T_User) session.getAttribute("loggedinUser");
          BusinessCaseDAOImpl bcdao = new BusinessCaseDAOImpl();
          List<BusinessCase> businessCases =  bcdao.readBySentUser(loggedInUser.getUserId());
          
          ModelAndView mv = new ModelAndView();
          mv.addObject("businessCases",businessCases);
          mv.setViewName("BusinessCase.jsp");
          
          return mv;
     }
     
     
     // No frontend
     @RequestMapping("/BusinessCaseReceivedHistoryForNormalUser")
     public ModelAndView viewBusinessCaseReceivedHistoryForNormalUser(HttpSession session) {
          
          
          T_User loggedInUser = (T_User) session.getAttribute("loggedinUser");
          BusinessCaseDAOImpl bcdao = new BusinessCaseDAOImpl();
          List<BusinessCase> businessCases = bcdao.readByRecUser(loggedInUser);
          
          ModelAndView mv = new ModelAndView();
          mv.addObject("businessCases",businessCases);
          mv.setViewName("BusinessCase.jsp");
          
          return mv;
     }
     
     // No frontend
     @RequestMapping("/businessCaseChangeProgress")
     public ModelAndView businessCaseChangeProgress(HttpServletRequest req, HttpServletResponse resp) {
          
    	 String[] businessCaseId = req.getParameterValues("businessCaseId");
    	  
    	 long businessCaseId2 = Long.valueOf(businessCaseId[0]); 
    	 
    	 BusinessCaseDAOImpl bcdao = new BusinessCaseDAOImpl();
         
          BusinessCase bc = bcdao.readById(businessCaseId2);
          
          bc.setProgress("Approved");
          
          bcdao.update(bc);
          
          ModelAndView mv = new ModelAndView();
          mv.addObject("message1","Approved!");
          mv.addObject("businessCase", bc);
          mv.setViewName("receivedBusinessCaseContent.jsp");
          
          return mv;
     }
     
     @RequestMapping("/viewDetailForBusinessCaseSent")
     public @ResponseBody String viewDetailForBusinessSent(@RequestParam("businessCaseId") long businessCaseId) {
    	 System.out.println(businessCaseId);
//    	 
//    	 String businessCaseId = req.getParameterValues("businessCaseId")[0];
//  
//    	 long businessCaseId2 = Long.valueOf(businessCaseId);	
    	 
    	 BusinessCaseDAOImpl bcdao = new BusinessCaseDAOImpl();
    	 UserDAOImpl userDao = new UserDAOImpl();
    	 
    	 BusinessCase bc = bcdao.readById(businessCaseId);
    	 T_User sentUserDetails = userDao.readById(bc.getSentUserId());
    	 System.out.println(">>>bc " + bc.getComments());
    	 List<Object> bcResultList = new ArrayList<>();
    	 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	 bcResultList.add(bc);
    	 bcResultList.add(sentUserDetails);
    	 
    	 String jsonBc = gson.toJson(bcResultList);
    	 
    	 return jsonBc;
    	 
    	/* ModelAndView mv = new ModelAndView();
         mv.addObject("businessCase", bc);
    	 
         mv.setViewName("viewBCMessages.jsp");
    	 
		return mv;*/
    	 
     }
     
     
     @RequestMapping("/viewDetailForBusinessCaseReceived")
     public @ResponseBody String viewDetailForBusinessCaseReceived(@RequestParam("businessCaseId") long businessCaseId) {

    	 
    	 BusinessCaseDAOImpl bcdao = new BusinessCaseDAOImpl();
    	 UserDAOImpl userDao = new UserDAOImpl();
    	 BusinessCase bc = bcdao.readById(businessCaseId);
    	 T_User sentUserDetails = userDao.readById(bc.getSentUserId());
    	 System.out.println(">>>bc " + bc.getComments());
    	 List<Object> bcResultList = new ArrayList<>();
    	 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	 bcResultList.add(bc);
    	 bcResultList.add(sentUserDetails);
    	 
    	 String jsonBc = gson.toJson(bcResultList);
    	 
    	 return jsonBc;
    	 /*ModelAndView mv = new ModelAndView();
         mv.addObject("businessCase", bc);
    	 
         mv.setViewName("receivedBusinessCaseContent.jsp");
    	 
		return mv;*/
    	 
     }
     
     
     
}
