package com.project.website.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DeptProgrammes {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

@ManyToOne
private Department department;

@OneToMany(mappedBy="deptprog")
private List<DeptProgSubject> deptProgSub; 

private String degreeName;
private String shortName;
private int noOfSemester;

public int getDepartmentId() {
	return department.getId();
}
public String getDepartment() {
	return department.getDepartment();
}
public void setDepartment(Department department) {
	this.department = department;
}
public List<DeptProgSubject> getDeptProgSub() {
	return deptProgSub;
}

public void setDeptProgSub(List<DeptProgSubject> deptProgSub) {
	this.deptProgSub = deptProgSub;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getDegreeName() {
	return degreeName;
}
public void setDegreeName(String degreeName) {
	this.degreeName = degreeName;
}


public String getShortName() {
	return shortName;
}
public void setShortName(String shortName) {
	this.shortName = shortName;
}
public int getNoOfSemester() {
	return noOfSemester;
}
public void setNoOfSemester(int noOfSemester) {
	this.noOfSemester = noOfSemester;
}



}
