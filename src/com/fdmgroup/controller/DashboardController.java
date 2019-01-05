package com.fdmgroup.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.daoJpa.ActivityDAOImpl;
import com.fdmgroup.daoJpa.TargetDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.helperClasses.ProgressCalculator;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.T_User;
import com.fdmgroup.model.Target;

@Controller
@SessionAttributes("loggedinuser")
public class DashboardController {
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView homeScreen (HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		UserDAOImpl udao = new UserDAOImpl();
		
		T_User currentUser = (T_User) request.getSession().getAttribute("loggedinuser");
		
		Set<Privilege> keys = currentUser.getPrivileges().keySet(); 
		ArrayList<String> privs = new ArrayList<>();
		
		for (Privilege p : keys) {
			privs.add(p.name());
		}
		
		if(privs.contains(Privilege.ViewAllActivities.name())){
		
			//operator
			calTopThreeActivitiesAdmin(mv);
			
		}else if(privs.contains(Privilege.ViewSelf.name())) {
			calTopThreeActivities(mv, currentUser);
			mv.addObject("viewSelf", Privilege.ViewSelf.name());
		}
		
		mv.addObject("PrivList", privs);
		
		mv.setViewName("dashboard.jsp");
		
		return mv;
		
	}
	
	@RequestMapping(value="/index")
	public ModelAndView mainScreen (HttpServletRequest request, HttpServletResponse response) {

		T_User currentUser = (T_User) request.getSession().getAttribute("loggedinuser");
		
		ModelAndView mv = new ModelAndView();

		if(currentUser == null) {
			//return to login screen
			mv.setViewName("index.jsp");
			return mv;
		}
		return homeScreen(request, response);
		
	}
	
	@RequestMapping(value="/upload")
	public ModelAndView uploadPage (HttpServletRequest request, HttpServletResponse response) {
		
		//Also need to check session before go to that page
		//...
		//...
		ModelAndView mv = new ModelAndView();
		mv.setViewName("upload_doc.jsp");
		return mv;
	}
	
	public void calTopThreeActivities(ModelAndView mv, T_User currentUser) {
		ActivityDAOImpl acDAO = new ActivityDAOImpl();
		TargetDAOImpl tarDAO = new TargetDAOImpl();
		ProgressCalculator calc = new ProgressCalculator();
		
		LocalDate start = LocalDate.now().withDayOfMonth(1);
		LocalDate end = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

		ArrayList<Activity> activities = (ArrayList<Activity>) acDAO.readByUserAndDate(currentUser,start,end);
		
		//make a read by user for target as well
		//get targets by type
		ArrayList<Target> targets = (ArrayList<Target>) tarDAO.readAll();
		
		int numOfCalls = (int) calc.calcCurrentMonthlyTotal(activities, targets.get(0));
		int numOfDeals = (int) calc.calcCurrentMonthlyTotal(activities, targets.get(1));
		int numOfEmails = (int) calc.calcCurrentMonthlyTotal(activities, targets.get(2));
		
		mv.addObject("Call", numOfCalls);
		mv.addObject("Deal", numOfDeals);
		mv.addObject("Email", numOfEmails); 
		
		//TODO add actual amounts 
	
	}
	
	public void calTopThreeActivitiesUsers(T_User user, ArrayList<Integer> calls, ArrayList<Integer> deals, ArrayList<Integer> emails) {
		ActivityDAOImpl acDAO = new ActivityDAOImpl();
		TargetDAOImpl tarDAO = new TargetDAOImpl();
		ProgressCalculator calc = new ProgressCalculator();
		
		LocalDate start = LocalDate.now().withDayOfMonth(1);
		LocalDate end = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

		ArrayList<Activity> activities = (ArrayList<Activity>) acDAO.readByUserAndDate(user,start,end);
		
		if(activities != null && !activities.isEmpty()) {
			//make a read by user for target as well
			//get targets by type
			//ArrayList<Target> targets = (ArrayList<Target>) tarDAO.readAll();
			
			ArrayList<Target> targets = (ArrayList<Target>) tarDAO.readByUserAndDate(user, start, end);
			//check for size and type
			for (Target t : targets) {
				if(t.getType() == ActivityType.Call) {
					int numOfCalls = (int) calc.calcCurrentMonthlyTotal(activities, t);
					calls.add(numOfCalls);
				}
				if(t.getType() == ActivityType.Deal) {
					int numOfDeals = (int) calc.calcCurrentMonthlyTotal(activities, t);
					deals.add(numOfDeals);
				}
				if(t.getType() == ActivityType.Email) {
					int numOfEmails = (int) calc.calcCurrentMonthlyTotal(activities, t);
					emails.add( numOfEmails); 
				}
			}
			
		}
	}
	
	public void calTopThreeActivitiesAdmin(ModelAndView mv) {
		ActivityDAOImpl acDAO = new ActivityDAOImpl();
		UserDAOImpl uDAO = new UserDAOImpl();
		TargetDAOImpl tarDAO = new TargetDAOImpl();
		ProgressCalculator calc = new ProgressCalculator();
		
		LocalDate start = LocalDate.now().withDayOfMonth(1);
		LocalDate end = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		
		
		
		ArrayList<T_User> users = (ArrayList<T_User>) uDAO.readAll();
		
		//ArrayList<Activity> activity =  new ArrayList<Activity>();
		
		ArrayList<Integer> calls = new ArrayList<Integer>();
		ArrayList<Integer> deals = new ArrayList<Integer>();
		ArrayList<Integer> emails = new ArrayList<Integer>();
		
		for (T_User user : users) {
			calTopThreeActivitiesUsers(user, calls, deals, emails);
		
		}
	
		mv.addObject("Call", average(calls));
		mv.addObject("Deal", average(deals));
		mv.addObject("Email", average(emails));
	
	}
	
	private int average(ArrayList<Integer> activities) {
		int average = 0;
		
		for(Integer i : activities) {
			average += i;
		}
		
		if (activities.size() == 0) {
			return 0;
		}
		if (activities.size() == 0) {
			return 0;
		}
		return average / activities.size();
	}
}
