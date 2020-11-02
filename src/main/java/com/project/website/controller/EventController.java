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

import com.project.website.model.Event;
import com.project.website.repository.EventRepository;

@RestController
public class EventController {

	
	@Autowired
	private EventRepository event;
	
	
	@PostMapping("api/v1/Event")
	public void addEvent(@RequestBody Event event)
	{
		this.event.save(event);
		
	}
	
	@GetMapping("api/v1/event")
	public List<Event> getAllEvents()
	{
		return event.findAll();
		
	}
	
	@GetMapping("api/v1/event/{id}")
	public Event findEventById(@PathVariable int id)
	{
		Optional<Event> optional=event.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Event found with event id:"+id);
		}
		return optional.get();
		
	}


	@DeleteMapping("api/v1/event/{id}")
	public void deleteEventById(@PathVariable int id)
	{
		event.deleteById(id);
	}
	
	
	@PutMapping("api/v1/event/{id}")
	public void updateEventById(@PathVariable int id, @RequestBody Event event) {
	
		Optional<Event> optional=this.event.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("No Event found with event id:"+id);
		}
		event.setId(optional.get().getId());
		this.event.save(event);
	}
	
	
}
