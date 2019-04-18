package com.test.controller;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.test.controller.UserController;
import com.test.domain.User;
import com.test.service.UserService;

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
