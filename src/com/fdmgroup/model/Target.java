package com.fdmgroup.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Target")
@NamedQueries({
	//find all targets
	@NamedQuery(name = "target.findAll", query = "SELECT t FROM Target t where t.isVisible = TRUE"),
	//find target by targetID
	@NamedQuery(name = "target.findByID", query = "SELECT t FROM Target t WHERE t.targetId = :targetId"),
	//find targets by user
	@NamedQuery(name = "target.findByUser", query = "SELECT t FROM Target t WHERE t.user = :user"),
	//find target by user and date
	@NamedQuery(name = "target.findByUserAndDate", query = "SELECT a FROM Target a WHERE a.user = :user AND a.targetDate BETWEEN :start AND :end"),
	//find target by user and day
	@NamedQuery(name = "target.findByUserAndDay", query = "SELECT a FROM Target a WHERE a.user = :user AND a.targetDate = :targetDate"),
})
public class Target implements IStorable{
	
	@Id
	@Column(name="targetId")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="targetId") 
	@SequenceGenerator(name="targetId", sequenceName="targetId")
	private int targetId;
	
	@ManyToOne
	private T_User user;
	
	@Column
	private ActivityType type;
	
	@Column
	private LocalDate targetDate;
	
	@Column
	private int count;
	
	@Column(columnDefinition = "Number(1)")
	private boolean isVisible = true;

	public Target() {
		super();
	}
	

	public Target( T_User user, ActivityType type, LocalDate targetDate, int count, boolean isVisible) {
		super();
		this.user = user;
		this.type = type;
		this.targetDate = targetDate;
		this.count = count;
		this.isVisible = isVisible;
	}



	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public T_User getUser() {
		return user;
	}

	public void setUser(T_User user) {
		this.user = user;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



	public boolean isVisible() {
		return isVisible;
	}



	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}



	@Override
	public String toString() {
		return "Target [targetId=" + targetId + ", user=" + user + ", type=" + type + ", targetDate=" + targetDate
				+ ", count=" + count + ", isVisible=" + isVisible + "]";
	}
	

}
