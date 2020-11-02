package com.project.website.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.website.service.PaperType;

@Entity
public class Media {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	//@JsonIgnore
	@JoinColumn(name="user_id")
	private User user;
	
	private String paperType; 
	  
	private String department;
	private String degreeName;
	private String subject;	
	private String paperYear;
	private String semester;
	private String topic;
	private String description;
	private String  importantLinks;
	private String downloadUri;
	private String fileName;
	private String fileType;
	private Long fileSize;
	private LocalDate dateOfUpload;
	private Boolean verified;  
	
	//Constructor
	
	public Media()
	{
		
	}
	
	
	public Media(String fileName,Long fileSize,String fileType)
	{
		this.fileName=fileName;
		this.fileSize=fileSize;
		this.fileType=fileType;
	
	}
	
	
	
	
	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUser() {
		return user.getUserName();
	}
	
	
	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	public String getImportantLinks() {
		return importantLinks;
	}
	public void setImportantLinks(String importantLinks) {
		this.importantLinks = importantLinks;
	}
	public String getDesc() {
		return description;
	}
	public void setDesc(String desc) {
		this.description = desc;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPath() {
		return fileName;
	}
	public void setPath(String path) {
		this.fileName = path;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getPaperYear() {
		return paperYear;
	}
	public void setPaperYear(String paperYear) {
		this.paperYear = paperYear;
	}
	public LocalDate getDateOfUpload() {
		return dateOfUpload;
	}
	public void setDateOfUpload(LocalDate dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	
}
