package com.project.website.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.website.model.FacultyTechingSub;
import com.project.website.repository.FacTeachRepository;



@RestController
public class FacultyTeachSubController {
	
	@Autowired
	private FacTeachRepository FacTeach;
	
	
	@PostMapping("api/v1/subjects")
	public void addSubjects(@RequestBody FacultyTechingSub sub)
	{
		FacTeach.save(sub);
		
	}
	
	@GetMapping("api/v1/subjects")
	public List<FacultyTechingSub> getAllSubjects()
	{
		return FacTeach.findAll();
		
	}
	
	@GetMapping("api/v1/subjects/{id}")
	public FacultyTechingSub findSubById(@PathVariable int id)
	{
		Optional<FacultyTechingSub> optional=FacTeach.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Subjects found with subjects id:"+id);
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/subjects/{id}")
	public void deleteSubById(@PathVariable int id)
	{
		FacTeach.deleteById(id);
	}
	
	
	@PutMapping("api/v1/subjects/{id}")
	public void updateSubById(@PathVariable int id, @RequestBody FacultyTechingSub sub) {
	
		Optional<FacultyTechingSub> optional=FacTeach.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Event found with event id:"+id);
		}
		sub.setId(optional.get().getId());
		FacTeach.save(sub);
	}
	
	

}
