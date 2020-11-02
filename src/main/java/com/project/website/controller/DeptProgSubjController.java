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
import com.project.website.repository.DeptProgRepositiory;
import com.project.website.repository.DeptProgSubjRepository;

@CrossOrigin("*")
@RestController
public class DeptProgSubjController {
	
	
@Autowired
private DeptProgSubjRepository subject;

@Autowired
private DeptProgRepositiory programme;

@PostMapping("api/v1/department/{programme}/subject")
public ResponseEntity<DeptProgSubject> addSubjects(@RequestBody DeptProgSubject subject,@PathVariable String programme)
{
Optional<DeptProgrammes> prog=this.programme.findByProgramme(programme);
	if(!prog.isPresent())
	{
		throw new RuntimeException("No such Prog was  found");
	}
	System.out.print(subject.getSubjectName());
	
	subject.setDeptprogrammes(prog.get());
	this.subject.save(subject);
	return new ResponseEntity<DeptProgSubject>(subject,HttpStatus.OK);	
	
}

@GetMapping("api/v1/department/programme/subject")
public List<DeptProgSubject> getAllSubject()
{
	return subject.findAll();
	
}

@GetMapping("api/v1/department/programme/subject/{id}")
public DeptProgSubject findDeptDegreeById(@PathVariable int id)
{
	Optional<DeptProgSubject> optional=subject.findById(id);
	
	if(!optional.isPresent()) {
		throw new RuntimeException("No Subject found with subject id:"+id);
	}
	return optional.get();
	
}


@DeleteMapping("api/v1/department/programme/subject/{id}")
public ResponseEntity<String> deleteSubjectById(@PathVariable int id)
{
	//Delete the subject based on id
	try
	{
	subject.deleteById(id);
	}
	catch(Exception e)
	{
		return new ResponseEntity<String>("Subject id not found for Deletion..!!",HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<String>("Subject Deleted Successfully", HttpStatus.OK);
}


@PutMapping("api/v1/department/programme/subject/{id}")
public ResponseEntity<DeptProgSubject> updateUserById(@PathVariable int id, @RequestBody DeptProgSubject subject) 
{
	
	Optional<DeptProgSubject> optional=this.subject.findById(id);
	
		//get the department programme for the updated subject
	
		Optional<DeptProgrammes> prog=this.programme.findByProgramme(optional.get().getDeptprogrammes());
		if(!prog.isPresent())
		{
		throw new RuntimeException("No such department found");
		}
		subject.setId(optional.get().getId());
		subject.setDeptprogrammes(prog.get());
		this.subject.save(subject);
		
	return new ResponseEntity<DeptProgSubject>(subject,HttpStatus.OK);	

	}
}
