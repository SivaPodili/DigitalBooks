package com.bezkoder.springjwt.controllers;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthControllerTest {

	@Mock
	UserRepository userRepository;

	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	RoleRepository roleRepository;

	@InjectMocks
	AuthController authcontroller;

	@Mock
	SignupRequest signup;

	@Mock
	PasswordEncoder encoder;

	@Test
	public void registerUser_Email() {
		User user = new User();
      user.setEmail("siva@gmal.com");
      String ss=user.getEmail();
		when(userRepository.save(user)).thenReturn(user);// mocking
		ResponseEntity<?> singup = authcontroller.registerUser(signup);
		assertEquals(ss, singup);
	}
	
	@Test
	public void registerUser_username() {
		User user = new User();
      user.setUsername("siva");
      String s=user.getUsername();
		when(userRepository.save(user)).thenReturn(user);// mocking
		ResponseEntity<?> singup = authcontroller.registerUser(signup);
		assertEquals(s, singup);
	}
	
	

}
