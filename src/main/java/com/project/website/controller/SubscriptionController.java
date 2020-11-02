package com.project.website.controller;

import java.time.LocalDate;
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

import com.project.website.model.Student;
import com.project.website.model.Subscription;
import com.project.website.model.User;
import com.project.website.repository.StudentRepository;
import com.project.website.repository.SubscriptionRepository;
import com.project.website.repository.UserRepository;


@CrossOrigin("*")
@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionRepository subscribe;
	
	@Autowired 
	private StudentRepository student;
	
	@Autowired UserRepository user;
	
	
	@PutMapping(value="api/v1/subscribe/{userId}/{facultyId}",produces="text/html")
	public ResponseEntity<String> addSubscription(@PathVariable Integer userId,@PathVariable Integer facultyId,@RequestBody Subscription subs)
	{
		
	//find whether he is a user or not
	Optional<User> curUser=user.findById(userId);
	if(!curUser.isPresent())                             
	{
		throw new RuntimeException("No User found with user id:"+userId);
	}
		
	
	//find whether the user is a Student or not
	Optional<Student> stud=student.findByUserId(curUser);
	 
	if(!stud.isPresent())
	{
		//http response saying please complete your profile section
		return new ResponseEntity<String>("Please complete your profile section",HttpStatus.ACCEPTED);
		
	}
	
	//check whether he has already subscribed
	Optional<Subscription> subscribeCheck=subscribe.findBySubscription(facultyId,stud.get());
	
	if(subscribeCheck.isPresent())
	{
		subs.setId(subscribeCheck.get().getId());
	}
	subs.setDateOfSubscription(LocalDate.now());
	subs.setEnroll(true);
	subs.setFacultyId(facultyId);
	subs.setStudent(stud.get());
	this.subscribe.save(subs);
	
	return new ResponseEntity<String>("Subscribed Succesfully",HttpStatus.ACCEPTED);
		
		
	}
	
	
	
	
	@GetMapping("api/v1/subscribeCheck/{userId}/{facultyId}")
	public  ResponseEntity<String> getSubscription(@PathVariable Integer userId,@PathVariable Integer facultyId)
	{
		
		//find whether he is a user or not
		Optional<User> curUser=user.findById(userId);
		if(!curUser.isPresent())                             
		{
			throw new RuntimeException("No User found with user id:"+userId);
		}
			
		
		//find whether the user is a Student or not
		Optional<Student> stud=student.findByUserId(curUser);
		 
		if(!stud.isPresent())
		{
			//http response saying please complete your profile section
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
			
		}
		
		//find whether the student has subscribed to faculty or not(enroll=true or false)
		Optional<Subscription> subscribeCheck=subscribe.findBySubscription(facultyId,stud.get());
		
		if(!subscribeCheck.isPresent())
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	//Get the followers count based on id
	@GetMapping("api/v1/subscribeCount/{id}")
	public Optional<Subscription> findSubscriptionCount(@PathVariable int id)
	{
		
		Optional<Subscription> followersCount=subscribe.findSubscriptionCount(id);
			
		
		return followersCount;
	}
	
	
	@GetMapping("api/v1/subscribe/{id}")
	public Subscription findSubscriptionById(@PathVariable int id)
	{
		Optional<Subscription> optional=subscribe.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Subscription found with subscribe id:"+id);
		}
		return optional.get();
		
	}
	
	//get the user subscripption details(left)
	@GetMapping("api/v1/subscribe/{userId}")
	public void findAllSubscriptionByUserId(@PathVariable int userId)
	{
		
	}
	
	
	//unsubscribe apis must also be there
		@PutMapping(value="api/v1/unsubscribe/{userId}/{facultyId}")
		public ResponseEntity<String> removeSubscription(@PathVariable Integer userId,@PathVariable Integer facultyId,@RequestBody Subscription sub)
		{
			
			//find whether he is a user or not
			Optional<User> curUser=user.findById(userId);
			if(!curUser.isPresent())                             
			{
				throw new RuntimeException("No User found with user id:"+userId);
			}
				
			
			//find whether the user is a Student or not
			Optional<Student> stud=student.findByUserId(curUser);
			 
			if(!stud.isPresent())
			{
				//http response saying please complete your profile section
				return new ResponseEntity<String>("Please complete your profile section",HttpStatus.ACCEPTED);
				
			}
			
			Optional<Subscription> subscribeCheck=subscribe.findBySubscription(facultyId,stud.get());
			
			sub.setId(subscribeCheck.get().getId());
			sub.setDateOfSubscription(subscribeCheck.get().getDateOfSubscription());
			sub.setEnroll(false);
			sub.setFacultyId(subscribeCheck.get().getFacultyId());
			sub.setStudent(subscribeCheck.get().getStudent());
			this.subscribe.save(sub);
			
			return new ResponseEntity<>("Un-Subscribed Successfully",HttpStatus.OK);
		}
		


	@DeleteMapping("api/v1/subscribe/{id}")
	public void deleteSubscriptionById(@PathVariable int id)
	{
		subscribe.deleteById(id);
	}
	
	
	
	
	

}
