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

import com.project.website.model.Department;
import com.project.website.model.DeptProgSubject;
import com.project.website.model.DeptProgrammes;
import com.project.website.repository.DepartmentRepository;
import com.project.website.repository.DeptProgRepositiory;

@CrossOrigin("*")
@RestController
public class DeptProgramsController {
	
	
	@Autowired
	private DeptProgRepositiory programmes;
	
	@Autowired
	private DepartmentRepository department;
	
	
	@PostMapping("api/v1/department/{departmentId}/programme")
	public ResponseEntity<DeptProgrammes> addProgramme(@RequestBody DeptProgrammes programmes,@PathVariable int departmentId)
	{
		Optional<Department> dept=department.findById(departmentId);
		if(!dept.isPresent())
		{
			throw new RuntimeException("No such department found");
		}
		programmes.setDepartment(dept.get());
		this.programmes.save(programmes);
	return new ResponseEntity<DeptProgrammes>(programmes,HttpStatus.OK);	
		
	}
	
	@GetMapping("api/v1/department/programme")
	public List<DeptProgrammes> getAllProgramme()
	{
		return programmes.findAll();
		
	}

	@GetMapping("api/v1/department/programme/{id}")
	public DeptProgrammes findProgrammeById(@PathVariable int id)
	{
		Optional<DeptProgrammes> optional=programmes.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Programmes found with programme id:"+id);
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/department/programme/{id}")
	public void deleteProgrammeNameById(@PathVariable int id)
	{
		programmes.deleteById(id);
	}
	
	
	@PutMapping("api/v1/department/programme/{id}")
	public ResponseEntity<DeptProgrammes> updateProgrammeNameById(@PathVariable int id, @RequestBody DeptProgrammes programmes)
	{
	
		Optional<DeptProgrammes> optional=this.programmes.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Degree found with degree id:"+id);
		}
		//get the department for the updated programme
		
		Optional<Department> dept=department.findById(((DeptProgrammes) optional.get()).getDepartmentId());
		if(!dept.isPresent())
		{
			throw new RuntimeException("No such department found");
		}
//		programmes.setDepartment(dept.get());
		programmes.setId(optional.get().getId());
		programmes.setDepartment(dept.get());
		this.programmes.save(programmes);
		return new ResponseEntity<DeptProgrammes>(programmes,HttpStatus.OK);	
	}


}
