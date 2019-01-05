package com.fdmgroup.dao;


import com.fdmgroup.model.T_User;

public interface UserDAOI extends ICreatable<T_User>, IDeletable<T_User>, IReadable<T_User>, IUpdatable<T_User>{

	T_User findByUsername(String username);

}
