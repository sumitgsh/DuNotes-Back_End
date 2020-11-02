package com.project.website.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.project.website.model.Department;
import com.project.website.model.DeptProgrammes;
import com.project.website.model.User;
import com.project.website.repository.DepartmentRepository;
import com.project.website.repository.UserRepository;

@CrossOrigin("*")
@RestController
public class DepartMentController {
	
	
	@Autowired
	private DepartmentRepository repo;
	
	@Autowired
	private UserRepository user;
	
	@PostMapping("api/v1/department")
	public ResponseEntity<Department> addDepartment(@RequestBody Department dept)
	{
		HttpHeaders headers = new HttpHeaders();
		Optional<User> aUser=this.user.findById(1);
		//find whether such department exist in the database
		String departName=dept.getDepartment().trim();
		List<Department> opt= this.repo.findByDepartment(departName);
		if(opt.size()>=1)
		{
		headers.add("message", "Department already exists");
		return new ResponseEntity<Department>(
				          dept,headers, HttpStatus.BAD_REQUEST);	
				
		}else {
		
		dept.setDepartment(dept.getDepartment());
		dept.setUser(aUser.get());
		dept.setStatus(true);
		repo.save(dept);
		}
	return new ResponseEntity<Department>(dept,HttpStatus.OK);
	}
	
	@GetMapping("api/v1/department")
	public List<Department> getAllUsers()
	{
		return repo.findAll();
		
	}
	
	@GetMapping("api/v1/department/{id}")
	public Department findDepartmentById(@PathVariable int id)
	{
		Optional<Department> optional=repo.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No User found with user id:"+id);
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/department/{id}")
	public void deleteDeptById(@PathVariable int id)
	{
		repo.deleteById(id);
	}
	
	//Update onn edit request
	@PutMapping("api/v1/department/{id}")
	public ResponseEntity<Department> updateDeptById(@PathVariable int id, @RequestBody Department dept) {
	
		Optional<Department> optional=repo.findById(id);
		//set the user
		Optional<User> aUser=this.user.findById(1);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Department found with department id:"+id);
		}
		
		dept.setId(optional.get().getId());
		dept.setStatus(optional.get().getStatus());
		dept.setUser(aUser.get());
		repo.save(dept);
		return new ResponseEntity<Department>(dept,HttpStatus.OK);
	}
	
	//Changing the status of the requested data
	@PutMapping("api/v1/department/status/{id}")
	public ResponseEntity<Department> updateDeptStatus(@PathVariable int id,@RequestBody Department dept) {
	
		Optional<Department> optional=repo.findById(id);
		
		Optional<User> aUser=this.user.findById(1);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Department found with department id:"+id);
		}
		
		dept.setId(optional.get().getId());
		if(!optional.get().getStatus())
		{
			dept.setStatus(true);
		}else
		{
			dept.setStatus(false);
		}
		dept.setUser(aUser.get());
		repo.save(dept);
		return new ResponseEntity<Department>(dept,HttpStatus.OK);
	}
	

}
