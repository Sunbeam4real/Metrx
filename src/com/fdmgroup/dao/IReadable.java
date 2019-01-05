package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.IStorable;

public interface IReadable <T extends IStorable>{
	T readById(long id);
	List<T> readAll();
}
