package com.meta.volvo.services;

import org.springframework.http.ResponseEntity;

public class ServicesHelper {
	
	public static ResponseEntity<?> returnBadRequest() {
		return ResponseEntity.badRequest().build();
	}
	
	public static ResponseEntity<?> returnNotFound() {
		return ResponseEntity.notFound().build();
	}
	

}
