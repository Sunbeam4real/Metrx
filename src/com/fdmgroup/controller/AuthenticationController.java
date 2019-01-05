package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.T_User;
import com.fdmgroup.tool.InputChecking;
import com.fdmgroup.tool.PasswordUtils;

@Controller

//session_attribute
@SessionAttributes("loggedinuser")
public class AuthenticationController {
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView login (@RequestParam("username") String username, @RequestParam("password") String password) {
		UserDAOI udao = new UserDAOImpl();
		T_User foundUser = udao.findByUsername(username);
		ModelAndView mv = new ModelAndView();
		password=PasswordUtils.encrypt(password);
		if(foundUser!= null && password != null && password.equals(foundUser.getPassword())) {
			if (foundUser.isActive() && foundUser.isVisible()) {
				mv.addObject("loggedinuser", foundUser);
				//				//boolean privilege = foundUser.getPrivileges().keySet().contains(Privilege.ViewAllActivities.name());
				//				mv.addObject("privilege", foundUser);
				//mv.setViewName("home");
				mv.setViewName("redirect:/home");
			}
			//account is not active or visible
			else {
				mv.addObject("error", "Ask admin for the support");
				mv.setViewName("index.jsp");
			}
		}
		//invalid input
		else {
			mv.addObject("error","Invalid Username/Password");
			mv.setViewName("index.jsp");
		}
		return mv;
	}

	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest req,@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("position") String position){
		ModelAndView mv = new ModelAndView();
		//security check for enter web address directly:
		HttpSession session=req.getSession();
		if (session.getAttribute("loggedinuser")==null) {
			mv.setViewName("index.jsp");
			return mv;
		}
		if (!(((T_User)session.getAttribute("loggedinuser")).getPrivileges().containsKey(Privilege.CreateUser)) || ((T_User)session.getAttribute("loggedinuser")).getPrivileges().get(Privilege.CreateUser)==0){
			mv.setViewName("home");
			return mv;
		}

		String[] privs = req.getParameterValues("priv");
		Map<Privilege, Integer> privileges = new HashMap<Privilege,Integer>();
		if (privs != null) {
		for(String p: privs) {
			privileges.put(Privilege.valueOf(p), 1);
		}
		}

		// Check if all parameters are non-empty string with valid syntax/regex.
		if (InputChecking.emptyCheck(username)) {

		}
		if (InputChecking.checkPassword(password)) {

		}
		if (InputChecking.emptyCheck(firstname)) {

		}
		if (InputChecking.emptyCheck(lastname)) {

		}
		if (InputChecking.emptyCheck(position)) {

		}


		// Get all privileges from the privileges input
		// Get UserDAO 
		//		ArrayList<Privilege> privileges = new ArrayList<Privilege>();
		//		for (String priv: privs) {
		//			// for each priv get the Privilege enum and add to the privileges array.
		//		}
		UserDAOI udao = new UserDAOImpl();


		//check if the username is existed .
		if (udao.findByUsername(username)!=null) {
			mv.setViewName("addUser.jsp");
			mv.addObject("message", "This user is existed");
		}
		else {
			//Create User with all these params.
			T_User newUser = new T_User(firstname, lastname, username, PasswordUtils.encrypt(password), position, privileges,true,true);

			// Add to database the new T_User
			udao.create(newUser);

			// Add attributes and set next jsp page.
			mv.setViewName("addUser.jsp");
			mv.addObject("message", "Successfully added user.");
		}

		return mv;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,HttpServletRequest request, HttpServletResponse response){
		session.removeAttribute("loggedinuser");
		for (Cookie ck:request.getCookies()) {
			ck.setMaxAge(0);
			response.addCookie(ck);
		}
		return "redirect:index.jsp";
	}
}
