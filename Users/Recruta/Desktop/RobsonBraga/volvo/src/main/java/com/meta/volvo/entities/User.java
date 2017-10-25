package com.meta.volvo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "USER")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878339487358078070L;
	
	@Column(length = 8, nullable = false, name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(length = 50, nullable = false, name = "NAME")
	private String name;
	
	@Column(length = 50, nullable = false, name = "DESCRIPTION")
	private String description;
	
	@OneToMany
	@JoinColumn(name = "USER_ID", nullable = false)
	private List<UserPermission> permissions;
	
	@OneToMany
	@JoinColumn(name = "USER_ID", nullable = false)
	private List<UserDepartment> departments;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public List<UserDepartment> getDepartments() {
		return departments;
	}

	public void setDepartments(List<UserDepartment> departments) {
		this.departments = departments;
	}

}
