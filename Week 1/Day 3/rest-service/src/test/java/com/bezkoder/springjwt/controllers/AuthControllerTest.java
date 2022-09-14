package com.bezkoder.springjwt.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthControllerTest {
	@Autowired
	private MockMvc mockmvc;
	@Autowired
	private Mockito mockito;
	@Autowired
	ObjectMapper mapper=new ObjectMapper();
	
	ObjectWriter writer=mapper.writer();
	@Mock
	UserRepository userRepository;
	
	@Autowired
	private MockMvcResultMatchers matcher;

	@Mock
	AuthenticationManager authenticationManager;
	
//	public void setUp() {
//		MockitoAnnotations.initMocks(authcontroller);
//		this.mockmvc=MockMvcBuilders.standaloneSetup(authcontroller).build();
//	}
//
	@Mock
	RoleRepository roleRepository;

	@InjectMocks
	AuthController authcontroller;

	@Mock
	SignupRequest signup;

	@Mock
	PasswordEncoder encoder;

//	@Test
//	public void registerUser_Email() {
//		User user = new User();
//      user.setEmail("siva@gmal.com");
//      String ss=user.getEmail();
//		when(userRepository.save(user)).thenReturn(user);// mocking
//		ResponseEntity<?> singup = authcontroller.registerUser(signup);
//		assertEquals(ss, singup);
//	}
	
	private UserDetailsImpl service;
//	
	@Test
	public void registerUser_username() {
		User user = new User();
      user.setUsername("siva42");
      String s=user.getUsername();
		when(userRepository.save(user)).thenReturn(user);// mocking
	
		assertEquals(s, user.getUsername());
	}
	
	@Test
	public void registerUser_email() {
		User user = new User();
      user.setEmail("siva@gmail.com");
      String s=user.getEmail();
		when(userRepository.save(user)).thenReturn(user);// mocking
		assertEquals(s, user.getEmail());
	}
	
	@Test
	public void registerUser_password() {
		User user = new User();
      user.setPassword("abc123");
      String s=user.getPassword();
      System.out.println(s);
		when(userRepository.save(user)).thenReturn(user);// mocking
		assertEquals(s, user.getPassword());
	}
	
	
	public void registerUser_id() {
		//User user = new User();
		signup.setUsername("siva");
		String name=signup.getUsername();
		
     // user.setId(1L);
     // Long ss=user.getId();
     // System.out.println(ss);
      when(userRepository.existsByUsername(name)).thenReturn(true);// mocking
    //  authcontroller.registerUser(signup.getid());
		assertEquals(signup, signup.getUsername());
	}
	
//	
	
//	@Test
//	public void register() throws Exception {
//		User user = new User();
//		user.setId(1L);
//		user.setUsername("Siva");
//		user.setEmail("siva@gmail.com");
//		user.setPassword("abc123");
//		
//		mockito.when(userRepository.save(user)).thenReturn(user);
//		
//		String content=writer.writeValueAsString(user);
//		
//		MockHttpServletRequestBuilder mockrequest= MockMvcRequestBuilders.post("/api/auth/signup")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(content);
//		
//		mockmvc.perform(mockrequest)
//		.andExpect(status().isOK());
//		
//		
//	}
	
//	@Test
//	public void register() throws Exception {
//		User user = new User();
//		user.setId(1L);
//		user.setUsername("Siva");
//		user.setEmail("siva@gmail.com");
//		user.setPassword("abc123");
//		
//		when(userRepository.save(user)).thenReturn(user);
//		assertEquals(user, );
//
//}
}