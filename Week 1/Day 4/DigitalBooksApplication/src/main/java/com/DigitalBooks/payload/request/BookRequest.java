package com.DigitalBooks.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookRequest {
	
	@NotBlank
	@Size(min=5, max=20)
	private String username;
	@NotBlank
	@Size(min=5, max=20)
	@Email
	private String email;
	private String bookid;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	
	
	

}
