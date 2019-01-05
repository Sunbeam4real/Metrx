package com.fdmgroup.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.daoJpa.ActivityDAOImpl;
import com.fdmgroup.daoJpa.TargetDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.T_User;
import com.fdmgroup.model.Target;

@Controller
public class UserViewController {
	
	@RequestMapping(value = "/userviewcontroller", method = RequestMethod.GET)
	public ModelAndView userviewcontroller(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
		ActivityDAOImpl act = new ActivityDAOImpl();
		UserDAOImpl user = new UserDAOImpl();
		HashMap<T_User, List> totalMonthlyActsPerUser = new HashMap<>();
		
		int count = LocalDate.now().getMonthValue();
		int currentYear = LocalDate.now().getYear();
		for(T_User u : user.readAll()) {
			ArrayList<List> monthlyTotals = new ArrayList<>();
			//change to start and end date
			if(act.readByUserAndDate(u, LocalDate.of(currentYear, 1 , 1), LocalDate.now()) != null 
					&& !act.readByUserAndDate(u, LocalDate.of(currentYear, 1 , 1), LocalDate.now()).isEmpty()) {
				
				for(int i=1;i<=count;i++) {
					Month monthPointer = Month.of(i);
					int lastDayOfMonth = LocalDate.of(currentYear, i, 1).lengthOfMonth();
					ArrayList<Activity> monthlyActTotal = (ArrayList<Activity>) act.readByUserAndDate(u, LocalDate.of(currentYear, monthPointer , 1), 
							LocalDate.of(currentYear, monthPointer, lastDayOfMonth));
					
					monthlyTotals.add(calculateTotals(monthlyActTotal));
				}
				totalMonthlyActsPerUser.put(u, monthlyTotals);
			}	
		}
		
		System.out.println(totalMonthlyActsPerUser.toString());
		
		mv.addObject("totalActivities", totalMonthlyActsPerUser);
		request.getSession().setAttribute("totActivities", totalMonthlyActsPerUser);
		mv.addObject("totalActivitiesSize", totalMonthlyActsPerUser.size());
        mv.setViewName("view-doc.jsp");

		return mv;

	}

	private ArrayList<Integer> calculateTotals(ArrayList<Activity> monthlyActTotal) {
		int calls = 0;
		int deals = 0;
		int emails = 0;
		ArrayList<Integer> totals = new ArrayList<>();
		
		for (Activity a : monthlyActTotal) {
			Map<ActivityType, Integer> acts = a.getActivitiesDone();
			
			if(acts.containsKey(ActivityType.Call)) {
				calls += acts.get(ActivityType.Call);
			}
			if(acts.containsKey(ActivityType.Deal)) {
				deals += acts.get(ActivityType.Deal);
			}
			if(acts.containsKey(ActivityType.Email)) {
				emails += acts.get(ActivityType.Email);
			}
		}
		totals.add(calls);
		totals.add(deals);
		totals.add(emails);
		
		return totals;
		
	}
	

}
