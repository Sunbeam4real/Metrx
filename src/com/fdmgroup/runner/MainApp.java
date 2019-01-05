package com.fdmgroup.runner;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fdmgroup.dao.ActivityDAOI;
import com.fdmgroup.dao.BusinessCaseDAO;
import com.fdmgroup.dao.TargetDAOI;
import com.fdmgroup.dao.UserDAOI;
import com.fdmgroup.daoJpa.ActivityDAOImpl;
import com.fdmgroup.daoJpa.BusinessCaseDAOImpl;
import com.fdmgroup.daoJpa.TargetDAOImpl;
import com.fdmgroup.daoJpa.UserDAOImpl;
import com.fdmgroup.model.Activity;
import com.fdmgroup.model.ActivityType;
import com.fdmgroup.model.BusinessCase;
import com.fdmgroup.model.Privilege;
import com.fdmgroup.model.T_User;
import com.fdmgroup.model.Target;
import com.fdmgroup.tool.PasswordUtils;

public class MainApp {
	
	public static void main(String[] args) {
		String pww = PasswordUtils.encrypt("123456");
		 UserDAOI udao = new UserDAOImpl();
		 ActivityDAOI adao = new ActivityDAOImpl();
		 TargetDAOI tdao = new TargetDAOImpl();
		 BusinessCaseDAO bdao = new BusinessCaseDAOImpl();
		 
		  Map<Privilege,Integer> priv = new HashMap<Privilege,Integer>();
	        priv.put(Privilege.CreateUser,1);
	        priv.put(Privilege.EditUserAdmin,1);
	        priv.put(Privilege.CreateBusinessCase, 1);
	        priv.put(Privilege.EditUser,1);
	        priv.put(Privilege.ExportDoc,1);
	        priv.put(Privilege.UploadDoc,1);
	        priv.put(Privilege.ViewAllActivities,1);
	        priv.put(Privilege.ViewDoc,1);
	        priv.put(Privilege.ViewProfiles,1);
	        priv.put(Privilege.ViewSelf,1);
	        
	        T_User user = new T_User("Kie", "Ogiya", "kiki", pww, "manager",priv, true, true);
	        
	        udao.create(user);
	        
	     
			Map<Privilege,Integer> privs = new HashMap<Privilege,Integer>();
			privs.put(Privilege.CreateUser,1);
			privs.put(Privilege.EditUserAdmin,1);
			
			T_User users = new T_User("john", "doe", "jdoe", pww, "manager",privs, true, true);
			T_User user1 = new T_User("james", "doe", "jamesdoe", pww, "manager",privs, true, true);
			T_User user2 = new T_User("jacob", "doe", "jacobdoe", pww, "manager",privs, true, true);
			T_User user3 = new T_User("jim", "doe", "jimdoe", pww, "manager",privs, true, true);
			T_User user4 = new T_User("joe", "doe", "joedoe", pww, "manager",privs, true, true);
			T_User user5 = new T_User("jake", "doe", "jakedoe", pww, "manager",privs, true, true);
			T_User user6 = new T_User("tony", "mariani", "Anthony Mariani", pww, "manager",priv, true, true);
            T_User user7 = new T_User("brennen", "Leslie", "Brennen Leslie", pww, "manager",priv, true, true);
            T_User user8 = new T_User("Ibiye", "Okaye", "Ibiye Okaye", pww, "manager",priv, true, true);
            T_User user9 = new T_User("jeffrey", "fang", "Jeffrey Fang", pww, "manager",priv, true, true);
            T_User user10 = new T_User("joelle", "mcmanus", "Joelle McManus", pww, "manager",priv, true, true);
            T_User user11 = new T_User("kat", "turchik", "Kat Turchik", pww, "manager",priv, true, true);
            T_User user12 = new T_User("purvaja", "choudhary", "Purvaja Choudhary", pww, "manager",priv, true, true);
            T_User user13 = new T_User("kendra", "halman", "Kendra Halman", pww, "manager",priv, true, true);
            T_User user14 = new T_User("sean", "batts", "Sean Batts", pww, "manager",priv, true, true);
            
            udao.create(user14);
udao.create(user12);
            udao.create(user13);
            udao.create(user11);
            udao.create(user10);
            udao.create(user9);
            udao.create(user7);
            udao.create(user8);
            udao.create(user6);
	
			Boolean u=udao.create(users);
			udao.create(user1);
			udao.create(user2);
			udao.create(user3);
			udao.create(user4);
			udao.create(user5);
			
			HashMap<ActivityType, Integer> act = new HashMap<ActivityType, Integer>();
			act.put(ActivityType.Call, 31);
			act.put(ActivityType.Deal, 22);
			act.put(ActivityType.Email, 23);
			
			
			
			HashMap<ActivityType, Integer> acts = new HashMap<ActivityType, Integer>();
			acts.put(ActivityType.Deal, 22);
			acts.put(ActivityType.Email, 23);
			
			HashMap<ActivityType, Integer> act1 = new HashMap<ActivityType, Integer>();
			act1.put(ActivityType.Call, 31);
			act1.put(ActivityType.Email, 23);
			
			HashMap<ActivityType, Integer> act3 = new HashMap<ActivityType, Integer>();
			act3.put(ActivityType.Call, 31);
			act3.put(ActivityType.Deal, 22);
			
			Activity a = new Activity(LocalDate.now(), user, act);
			Activity a1 = new Activity(LocalDate.now(), users, acts);
			Activity a2 = new Activity(LocalDate.now(), user1, act1);
			Activity a3 = new Activity(LocalDate.now(), user2, act);
			Activity a4 = new Activity(LocalDate.now(), user3, act3);
			Activity a5 = new Activity(LocalDate.now(), user4, act);
			Activity a6 = new Activity(LocalDate.now(), user5, act);
			
			adao.create(a);
			adao.create(a1);
			adao.create(a2);
			adao.create(a3);
			adao.create(a4);
			adao.create(a5);
			adao.create(a6);
			
			Target t = new Target( user, ActivityType.Call, LocalDate.now(), 50, u);
			//Target t1 = new Target( users, ActivityType.Call, LocalDate.now(), 510, u);
			Target t2= new Target( user1, ActivityType.Call, LocalDate.now(), 1000, u);
			Target t3 = new Target( user2, ActivityType.Call, LocalDate.now(), 5340, u);
			Target t4 = new Target( user3, ActivityType.Call, LocalDate.now(), 50, u);
			Target t5 = new Target( user4, ActivityType.Call, LocalDate.now(), 510, u);
			Target t6 = new Target( user5, ActivityType.Call, LocalDate.now(), 53420, u);
			
			tdao.create(t);
			//tdao.create(t1);
			tdao.create(t2);
			tdao.create(t3);
			tdao.create(t4);
			tdao.create(t5);
			tdao.create(t6);
			
			t = new Target( user, ActivityType.Deal, LocalDate.now(), 540, u);
			Target t1 = new Target( users, ActivityType.Deal, LocalDate.now(), 50, u);
			// t2= new Target( user1, ActivityType.Deal, LocalDate.now(), 5230, u);
			 t3 = new Target( user2, ActivityType.Deal, LocalDate.now(), 50, u);
			 t4 = new Target( user3, ActivityType.Deal, LocalDate.now(), 530, u);
			 t5 = new Target( user4, ActivityType.Deal, LocalDate.now(), 5230, u);
			 t6 = new Target( user5, ActivityType.Deal, LocalDate.now(), 530, u);
			
			tdao.create(t);
			tdao.create(t1);
			//tdao.create(t2);
			tdao.create(t3);
			tdao.create(t4);
			tdao.create(t5);
			tdao.create(t6);
			
			t = new Target( user, ActivityType.Email, LocalDate.now(), 520, u);
			 t1 = new Target( users, ActivityType.Email, LocalDate.now(), 50, u);
			 t2= new Target( user1, ActivityType.Email, LocalDate.now(), 503, u);
			 t3 = new Target( user2, ActivityType.Email, LocalDate.now(), 50, u);
			// t4 = new Target( user3, ActivityType.Email, LocalDate.now(), 520, u);
			 t5 = new Target( user4, ActivityType.Email, LocalDate.now(), 550, u);
			 t6 = new Target( user5, ActivityType.Email, LocalDate.now(), 502, u);
			
			tdao.create(t);
			tdao.create(t1);
			tdao.create(t2);
			tdao.create(t3);
			//tdao.create(t4);
			tdao.create(t5);
			tdao.create(t6);
			
			BusinessCase b = new BusinessCase(1, user3, LocalDate.now(),"hello",
					"hello", "in progress", "toronto","ldfjlgjds");
			
			BusinessCaseDAOImpl bcd = new BusinessCaseDAOImpl();
			//T_User u = udao.readById(4);
			
			//System.out.println(bcd.readByRecUser(u));
			
//			HashMap<ActivityType, Integer> acts = new HashMap<ActivityType, Integer>();
//			act.put(ActivityType.Deal, 22);
//			act.put(ActivityType.Email, 23);
//			
//			HashMap<ActivityType, Integer> act1 = new HashMap<ActivityType, Integer>();
//			act.put(ActivityType.Call, 31);
//			act.put(ActivityType.Email, 23);
//			
//			HashMap<ActivityType, Integer> act3 = new HashMap<ActivityType, Integer>();
//			act.put(ActivityType.Call, 31);
//			act.put(ActivityType.Deal, 22);
//			
//			Activity a = new Activity(LocalDate.now(), user, act);
//			Activity a1 = new Activity(LocalDate.now(), users, acts);
//			Activity a2 = new Activity(LocalDate.now(), user1, act1);
//			Activity a3 = new Activity(LocalDate.now(), user2, act);
//			Activity a4 = new Activity(LocalDate.now(), user3, act3);
//			Activity a5 = new Activity(LocalDate.now(), user4, act);
//			Activity a6 = new Activity(LocalDate.now(), user5, act);
//			
//			adao.create(a);
//			adao.create(a1);
//			adao.create(a2);
//			adao.create(a3);
//			adao.create(a4);
//			adao.create(a5);
//			adao.create(a6);
//			
//			Target t = new Target( user, ActivityType.Call, LocalDate.now(), 50, u);
//			//Target t1 = new Target( users, ActivityType.Call, LocalDate.now(), 510, u);
//			Target t2= new Target( user1, ActivityType.Call, LocalDate.now(), 1000, u);
//			Target t3 = new Target( user2, ActivityType.Call, LocalDate.now(), 5340, u);
//			Target t4 = new Target( user3, ActivityType.Call, LocalDate.now(), 50, u);
//			Target t5 = new Target( user4, ActivityType.Call, LocalDate.now(), 510, u);
//			Target t6 = new Target( user5, ActivityType.Call, LocalDate.now(), 53420, u);
//			
//			tdao.create(t);
//			//tdao.create(t1);
//			tdao.create(t2);
//			tdao.create(t3);
//			tdao.create(t4);
//			tdao.create(t5);
//			tdao.create(t6);
//			
//			t = new Target( user, ActivityType.Deal, LocalDate.now(), 540, u);
//			Target t1 = new Target( users, ActivityType.Deal, LocalDate.now(), 50, u);
//			// t2= new Target( user1, ActivityType.Deal, LocalDate.now(), 5230, u);
//			 t3 = new Target( user2, ActivityType.Deal, LocalDate.now(), 50, u);
//			 t4 = new Target( user3, ActivityType.Deal, LocalDate.now(), 530, u);
//			 t5 = new Target( user4, ActivityType.Deal, LocalDate.now(), 5230, u);
//			 t6 = new Target( user5, ActivityType.Deal, LocalDate.now(), 530, u);
//			
//			tdao.create(t);
//			tdao.create(t1);
//			//tdao.create(t2);
//			tdao.create(t3);
//			tdao.create(t4);
//			tdao.create(t5);
//			tdao.create(t6);
//			
//			t = new Target( user, ActivityType.Email, LocalDate.now(), 520, u);
//			 t1 = new Target( users, ActivityType.Email, LocalDate.now(), 50, u);
//			 t2= new Target( user1, ActivityType.Email, LocalDate.now(), 503, u);
//			 t3 = new Target( user2, ActivityType.Email, LocalDate.now(), 50, u);
//			// t4 = new Target( user3, ActivityType.Email, LocalDate.now(), 520, u);
//			 t5 = new Target( user4, ActivityType.Email, LocalDate.now(), 550, u);
//			 t6 = new Target( user5, ActivityType.Email, LocalDate.now(), 502, u);
//			
//			tdao.create(t);
//			tdao.create(t1);
//			tdao.create(t2);
//			tdao.create(t3);
//			//tdao.create(t4);
//			tdao.create(t5);
//			tdao.create(t6);
//			
//			BusinessCase b = new BusinessCase(1, user3, LocalDate.now(),"hello",
//					"hello", "in progress", "toronto","ldfjlgjds");
//			
//			bdao.create(b);
//			
//			user3.addCases(b);
//			user3.setActive(false);
//			user3.setFirstName("Mayank");
//			udao.update(user3);
//			//update user
//			
//			System.out.println(user3.containsPrivilege(Privilege.EditUser));
//			T_User usertest = udao.readById(1L);
//			System.out.println(usertest.getUsername());
//			System.out.println(LocalDate.now());
//			ArrayList<String> pri = new ArrayList<>();
//			pri.add(Privilege.ViewSelf.name());
//			String test = Privilege.ViewSelf.name();
//			System.out.println(pri.contains(test));
	        
		
	}
}	
		
		//convert String to LocalDate
//		LocalDate localDate = LocalDate.parse(date, formatter);
//		Activity a = new Activity(localDate, user, act);
//	
//		adao.create(a);*/
//
//		Target t = new Target( user, ActivityType.Call, LocalDate.now(), 50, u);
//		tdao.create(t);
//		
//		BusinessCase b = new BusinessCase(1, user3, LocalDate.now(),"hello",
//				"hello", "in progress", "toronto","ldfjlgjds");
//		
//		bdao.create(b);
//		
//		user3.addCases(b);
//		user3.setActive(false);
//		user3.setFirstName("Mayank");
//		udao.update(user3);
//		//update user
//		
//		System.out.println(user3.containsPrivilege(Privilege.EditUser));
//		T_User usertest = udao.readById(1L);
//		System.out.println(usertest.getUsername());
//		System.out.println(LocalDate.now());
//		ArrayList<String> privs = new ArrayList<>();
//		privs.add(Privilege.ViewSelf.name());
//		String test = Privilege.ViewSelf.name();
//		System.out.println(privs.contains(test));
	

		
		
		
		
	
