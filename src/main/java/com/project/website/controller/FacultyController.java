package com.project.website.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.website.model.Faculty;
import com.project.website.model.User;
import com.project.website.repository.FacultyRepository;
import com.project.website.repository.MediaRepository;
import com.project.website.repository.UserRepository;

@CrossOrigin("*")
@RestController
public class FacultyController {

	@Autowired
	private FacultyRepository faculty;
	
	@Autowired
	private UserRepository users;
	

	
	@PutMapping("api/v1/faculty/{id}")
	public ResponseEntity<String> addFaculty(@RequestBody Faculty faculty,@PathVariable int id)
	{
		
		try
		{
			
			Optional<User> curUser=users.findById(id);
			
			if(!curUser.isPresent()) {
				throw new RuntimeException("No faculty found with faculty id:"+id);
			}
			
			Optional<Faculty> optional=this.faculty.findByUserId(curUser);
			if(optional.isPresent())
			{
				faculty.setId(optional.get().getId());
			}else {
			faculty.setId(id);
			}
			
			faculty.setUser(curUser.get());
			this.faculty.save(faculty);
		}
		catch(Exception e)
		{
		System.out.println(e);	
		}
		return new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("api/v1/faculty")
	public List<Faculty> getAllUsers()
	{
		return faculty.findAll();
		
	}
	
	@GetMapping("api/v1/faculty/{id}")
	public Faculty findFacultyById(@PathVariable int id)
	{
		
		Optional<Faculty> optional=faculty.findById(id);
		
		//get the facukty
		
		return optional.get();
		
	}

	@GetMapping("api/v1/faculty/uploadData/{id}")
	public User findFacultyUploadDataById(@PathVariable int id)
	{
		
		Optional<Faculty> optional=faculty.findById(id);
		
		//get the above faculty upload data
		
			Optional<User> data=users.findById(optional.get().getUser());
		
		
		return data.get();
		
	}

	@DeleteMapping("api/v1/faculty/{id}")
	public void deleteUserById(@PathVariable int id)
	{
		faculty.deleteById(id);
	}
	
}
