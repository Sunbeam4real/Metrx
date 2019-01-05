package com.fdmgroup.dao;

import java.time.LocalDate;
import java.util.List;

import com.fdmgroup.model.T_User;
import com.fdmgroup.model.Target;

public interface TargetDAOI extends ICreatable<Target>, IDeletable<Target>, IReadable<Target>, IUpdatable<Target>{
	
	List<Target> readByUser(T_User user);
	List<Target> readByUserAndDay(T_User user, LocalDate activityDate);
	List<Target> readByUserAndDate(T_User user, LocalDate start, LocalDate end);
}
