package com.leavemanagement.shared;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity

@Table(name="jobactivity")
public class JobActivityEntity   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobactivityId")
	private int jobActivityId;
	
	@JoinColumn(name = "jobId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Job jobId;

	
	@Column(name="planning")
	private Integer planning;

	@Column(name="execution")
	private Integer execution;
	
	@Column(name="reporting")
	private Integer reporting;
	
	@Column(name="designation")
	private Integer designation;
	
	
	@Column(name="followup")
	private Integer followup;

	public int getJobActivityId() {
		return jobActivityId;
	}

	public void setJobActivityId(int jobActivityId) {
		this.jobActivityId = jobActivityId;
	}

	public Job getJobId() {
		return jobId;
	}

	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}

	public Integer getPlanning() {
		return planning;
	}

	public void setPlanning(Integer planning) {
		this.planning = planning;
	}

	public Integer getExecution() {
		return execution;
	}

	public void setExecution(Integer execution) {
		this.execution = execution;
	}

	public Integer getReporting() {
		return reporting;
	}

	public void setReporting(Integer reporting) {
		this.reporting = reporting;
	}

	public Integer getFollowup() {
		return followup;
	}

	public void setFollowup(Integer followup) {
		this.followup = followup;
	}

	public Integer getDesignation() {
		return designation;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	
}
