package com.project.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FacultyTechingSub {
	
	@Id
	@GeneratedValue
	private int id;
//
//	@ManyToOne
//	private Faculty faculty;
//	
	private String facultyDept;
	private String degreeType;
	private String teachingDeptName;
	private String subjects;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFacultyDept() {
		return facultyDept;
	}
	public void setFacultyDept(String facultyDept) {
		this.facultyDept = facultyDept;
	}
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getTeachingDeptName() {
		return teachingDeptName;
	}
	public void setTeachingDeptName(String teachingDeptName) {
		this.teachingDeptName = teachingDeptName;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	
	
	
	

}
