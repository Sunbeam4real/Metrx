package com.fdmgroup.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static java.time.temporal.TemporalAdjusters.*;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.fdmgroup.dao.ActivityDAOI;
import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.daoJpa.ActivityDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.helperClasses.ActivityCalculator;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.T_User;
import com.google.gson.Gson;
@Controller
@SessionAttributes("loggedinuser")
public class ActivityController {

	/* this method populates an individual user's activity page. The table data 
*/	@RequestMapping("/activities")
	public ModelAndView populateView(HttpSession session, @RequestParam("userId") long userId) {
		UserDAOI uDao = new UserDAOImpl();
		T_User user = uDao.readById(userId);
		ActivityCalculator actContlr = new ActivityCalculator();
		ModelAndView mv = new ModelAndView();
		ActivityDAOI actDao = new ActivityDAOImpl();
		// LocalDate date1 =
		// LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), 1);
		// //this is for current month
		LocalDate date1 = LocalDate.of(LocalDate.now().minusMonths(1).getYear(), // this is for last month
				LocalDate.now().minusMonths(1).getMonthValue(), 1);
		LocalDate date2 = date1.with(lastDayOfMonth());
		List<Activity> activityList = actDao.readByUserAndDate(user, date1, date2);
		List<LocalDate> datesMonth = new ArrayList<LocalDate>();
		LocalDate temp = date1;
		for (Activity a : activityList) {
			datesMonth.add(a.getActivityDate());
			System.out.println(a.getActivityDate());
		}

		List<List> listPerYear = actContlr.getActivityCountOrganizedByMonth(user, LocalDate.now().getYear());
		Map<LocalDate, List<Integer>> myMap = new LinkedHashMap<LocalDate, List<Integer>>();
		while (!temp.isAfter(date2)) {
			List<Activity> listperday = actDao.readByUserAndDay(user, temp);
			if (datesMonth.contains(temp))
				myMap.put(temp, actContlr.calcTotal(listperday));
			temp = temp.plusDays(1);
		}

		//////////// tests map
		for (Entry<LocalDate, List<Integer>> entry : myMap.entrySet()) {
			LocalDate key = entry.getKey();
			List<Integer> value = entry.getValue();
			for (Integer aString : value) {
				// System.out.println("key : " + key + " value : " + aString);
			}
		}
		////////////

		mv.addObject("listPYear", listPerYear);
		mv.addObject("myMap", myMap);
		mv.addObject("userId", userId);
		mv.setViewName("activity-individual.jsp");
		return mv;

	}

	@RequestMapping("/activitiesPerYear")
	public @ResponseBody String viewActivitiesPerYear(@RequestParam("year") int year,@RequestParam("userId") long userId) {
		UserDAOI uDao = new UserDAOImpl();
		T_User user = uDao.readById(userId);
		ActivityCalculator actContlr = new ActivityCalculator();
		Gson gson = new Gson();
		List<List> lists = actContlr.getActivityCountOrganizedByMonth(user, year);
		String json = gson.toJson(lists);

		return json;
	}

	@RequestMapping("/activitiesPerMonth")
	public @ResponseBody String viewActivitiesPerMonth(@RequestParam("userId") long userId,
			@RequestParam("year") int year, @RequestParam("month") int month, HttpSession session) {
		ActivityCalculator actContlr = new ActivityCalculator();
		ActivityDAOI actDao = new ActivityDAOImpl();
		UserDAOI uDao = new UserDAOImpl();
		System.out.println(userId);
		System.out.println(year);
		System.out.println(month);
		Gson gson = new Gson();
		LocalDate date1 = LocalDate.of(year, month, 1); // this is for current month
		LocalDate date2 = date1.with(lastDayOfMonth());
		T_User user = uDao.readById(userId);
		List<Activity> activityList = actDao.readByUserAndDate(user, date1, date2);

		Map<LocalDate, List<Integer>> myMap = new LinkedHashMap<LocalDate, List<Integer>>();

		for (Activity a : activityList) {
			List<Activity> listperday = actDao.readByUserAndDay(user, a.getActivityDate());
//			System.out.println(listperday);
			myMap.put(a.getActivityDate(), actContlr.calcTotal(listperday));
		}

		////////// tests map
		for (Entry<LocalDate, List<Integer>> entry : myMap.entrySet()) {
			LocalDate key = entry.getKey();
			List<Integer> value = entry.getValue();
			for (Integer aString : value) {
				// System.out.println("key : " + key + " value : " + aString);
			}
		}
		//////////

		String json = gson.toJson(myMap);
		System.out.println(json);

		return json;
	}

	@RequestMapping("/activitiesPerDay")
	public @ResponseBody String viewActivitiesPerDay(@RequestParam("userId") long userId, @RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("day") int day, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ActivityCalculator actContlr = new ActivityCalculator();
		ActivityDAOI actDao = new ActivityDAOImpl();
		LocalDate date1 = LocalDate.of(year, month, day);
		T_User user = (T_User) session.getAttribute("loggedinuser");
		List<Activity> activityList = actDao.readByUserAndDay(user, date1);
		Map<LocalDate, List<Integer>> myMap = new LinkedHashMap<LocalDate, List<Integer>>();
		myMap.put(date1, actContlr.calcTotal(activityList));
		Gson gson = new Gson();
		String json = gson.toJson(myMap);
		System.out.println(json);

		return json;

	}

}
