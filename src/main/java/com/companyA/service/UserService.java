package com.companyA.service;

import org.springframework.http.ResponseEntity;

import com.companyA.domain.User;

public interface UserService {
	public ResponseEntity<?> createUser(User user);
}
