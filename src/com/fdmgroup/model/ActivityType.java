package com.fdmgroup.model;

public enum ActivityType {
	Call(1), 
	Deal(1), 
	Email(1),
	Meeting(1),
	Interview(1),
	ClientVisit(1);
		
	private final int hasActivity;
	
	 ActivityType(final int hasActivity){
		this.hasActivity = hasActivity;
	}

	public int getHasPrivledge() {
		return hasActivity;
	}

}
