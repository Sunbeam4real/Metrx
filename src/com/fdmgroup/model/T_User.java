package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table
@NamedQueries({
	//find all activity
	@NamedQuery(name = "T_User.findAll", query = "SELECT a FROM T_User a WHERE a.isVisible = TRUE"),
	//find activity by activityID
	@NamedQuery(name = "T_User.findByID", query = "SELECT a FROM T_User a WHERE a.userId = :userId"),
	//find activity by user
	@NamedQuery(name = "T_User.findByUsername", query = "SELECT a FROM T_User a WHERE a.username = :username")

})
public class T_User implements IStorable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	@Expose
	private long userId;
	
	@Column
	@Expose
	private String firstName;
	
	@Column
	@Expose
	private String lastName;
	
	@Column(nullable=false,unique=true)
	@Expose
	private String username;
	
	@Column(name="pwd",nullable=false)
	private String password;
	
	@Column
	private String position;
	
	@Column(columnDefinition = "Number(1)")
	private boolean isActive = true;
	
	@Column(columnDefinition = "Number(1)")
	private boolean isVisible = true;
	
	@OneToMany(mappedBy="recUser")
	private List<BusinessCase> cases = new ArrayList<>();
    

	@ElementCollection
    @CollectionTable(name = "Privilege")
    @MapKeyColumn(name = "privilege_type")
    @Column(name = "integer_col")
    private Map<Privilege, Integer> privileges = new HashMap<>();

	public T_User() {
		super();
	}

	public T_User(String firstName, String lastName, String username, String password, String position,
			Map<Privilege,Integer> privileges, boolean isActive, boolean isVisible) {

		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.position = position;
		this.privileges = privileges;
	}
	
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof T_User)) return false;
	    T_User o = (T_User) obj;
	    return o.userId == this.userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwrd) {
		this.password = passwrd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public Map<Privilege, Integer> getPrivileges() {

		return privileges;
	}

	public void setPrivileges(Map<Privilege, Integer> privileges) {
		this.privileges = privileges;
	}
	
	public void addPrivilege(Privilege priv) {
		privileges.put(priv, 1);
	}
	
	public boolean getIsActive() {
		return isActive;
	}
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public List<BusinessCase> getCases() {
		return cases;
	}

	public void setCases(List<BusinessCase> cases) {
		this.cases = cases;
	}
	
	public void addCases(BusinessCase c) {
		this.cases.add(c);
	}

	@Override
	public String toString() {
		return "T_User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", position=" + position + ", isActive=" + isActive
				+ ", isVisible=" + isVisible + ", privileges=" + privileges + "]";
	}
	
	public boolean containsPrivilege(Privilege p) {
		Set keyset = privileges.keySet();
		return  keyset.contains(p);

	}
	
	
}