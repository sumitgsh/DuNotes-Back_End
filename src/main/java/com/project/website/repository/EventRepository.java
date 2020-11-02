package com.project.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.website.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
