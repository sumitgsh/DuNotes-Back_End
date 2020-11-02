package com.project.website.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Faculty {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user_Id;
	
//	@OneToMany(mappedBy="faculty")
//	private List<FacultyTechingSub> sub;
//	
	private String description;
	private String qualification;
	private String department;
	
	private String teachingDept;
	private String subjectsTeach;
	

	
	
	public String getSubjectsTeach() {
		return subjectsTeach;
	}
	public void setSubjectsTeach(String subjectsTeach) {
		this.subjectsTeach = subjectsTeach;
	}
	public int getUser() {
		return user_Id.getId();
	}
	public String getFirstName()
	{
		return user_Id.getFirstName();
	}
	public String getLastName()
	{
		return user_Id.getLastName();
	}
	public void setUser(User user) {
		this.user_Id = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getTeachingDept() {
		return teachingDept;
	}
	public void setTeachingDept(String teachingDept) {
		this.teachingDept = teachingDept;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	
}
