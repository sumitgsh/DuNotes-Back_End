package com.project.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.Faculty;
import com.project.website.model.User;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

	@Query("select m from Faculty m where m.user_Id = ?1")
	Optional<Faculty> findByUserId(Optional<User> curUser);

}