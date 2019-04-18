package com.companyA.service;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.companyA.domain.User;
import com.companyA.service.UserServiceImpl;

public class UserServiceImplTest {
	
	@Mock
	RestTemplateBuilder builder;
	
	UserServiceImpl userService;
	
	@Mock
	private RestTemplate restTemplateMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(builder.build()).thenReturn(restTemplateMock);
		userService = new UserServiceImpl(builder);
		ReflectionTestUtils.setField(userService, "restTemplate", restTemplateMock);
	}
	
	@Test
	public void createUser() {
		userService.createUser(new User());
		verify(restTemplateMock).postForEntity(Mockito.anyString(), 
				Mockito.any(HttpEntity.class), Mockito.any());
	}
}
