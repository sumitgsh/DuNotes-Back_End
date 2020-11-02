package com.project.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue
	private int id;
	private int mediaId;
	private int ratings;
	private boolean rated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMediaId() {
		return mediaId;
	}
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public boolean isRated() {
		return rated;
	}
	public void setRated(boolean rated) {
		this.rated = rated;
	}
	
	
	
	
}
