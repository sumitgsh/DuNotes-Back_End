package com.project.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.Rating;
import com.project.website.model.User;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	
	 @Query("select avg(u.ratings) from Rating u where u.mediaId = ?1")
	 double  findAverageRating(int id);
	
}

