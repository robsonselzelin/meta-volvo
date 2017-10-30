package com.meta.volvo.services;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meta.volvo.entities.Department;
import com.meta.volvo.repositories.IDepartmentRepository;
import com.meta.volvo.repositories.IDeptPageRepository;

@RestController
public class DepartmentService {

	@Autowired
	IDepartmentRepository deptRepository;

	@Autowired
	IDeptPageRepository deptPageRepository;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/depts/save", method = RequestMethod.POST)
	public ResponseEntity<Department> save(HttpServletResponse response, @RequestBody Department dept) {

		Department result = deptRepository.save(dept);
		return ResponseEntity.ok(result);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/depts/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(HttpServletResponse response, @PathVariable("id") Long id) {

		Department dept = deptRepository.findOne(id);

		if (dept != null) {
			deptRepository.delete(dept);
			return ResponseEntity.ok().build();
		} else {
			return ServicesHelper.returnNotFound();
		}

	}

	@RequestMapping(value = "/depts/list/all", method = RequestMethod.GET)
	public Page<Department> getAllDepartments(Pageable pageable) {
		return deptPageRepository.findAll(pageable);
	}

}
