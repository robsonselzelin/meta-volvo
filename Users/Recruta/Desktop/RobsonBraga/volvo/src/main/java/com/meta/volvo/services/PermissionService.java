package com.meta.volvo.services;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meta.volvo.entities.Permission;
import com.meta.volvo.repositories.IPermissionRepository;

@RestController
public class PermissionService {
	
	@Autowired
	IPermissionRepository permissionRepository;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/permissions/save", method = RequestMethod.POST)
	public ResponseEntity<Permission> save(HttpServletResponse response 
			, @RequestParam(name="name") String name
			, @RequestParam(name="description") String description) {
		
		Permission permission = new Permission();
		permission.setName(name);
		permission.setDescription(description);
		
		Permission result = permissionRepository.save(permission); 
		
		return ResponseEntity.ok(result);
		
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/permissions/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(HttpServletResponse response
			, @RequestParam("id") Long id
			, @RequestParam(name="name") String name
			, @RequestParam(name="description") String description) {
		
		Permission permission = permissionRepository.findOne(id);
		
		if (permission != null) {
			permission.setName(name);
			permission.setDescription(description);
			
			permission = permissionRepository.save(permission);
			
			return ResponseEntity.ok(permission);
		} else {
			return ServicesHelper.returnNotFound();
		}
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/permissions/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(HttpServletResponse response
			, @RequestParam("id") Long id) {
		
		Permission permission = permissionRepository.findOne(id);
		
		if (permission != null) {
			permissionRepository.delete(permission);		
			return ResponseEntity.ok().build();
		} else {
			return ServicesHelper.returnNotFound();
		}
		
	}
	
	@RequestMapping(value = "/permissions/list/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPermissions() {
		return ResponseEntity.ok(permissionRepository.findAll());
	}
	
	
}
