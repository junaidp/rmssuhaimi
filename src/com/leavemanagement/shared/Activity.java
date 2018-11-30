package com.leavemanagement.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="activity")
public class Activity   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activityId")
	private int activityId;
	
	@Column(name="activityName")
	private String activityName;
	
	
	
	public Activity(){}



	public int getActivityId() {
		return activityId;
	}



	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}



	public String getActivityName() {
		return activityName;
	}



	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	
}
