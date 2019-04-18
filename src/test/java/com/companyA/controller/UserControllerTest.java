package com.companyA.controller;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.companyA.controller.UserController;
import com.companyA.domain.User;
import com.companyA.service.UserService;

public class UserControllerTest {
	
	UserController userController = new UserController();
	
	@Mock
	private UserService userServiceMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(userController, "userService", userServiceMock);
	}
	
	@Test
	public void createUser() {
		User user = new User();
		userController.createUser(user);
		verify(userServiceMock).createUser(user);
	}
}
