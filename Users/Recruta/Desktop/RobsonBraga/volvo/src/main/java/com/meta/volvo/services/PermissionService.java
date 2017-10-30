package com.meta.volvo.services;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ResponseEntity<Permission> save(HttpServletResponse response, @RequestBody Permission permission) {

		Permission result = permissionRepository.save(permission);
		return ResponseEntity.ok(result);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/permissions/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(HttpServletResponse response, @PathVariable("id") Long id) {

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
