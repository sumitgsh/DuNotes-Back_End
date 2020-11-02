package com.project.website.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest.Headers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.website.model.Faculty;
import com.project.website.model.Media;
import com.project.website.model.Student;
import com.project.website.model.User;
import com.project.website.repository.StudentRepository;
import com.project.website.repository.UserRepository;

import utility.Constants;

@RestController
@CrossOrigin("*")
public class StudentController {
	
	
//	public static final String UPLOAD_FOLDER = "D:\\EasyAccess.com\\website\\src\\main\\resources\\images\\";
//	
	@Autowired
	private StudentRepository student;
	
	@Autowired
	private UserRepository users;
	
	//Update Student data
	@PutMapping("api/v1/students/{id}")
	public ResponseEntity<String> addStudent(@RequestBody Student student,@PathVariable int id)
	{
		try
		{
		//First Get the id from user
		Optional<User> curUser=users.findById(id);	
		
		
		if(!curUser.isPresent()) {
			throw new RuntimeException("No Student found with student id:"+id);
		}

		Optional<Student> optional=this.student.findByUserId(curUser);
		
		if(optional.isPresent())
		{
			student.setId(optional.get().getId());
			
		}else{	
		student.setId(id);
		
		}
		
		student.setUser(curUser.get());
		this.student.save(student);
		
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Netwrok Error",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Successfully updated",HttpStatus.ACCEPTED);
	}

		
	@GetMapping("api/v1/students")
	public List<Student> getAllUsers()
	{
		return student.findAll();
		
	}
	
	@GetMapping("api/v1/students/{id}")
	public Student findUsersById(@PathVariable int id)
	{
		Optional<Student> optional=student.findById(id);
		
		if(!optional.isPresent()) {
			return null;
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/students/{id}")
	public void deleteStudentById(@PathVariable int id)
	{
		student.deleteById(id);
	}
	
	
	
	
}
