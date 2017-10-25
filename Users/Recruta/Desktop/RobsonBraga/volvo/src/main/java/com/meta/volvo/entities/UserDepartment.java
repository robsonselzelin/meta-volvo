package com.meta.volvo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "USER_DEPARTMENT")
public class UserDepartment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3515380140904375507L;
	
	@OneToOne
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false)
	private Department department;
	
	@OneToMany
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
