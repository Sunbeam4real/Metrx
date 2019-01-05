package com.fdmgroup.dao;

import java.time.LocalDate;
import java.util.List;

import com.fdmgroup.model.Activity;
import com.fdmgroup.model.T_User;

public interface ActivityDAOI extends ICreatable<Activity>, IDeletable<Activity>, IReadable<Activity>, IUpdatable<Activity>{

	List<Activity> readByUser(T_User user);
	List<Activity> readByUserAndDay(T_User user, LocalDate activityDate);
	List<Activity> readByUserAndDate(T_User user, LocalDate start, LocalDate end);
	List<Activity> readAllByDate(LocalDate start, LocalDate end);
}
