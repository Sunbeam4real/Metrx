package com.fdmgroup.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public enum Privilege {

	ViewSelf(1), 
	ViewProfiles(1), 
	ViewDoc(1), 
	UploadDoc(1), 
	ViewAllActivities(1),
	CreateUser(1),
	EditUser(1),
	EditUserAdmin(1),
	ExportDoc(1),
	CreateBusinessCase(1);
	
	
	private final int hasPrivledge;
	
	 Privilege(final int hasPrivledge){
		this.hasPrivledge = hasPrivledge;
	}

	public int getHasPrivledge() {
		return hasPrivledge;
	}
}