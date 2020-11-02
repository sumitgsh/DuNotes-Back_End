package com.project.website.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.project.website.service.*;



@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy = "user_Id",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private Student student;
	
	@OneToOne(mappedBy = "user_Id",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private Faculty faculty;
	
	@OneToMany(mappedBy = "user",
				fetch = FetchType.LAZY,
				cascade = CascadeType.ALL)
	private List<Media> media;
	
	
	@OneToMany(mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<Event> event;
	
	@OneToMany(mappedBy = "user",
				cascade = CascadeType.ALL)
	private List<Department> department;
	
	
	
	
	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String phoneNo;
	private String password;

	
	@Enumerated
	private Role role;
	
	private String photoUrl;
	private LocalDate registerDate;
	private boolean coordinator;
	private boolean profileActive;
	private String college_Id_No;
	private String token;
	
	public User()
	{
		
	}
	
	

	public User( String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	
	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Media> getMedia() {
		return media;
	}
	public void setMedia(List<Media> media) {
		
		this.media = media;
	}
	
	public String getCollege_Id_No() {
		return college_Id_No;
	}
	public void setCollege_Id_No(String college_Id_No) {
		this.college_Id_No = college_Id_No;
	}
	public List<Event> getEvent() {
		return event;
	}
	public void setEvent(List<Event> event) {
		this.event = event;
	}
	public List<Department> getDepartment() {
		return department;
	}
	public void setDepartment(List<Department> department) {
		this.department = department;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isCoordinator() {
		return coordinator;
	}
	public void setCoordinator(boolean coordinator) {
		this.coordinator = coordinator;
	}
	public boolean isProfileActive() {
		return profileActive;
	}
	public void setProfileActive(boolean profileActive) {
		this.profileActive = profileActive;
	}
	
	
	
}
