package com.project.website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {


	User findByUserName(String userName);
	
	 @Query("select u from User u where u.userName = ?1 AND u.password=?2")
	 Optional<User> findByUserNameAndPas(String userName,String password);
	 
	 @Query("select u from User u where u.token = ?1 ")
	 User findByToken(String token);

}
