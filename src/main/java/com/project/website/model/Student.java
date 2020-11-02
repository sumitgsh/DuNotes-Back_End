package com.project.website.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user_Id;
	
	@OneToMany(mappedBy="student")
	private List<Subscription> subscribe;
	
	private String collegeName;
	private String departMent;
	private String degreeType;
	private String degreeName;

	
	private LocalDate dateOfAdm;
	private String currentSem;
	
	
	Student()
	{
		
	}
	
	public Student(User user)
	{
		this.user_Id=user;
	}
	
	


	public int getUser() {
		return user_Id.getId();
	}
	public void setUser(User user) {
		this.user_Id = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getDepartMent() {
		return departMent;
	}
	public void setDepartMent(String departMent) {
		this.departMent = departMent;
	}
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	
	
	public LocalDate getDateOfAdm() {
		return dateOfAdm;
	}
	public void setDateOfAdm(LocalDate dateOfAdm) {
		this.dateOfAdm = dateOfAdm;
	}
	public String getCurrentSem() {
		return currentSem;
	}
	public void setCurrentSem(String currentSem) {
		this.currentSem = currentSem;
	}
	
	
	
}
