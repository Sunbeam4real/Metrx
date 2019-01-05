package com.fdmgroup.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Activity")
@NamedQueries({
	//find all activity
	@NamedQuery(name = "activity.findAll", query = "SELECT a FROM Activity a WHERE a.isVisible = TRUE"),
	//find activity by activityID
	@NamedQuery(name = "activity.findByID", query = "SELECT a FROM Activity a WHERE a.activityId = :activityId"),
	//find activity by user
	@NamedQuery(name = "activity.findByUser", query = "SELECT a FROM Activity a WHERE a.user = :user"),
	//find activity by user and date
	@NamedQuery(name = "activity.findByUserAndDate", query = "SELECT a FROM Activity a WHERE a.user = :user AND a.activityDate BETWEEN :start AND :end"),
	//find activity by user and day
	@NamedQuery(name = "activity.findByUserAndDay", query = "SELECT a FROM Activity a WHERE a.user = :user AND a.activityDate = :activityDate"),
	//find all activity by date
	@NamedQuery(name = "activity.findAllByDate", query = "SELECT a FROM Activity a WHERE a.activityDate BETWEEN :start AND :end"),
})
public class Activity implements IStorable{
	
	@Id
	@Column(name="activityId")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="activityId") 
	@SequenceGenerator(name="activityId", sequenceName="activityId")
	private long activityId;
	
	@Column(name="activityDate")
	private LocalDate activityDate;
	
	@ManyToOne
	private T_User user;
	
    @ElementCollection
    @CollectionTable(name = "activity_type")
    @MapKeyColumn(name = "activity_type_col")
    @Column(name = "integer_col")
    private Map<ActivityType, Integer> activitiesDone = new HashMap<>();
	
	@Column(columnDefinition = "Number(1)")
	private boolean isVisible = true;
    
	public Activity() {
		super();
	}

	public Activity(LocalDate activityDate, T_User user,
			HashMap<ActivityType, Integer> activitiesDone) {
		super();
		this.activityDate = activityDate;
		this.user = user;
		this.activitiesDone = activitiesDone;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public LocalDate getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(LocalDate activityDate) {
		this.activityDate = activityDate;
	}

	public T_User getUser() {
		return user;
	}

	public void setUser(T_User user) {
		this.user = user;
	}

	public Map<ActivityType, Integer> getActivitiesDone() {
		return activitiesDone;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setActivitiesDone(Map<ActivityType, Integer> activitiesDone) {
		this.activitiesDone = activitiesDone;
	}

	
	public boolean appendActivityCount(ActivityType type, int count) {
		if(activitiesDone.containsKey(type)) {
			activitiesDone.put(type, activitiesDone.get(type) + count);
			return true;
		}	
		return false;
	}
	
	//Default value should be 0 not null
	public boolean addActivity(ActivityType type) {
		if(activitiesDone.containsKey(type) == false) {
			activitiesDone.put(type, 0);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityDate=" + activityDate + ", user=" + user
				+ ", activitiesDone=" + activitiesDone + ", isVisible=" + isVisible + "] \n";
	}	
	
	
}
