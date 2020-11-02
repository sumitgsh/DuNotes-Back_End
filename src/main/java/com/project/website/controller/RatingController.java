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

import com.project.website.model.Rating;
import com.project.website.repository.RatingRepository;


@CrossOrigin("*")
@RestController
public class RatingController {
	
	@Autowired
	private RatingRepository rating;
	
	@CrossOrigin("*")
	@PostMapping("api/v1/media/rate")
	public void addRating(@RequestBody Rating rating)
	{			
		this.rating.save(rating);
		
	}
	
	@GetMapping("api/v1/rating")
	public List<Rating> getAllRating()
	{
		return rating.findAll();
		
	}
	
	@GetMapping("api/v1/rating/{id}")
	public ResponseEntity<Double> findRatingById(@PathVariable int id)
	{
		double avg= rating.findAverageRating(id);
		
		return new ResponseEntity<>(avg, HttpStatus.ACCEPTED);
		
	}


	@DeleteMapping("api/v1/rating/{id}")
	public void deleteRatingById(@PathVariable int id)
	{
		rating.deleteById(id);
	}
	
	
	@PutMapping("api/v1/rating/{id}")
	public void updateRatingById(@PathVariable int id, @RequestBody Rating rating) {
	
		Optional<Rating> optional=this.rating.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Rating found with rating id:"+id);
		}
		rating.setId(optional.get().getId());
		this.rating.save(rating);
	}
	

}
