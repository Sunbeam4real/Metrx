package com.fdmgroup.model;

import java.time.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "BusinessCase")
@NamedQueries({
		// find all businesscases
		@NamedQuery(name = "BusinessCase.findAll", query = "SELECT a FROM BusinessCase a where a.isVisible =:true"),
		// find businesscases by businesscaseID
		@NamedQuery(name = "BusinessCase.findByID", query = "SELECT a FROM BusinessCase a WHERE a.businessCaseId = :businessCaseId"),
		// find businesscases by userId
		@NamedQuery(name = "BusinessCase.findBySentUser", query = "SELECT a FROM BusinessCase a WHERE a.sentUserId = :sentUser"),
		// find by receiving userId
		@NamedQuery(name = "BusinessCase.findByRecUser", query = "SELECT a FROM BusinessCase a WHERE a.recUser = :recUserId"), })
public class BusinessCase implements IStorable {

	@Id
	@Expose
	@Column(name = "businessCaseId")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "businessCaseId")
	@SequenceGenerator(name = "businessCaseId", sequenceName = "businessCaseId")
	private long businessCaseId;

	@Column
	@Expose
	private long sentUserId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	@Expose
	private T_User recUser;

	@Column
	@Expose
	private LocalDate caseDate;

	@Column
	@Expose
	private String subject;

	@Column
	@Expose
	private String message;

	@Column
	@Expose
	private String progress;

	@Column
	@Expose
	private String location;

	@Column
	@Expose
	private String comments;

	@Column(columnDefinition = "Number(1)")
	@Expose
	private boolean isVisible = true;



	public BusinessCase() {
		super();
	}

	public BusinessCase(long sentUserId, T_User recUser, LocalDate caseDate, String subject, String message,
			String progress, String location, String comments) {
		super();
		this.sentUserId = sentUserId;
		this.recUser = recUser;
		this.caseDate = caseDate;
		this.subject = subject;
		this.message = message;
		this.progress = progress;
		this.location = location;
		this.comments = comments;
	}
	
	public BusinessCase(long sentUserId, T_User recUser, LocalDate caseDate, String subject, String message,
			String progress) {
		super();
		this.sentUserId = sentUserId;
		this.recUser = recUser;
		this.caseDate = caseDate;
		this.subject = subject;
		this.message = message;
		this.progress = progress;
	}

	public long getSentUserId() {
		return sentUserId;
	}

	public void setSentUserId(long sentUserId) {
		this.sentUserId = sentUserId;
	}

	public T_User getRecUser() {
		return recUser;
	}

	public void setRecUser(T_User recUser) {
		this.recUser = recUser;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getBusinessCaseId() {
		return businessCaseId;
	}

	public void setBusinessCaseId(long businessCaseId) {
		this.businessCaseId = businessCaseId;
	}

	public LocalDate getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(LocalDate caseDate) {
		this.caseDate = caseDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "BusinessCase [businessCaseId=" + businessCaseId + ", sentUserId=" + sentUserId + ", recUser=" + recUser
				+ ", caseDate=" + caseDate + ", subject=" + subject + ", message=" + message + ", progress=" + progress
				+ ", location=" + location + ", comments=" + comments + ", isVisible=" + isVisible + "]";
	}



}
