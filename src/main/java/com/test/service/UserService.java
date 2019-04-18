package com.test.service;

import org.springframework.http.ResponseEntity;

import com.test.domain.User;

public interface UserService {
	public ResponseEntity<?> createUser(User user);
}
