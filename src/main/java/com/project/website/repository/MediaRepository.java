package com.project.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.Media;
import com.project.website.service.PaperType;

public interface MediaRepository extends JpaRepository<Media, Integer> {
	
	
	 

		//SELECT all the elments that are not verified
		 @Query("select m from Media m where m.verified=?1")
		 List<Media> findByMediaUnverified(boolean verified);
	 
		//SELECT all the elments that are verified
		 @Query("select m from Media m where m.verified=?1")
		 List<Media> findByMediaVerified(boolean verified);
		 
}
 