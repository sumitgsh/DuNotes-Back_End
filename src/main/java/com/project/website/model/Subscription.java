package com.project.website.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Subscription {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Student student;
	private int facultyId;
	private boolean enroll;
	private LocalDate dateOfSubscription;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	
	public boolean isEnroll() {
		return enroll;
	}
	public void setEnroll(boolean enroll) {
		this.enroll = enroll;
	}
	public LocalDate getDateOfSubscription() {
		return dateOfSubscription;
	}
	
	public void setDateOfSubscription(LocalDate dateOfSubscription) {
		this.dateOfSubscription = dateOfSubscription;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student stud) {
		this.student = stud;
	}
	
	
	
	
}
