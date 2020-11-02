package com.project.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DeptProgSubject {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int Id;

@ManyToOne
private DeptProgrammes deptprog;

private String subjectName;
private String shortName;
private int semItBelongs;

public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}


public String getShortName() {
	return shortName;
}
public void setShortName(String shortName) {
	this.shortName = shortName;
}

//For getting the department name
public String getDepartment()
{
	return deptprog.getDepartment();
}

public int getDeptprogrammesId() {
	return deptprog.getId();
}

public String getDeptprogrammes() {
	return deptprog.getDegreeName();
}

public String getDeptprogShortName() {
	return deptprog.getShortName();
}
public void setDeptprogrammes(DeptProgrammes deptprog) {
	this.deptprog= deptprog;
}

public String getSubjectName() {
	return subjectName;
}
public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
}
public int getSemItBelongs() {
	return semItBelongs;
}
public void setSemItBelongs(int semItBelongs) {
	this.semItBelongs = semItBelongs;
}





}
