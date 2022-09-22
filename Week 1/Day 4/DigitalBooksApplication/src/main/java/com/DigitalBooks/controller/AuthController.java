package com.DigitalBooks.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.DigitalBooks.models.Author;
import com.DigitalBooks.models.Book;
import com.DigitalBooks.models.ERole;
import com.DigitalBooks.models.Payment;
import com.DigitalBooks.models.Role;
import com.DigitalBooks.payload.request.LoginRequest;
import com.DigitalBooks.payload.request.SignupRequest;
import com.DigitalBooks.payload.response.JwtResponse;
import com.DigitalBooks.payload.response.MessageResponse;
import com.DigitalBooks.payload.response.UserResponse;
import com.DigitalBooks.repository.AuthorRepository;
import com.DigitalBooks.repository.RoleRepository;
import com.DigitalBooks.security.jwt.JwtUtils;
import com.DigitalBooks.security.service.AuthorDetailsImpl;
import com.DigitalBooks.security.service.AuthorDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;


//import io.swagger.annotations.ApiResponses;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/digitalbooks/author")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private AuthorDetailsServiceImpl authorDetailsServiceImpl;
	
	

	@Autowired
	AuthorRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	Author author=new Author();

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		AuthorDetailsImpl userDetails = (AuthorDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
		
//	  @ApiResponses(value = { @ApiResponse(code = 400, message = "User Successfully Registered", content = @Content(schema = @Schema(implementation = UserResponse.class))),
//		      @ApiResponse(code = 404, message = "Pet not found") })


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Author user = new Author(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				
				case "ROLE_AUTHOR":
					Role modRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_READER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		//return ResponseEntity.of(Optional.of(user));
		return ResponseEntity.ok(new UserResponse(HttpStatus.OK));
				
		
	}
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PostMapping("/createbook")
	public ResponseEntity<?> createBook(@RequestBody Book authorCreateBook) {
		authorDetailsServiceImpl.createbookservice(authorCreateBook);
		//return ResponseEntity.of(Optional.of(book));
		 return ResponseEntity.ok(new UserResponse(HttpStatus.OK));
		
		
		
	}
		
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@GetMapping("/getallbooks")
	public List<Book> getAllBooks(){
		return authorDetailsServiceImpl.getAllBooksService();
		
	}
	
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PutMapping("/books/{bookid}")
	public Book updateBook(@RequestBody Book authorCreateBook, @PathVariable("bookid") int bookid) {
		authorCreateBook.setBookid(bookid);
		authorDetailsServiceImpl.updateBookDetails(authorCreateBook);
		return authorCreateBook;
		}
	
	
	
//	@PreAuthorize("hasRole('ROLE_READER')")
//    @GetMapping("/reader/{emailid}/books/{bookid}")
//	@ResponseBody
//	public ResponseEntity<?> readBooks(@PathVariable("emailid") String email, @PathVariable("bookid") String bookid) {
//		
//		Integer bookbid=Integer.parseInt(bookid);
//		Map<String,String> map=authorDetailsServiceImpl.readBookservice(email,bookbid);
//		ResponseEntity responseEntity=new ResponseEntity(map,HttpStatus.OK);
//		
//		return responseEntity;
//		
//		
//	}
//	
	@PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("/searchBooks")
    @ResponseBody
    public ResponseEntity<Map<String, String>> SearchBooks(@RequestParam String category,@RequestParam String author,@RequestParam String price,@RequestParam String publisher) throws JsonProcessingException {
        
        Integer priceInt=Integer.parseInt(price);
        List<Book> BooksList=authorDetailsServiceImpl.findByCatagoryAndAuthorAndPriceAndPublisher(category,author,priceInt,publisher);
            Map<String,String>result = new HashMap<>();
            BooksList.forEach(Book->{
                result.put("author",Book.getAuthor());
                result.put("catagory",Book.getCategory());
                result.put("publisheddate",Book.getPublisheddate());
                result.put("publisher",Book.getPublisher());
                result.put("title",Book.getTitle());
                result.put("price",String.valueOf(Book.getPrice()));
                
            });
            ResponseEntity responseEntity = new ResponseEntity(result , HttpStatus.OK);
        return responseEntity;
        }
	
	@PreAuthorize("hasRole('ROLE_READER')")
	@GetMapping("/books/{bookid}")
	public Book readBook(@PathVariable("bookid") int bookid) {
	
		return authorDetailsServiceImpl.readBook(bookid);
				
		
			
} 
	
	@PreAuthorize("hasRole('ROLE_READER')")
    @PostMapping("/book/buy")
    public ResponseEntity<?> newBookPayment(@RequestBody Payment payment)
    {
		authorDetailsServiceImpl.bookPayment(payment);
        return ResponseEntity.ok(new UserResponse(HttpStatus.OK));
    }
	
    }
	
	
