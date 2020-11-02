package com.project.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.Student;
import com.project.website.model.Subscription;
import com.project.website.model.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {


	@Query("select m from Subscription m where m.student= ?1")
	Optional<Subscription> findByStudentId(Student student);

	@Query("select m from Subscription m where m.facultyId=?1 AND m.student= ?2 AND m.enroll=true")
	Optional<Subscription> findBySubscription(Integer facultyId, Student student);
	
	@Query("select count(m) from Subscription m where m.facultyId=?1 AND m.enroll=true")
	Optional<Subscription> findSubscriptionCount(Integer facultyId);

}
