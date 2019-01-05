package com.fdmgroup.helperClasses;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.dao.ActivityDAOI;
import com.fdmgroup.daoJpa.ActivityDAOImpl;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.T_User;



/*This method generates a list of activities of organized by month.
 The acitivities are also a list.
 
  */
public class ActivityCalculator {
	
	public List<List> getActivityCountOrganizedByMonth(T_User user, int year) {
		ActivityDAOI actDao = new ActivityDAOImpl();
		List<List> lists = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			LocalDate date1 = LocalDate.of(year, i, 1);
			LocalDate date2 = date1.with(lastDayOfMonth());
			List<Activity> activityList = actDao.readByUserAndDate(user, date1, date2);
			lists.add(calcTotal(activityList));

		}
		return lists;
	}
	
/*Receives a list of Activities
Calculates the total for each activity type.
Returns a list of totals of activity types. 
 */
	public List<Integer> calcTotal(List<Activity> list) {

		int call = 0;
		for (Activity a : list) {

			if (a.getActivitiesDone().containsKey(ActivityType.Call)) {
				call = call + a.getActivitiesDone().get(ActivityType.Call);
			}
		} 


		int email = 0;
		for (Activity a : list) {

			if (a.getActivitiesDone().containsKey(ActivityType.Email)) {
				email = email + a.getActivitiesDone().get(ActivityType.Email);
			}
		}
		
		int meeting = 0;
		for (Activity a : list) {

			if (a.getActivitiesDone().containsKey(ActivityType.Meeting)) {
				meeting = meeting + a.getActivitiesDone().get(ActivityType.Meeting);
			}
		}
		
		int interview = 0;
		for (Activity a : list) {

			if (a.getActivitiesDone().containsKey(ActivityType.Interview)) {
				interview = interview + a.getActivitiesDone().get(ActivityType.Interview);
			}
		}
		
		int clientVisit = 0;
		for (Activity a : list) {

			if (a.getActivitiesDone().containsKey(ActivityType.ClientVisit)) {
				clientVisit = clientVisit + a.getActivitiesDone().get(ActivityType.ClientVisit);
			}
		}

		List<Integer> l = new ArrayList<>();

		l.add(call);
		l.add(email);
		l.add(meeting);
		l.add(interview);
		l.add(clientVisit);
		return l;

	}



}
