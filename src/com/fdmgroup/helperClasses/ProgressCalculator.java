package com.fdmgroup.helperClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fdmgroup.model.Activity;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.Target;

public class ProgressCalculator {
	
	/**
	 * Calculates the total current yearly percentage of progress completed of an activity
	 *  
     * @param activities List of activities within same year 
     * @param target Target object for that year and that ActivityType
     * @return:
     * <pre>
     * Percent of yearly goal completed so far represented by a double 
     * 
     * 0 otherwise
     * </pre>	
     */
	public double calcCurrentYearlyTotal(ArrayList<Activity> activities, Target target) {
		double value = 0;
		for (Activity a : activities) {
			if(a.getActivitiesDone().containsKey(target.getType())) {
				value = value + a.getActivitiesDone().get(target.getType()); 
			}
		}
		return(value/target.getCount())*100;
		
	}
	
	/**
	 * Calculates the total current monthly percentage of progress completed of an activity
	 *  
     * @param activities list of activities within same month 
     * @param target target object for that year and that ActivityType
     * @return:
     * <pre>
     * Percent of monthly goal completed so far represented by a double 
     * 
     * 0 otherwise
     * </pre>
     */
	public double calcCurrentMonthlyTotal(ArrayList<Activity> activities, Target target) {
		double value = 0;
		for (Activity a : activities) {
			
			if(a.getActivitiesDone().containsKey(target.getType())) {
				value = value + a.getActivitiesDone().get(target.getType()); 
			}
		}
		return(value/calcPerMonthTarget(target))*100;
	}
	
	/**
	 * Calculates the total current daily percentage of progress completed of an activity
	 * 
     * @param activity List of activities within same month 
     * @param target Target object for that year and that ActivityType
     * @return 
     * <pre>
     * Percent of daily goal completed so far represented by a double 
     * 
     * 0 otherwise
     * </pre>
     */
	public double calcCurrentDailyTotal(Activity activity, Target target) {
		
		if(activity.getActivitiesDone().containsKey(target.getType())) {
			double value = activity.getActivitiesDone().get(target.getType());
			return(value/calcPerDayTarget(target))*100;
		}
		
		return 0;
	}
	
	//calculate the average goal per day to meet yearly goal
	private int calcPerDayTarget(Target target) {
		return (int) Math.ceil((double)target.getCount() / 261);
	}
	
	//calculate the average goal per month to meet yearly goal
	private int calcPerMonthTarget(Target target) {
		return calcPerDayTarget(target) * 20;
	}
	
	public double calcActivityTotal(ArrayList<Activity> activities, Target target) {
		double value = 0;
		for (Activity a : activities) {
			
			if(a.getActivitiesDone().containsKey(target.getType())) {
				value = value + a.getActivitiesDone().get(target.getType()); 
			}
		}
		return(value/calcPerMonthTarget(target))*100;
	}

}
