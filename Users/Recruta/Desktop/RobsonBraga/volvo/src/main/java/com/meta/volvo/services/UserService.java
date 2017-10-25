package com.meta.volvo.services;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meta.volvo.entities.Department;
import com.meta.volvo.entities.Permission;
import com.meta.volvo.entities.User;
import com.meta.volvo.repositories.IDepartmentRepository;
import com.meta.volvo.repositories.IPermissionRepository;
import com.meta.volvo.repositories.IUserDepartmentRepository;
import com.meta.volvo.repositories.IUserPageRepository;
import com.meta.volvo.repositories.IUserPermissionRepository;
import com.meta.volvo.repositories.IUserRepository;

@RestController
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IUserPageRepository userPageRepository;
	
	@Autowired
	private IPermissionRepository permissionRepository;
	
	@Autowired
	private IUserPermissionRepository userPermissionRepository;
	
	@Autowired
	private IUserDepartmentRepository userDeptRepository;
	
	@Autowired
	private IDepartmentRepository deptRepository;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public ResponseEntity<User> save(HttpServletResponse response 
			, @RequestParam(name="name") String name
			, @RequestParam(name="description") String description) {
		
		User user = new User();
		user.setName(name);
		user.setDescription(description);
		
		User result = userRepository.save(user); 
		
		return ResponseEntity.ok(result);
		
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(HttpServletResponse response
			, @RequestParam("id") Long id
			, @RequestParam(name="name") String name
			, @RequestParam(name="description") String description) {
		
		User user = userRepository.findOne(id);
		
		if (user != null) {
			user.setName(name);
			user.setDescription(description);
			
			user = userRepository.save(user);
			
			return ResponseEntity.ok(user);
		} else {
			return ServicesHelper.returnNotFound();
		}
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(HttpServletResponse response
			, @RequestParam("id") Long id) {
		
		User user = userRepository.findOne(id);
		
		if (user != null) {
			userRepository.delete(user);		
			return ResponseEntity.ok(userRepository.save(user));
		} else {
			return ServicesHelper.returnNotFound();
		}
		
	}
	
	@RequestMapping(value = "/users/list/all", method = RequestMethod.GET)
	public Page<User> getAllUsers(Pageable pageable) {
		return userPageRepository.findAll(pageable);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/add/permission", method = RequestMethod.POST)
	public ResponseEntity<?> addPermission(HttpServletResponse response
			, @RequestParam("userId") Long userId
			, @RequestParam("permissionId") Long permissionId) {
		
		User user = userRepository.findOne(userId);		
		if (user != null) {
			Permission permission = permissionRepository.findOne(permissionId);
			if (permission != null) {
				userPermissionRepository.insertUserPermission(user.getId(), permission.getId());
				return ResponseEntity.ok().build();
			} else {
				return ServicesHelper.returnNotFound();
			}
		} else {
			return ServicesHelper.returnNotFound();
		}
		
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/add/department", method = RequestMethod.POST)
	public ResponseEntity<?> addDepartment(HttpServletResponse response
			, @RequestParam("userId") Long userId
			, @RequestParam("departmentId") Long departmentId) {
		
		User user = userRepository.findOne(userId);		
		if (user != null) {
			Department dept = deptRepository.findOne(departmentId);
			if (dept != null) {
				userDeptRepository.insertUserRepository(userId, departmentId);
				return ResponseEntity.ok().build();
			} else {
				return ServicesHelper.returnNotFound();
			}
		} else {
			return ServicesHelper.returnNotFound();
		}
		
	}

	
}
