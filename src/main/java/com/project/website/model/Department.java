package com.project.website.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Departments")
@Entity
public class Department {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

@JoinColumn(name="user_id")
@ManyToOne
private User user;

@OneToMany(mappedBy = "department")
private List<DeptProgrammes> deptprogrammes;

private String department;
private boolean status;


public Department() {
	
}

public Department(int id) {
	super();
	this.id = id;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public boolean getStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}

public void setUser(User user) {
	this.user = user;
}
public int getUser()
{
	return user.getId();
}
public List<DeptProgrammes> getDeptprogrammes() {
	return deptprogrammes;
}

public void setDeptprogrammes(List<DeptProgrammes> deptprogrammes) {
	this.deptprogrammes = deptprogrammes;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String deptName) {
	this.department = deptName;
}





}
