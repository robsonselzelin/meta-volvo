package com.meta.volvo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity(name = "USER_PERMISSION")

public class UserPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239937328519818235L;
	
	@ManyToMany
	@JoinColumn(name = "USER_ID", nullable = false)
	private List<User> user;
	
	@ManyToMany
	@JoinColumn(name = "PERMISSION_ID", nullable = false)
	private List<Permission> permission;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

}
