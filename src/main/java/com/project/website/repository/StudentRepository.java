package com.project.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.Student;
import com.project.website.model.User;

public interface StudentRepository extends JpaRepository<Student,Integer> {

	@Query("select m from Student m where m.user_Id = ?1")
	Optional<Student> findByUserId(Optional<User> curUser);

}
