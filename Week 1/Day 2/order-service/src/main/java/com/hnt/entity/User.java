package com.hnt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class User {//not a spring bean
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Name cannot be blank#######")
	private String name;
	@Min(value = 1, message = "age cannot be less than 1")
	private int age;
	@NotEmpty
	private String dob;
	public void setAge(int i) {
		this.age = i;
	}
	public Integer getId() {
		return this.id;
	}
	public Object getName() {
		return this.name;
	}
	public void setId(int i) {
		this.id = i;
		
	}
	public void setName(String string) {
		this.name = string;
		
	}
}
