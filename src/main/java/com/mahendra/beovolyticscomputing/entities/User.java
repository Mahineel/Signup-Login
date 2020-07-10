package com.mahendra.beovolyticscomputing.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column(name = "username")
	@NotNull
	@Size(min = 5, max = 150, message = "Username should be 5 characters")
	private String username;

	@Column(name = "email_id")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	private String emailId;

	@Column(name = "password")
	@NotNull
	@Size(min = 8, max=25, message = "Password Should be minimum 8 chars")
	private String password;

	@Column(name = "current_login")
	private Date currentLogin;

	@Column(name ="last_login")
	private Date lastLogin;
	
	@Column(name = "isactive")
	private boolean isActive;
	
	
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id" ), inverseJoinColumns = @JoinColumn(name ="role_id"))
	private Set<Role> roles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCurrentLogin() {
		return currentLogin;
	}

	public void setCurrentLogin(Date currentLogin) {
		this.currentLogin = currentLogin;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", emailId=" + emailId + ", password=" + password
				+ ", currentLogin=" + currentLogin + ", lastLogin=" + lastLogin + ", isActive=" + isActive + ", roles="
				+ roles + "]";
	}

}
