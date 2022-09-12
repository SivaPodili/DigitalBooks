package com.hnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hnt.entity.User;
import com.hnt.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@Mock
	UserService service;
	@InjectMocks
	UserController controller;

	@Test
	void testSaveUser1() {
		User user = new User();
		user.setId(1);
		when(service.save(user)).thenReturn(user);// mocking
		Integer savedUserId = controller.saveUser1(user);
		assertEquals(1, savedUserId);
	}
	
	void testGetUser() {
		Iterable<User> user=new ArrayList<User>();
		when(controller.getUser()).thenReturn(user);
		Iterable<User> userReturned=controller.getUser();
		assertEquals(userReturned,user);
	}
	
	
}
