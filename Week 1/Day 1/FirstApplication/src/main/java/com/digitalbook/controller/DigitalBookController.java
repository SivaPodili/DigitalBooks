package com.digitalbook.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbook.model.Student;
import com.digitalbook.repository.StudentRepository;

@RestController
public class DigitalBookController {
	
	@Autowired
	private StudentRepository repository;
	
	@GetMapping("/getallstudentdetails")
	public List<Student> getAllStudentDetails(){
		return repository.findAll();
	}
	
	@GetMapping("/getstudentbyid/{id}")
	public Student getStudentById(@PathVariable("id") int id) {
		return repository.findById(id).get();
		
	}
	
	@PostMapping("/createstudent")
	public Student createStudent(@RequestBody Student student) {
		return repository.save(student);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
