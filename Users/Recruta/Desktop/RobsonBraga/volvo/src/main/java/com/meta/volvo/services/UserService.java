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

import com.meta.volvo.entities.User;
import com.meta.volvo.repositories.IUserRepository;

@RestController
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public ResponseEntity<User> save(HttpServletResponse response, @RequestBody User user) {

		User result = userRepository.save(user);
		return ResponseEntity.ok(result);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(HttpServletResponse response, @PathVariable("id") Long id) {

		User user = userRepository.findOne(id);

		if (user != null) {
			userRepository.delete(user);
			return ResponseEntity.ok().build();
		} else {
			return ServicesHelper.returnNotFound();
		}

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/list/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

}
