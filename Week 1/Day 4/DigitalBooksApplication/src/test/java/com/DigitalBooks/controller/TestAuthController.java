package com.DigitalBooks.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.DigitalBooks.models.Book;
import com.DigitalBooks.repository.BookRepository;
import com.DigitalBooks.security.jwt.AuthEntryPointJwt;
import com.DigitalBooks.security.service.AuthorDetailsServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(value=AuthController.class)
@SpringBootTest
public class TestAuthController {
	
	
		}
