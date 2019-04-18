package com.companyA.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.companyA.domain.User;

/**
 * Calls Third party service to get data.
 */
@Service
public class UserServiceImpl implements UserService {
	
	private final RestTemplate restTemplate;

	@Value("${server.ip}")
	private String serverIp;
	
	@Value("${service.url}")
	private String serviceURL;
	
	public UserServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public ResponseEntity<?> createUser(User user) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = 
				new HttpEntity<>(createMultiPartRequest(user), headers);
		return restTemplate
				.postForEntity(serverIp + serviceURL, requestEntity, String.class); 
	}

	public MultiValueMap<String, String> createMultiPartRequest(User user) {
		MultiValueMap<String, String> multipartRequest = new LinkedMultiValueMap<String, String>();
		multipartRequest.add("firstname", user.getFirstName());
		multipartRequest.add("middlename", user.getMiddleName());
		multipartRequest.add("lastname", user.getLastName());
		multipartRequest.add("email", user.getEmail());
		multipartRequest.add("password", user.getPassword());
		multipartRequest.add("confirmation", user.getConfirmPassword());
		if(user.isSubscribed()) {
			multipartRequest.add("is_subscribed", "1");
		}
		multipartRequest.add("success_url", "");
		multipartRequest.add("error_url", "");
		return multipartRequest;
	}
}
