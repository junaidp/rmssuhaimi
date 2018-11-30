package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

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

@Table(name="job")
public class Job   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobId")
	private int jobId;
	
	@Column(name="jobName")
	private String jobName;
	
	//@Column(name="client")
	//private String client;
	
	@Column(name="segment")
	private int segment;
	
	@Column(name="allocation")
	private int allocation;
	
	@Column(name="nature")
	private String nature;
	
	@Column(name="company")
	private int company;
	
	
	@JoinColumn(name = "lineofServiceId")
	@ManyToOne(fetch = FetchType.LAZY)
	private LineofService lineofServiceId;
	
	@JoinColumn(name = "domainId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Domains domainId;
	
	@JoinColumn(name = "countryId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Countries countryId;
	
//	@JoinColumn(name = "employee1")
//	@ManyToOne(fetch = FetchType.LAZY)
//	private User employee1;
	
	
	@Transient
	private ArrayList<JobEmployees> jobEmployeesList;
	
	@Transient ArrayList<JobAttributes> jobAttributes;
	
	@Transient ArrayList<TimeSheet> timeSheets;
	//adding new array list
	@Transient
	private ArrayList<JobActivityEntity> jobActivityEntity;                     
	
	@Column(name="status")
	private String status;
	
	public Job(){}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	

	public LineofService getLineofServiceId() {
		return lineofServiceId;
	}

	public void setLineofServiceId(LineofService lineofServiceId) {
		this.lineofServiceId = lineofServiceId;
	}

	public Domains getDomainId() {
		return domainId;
	}

	public void setDomainId(Domains domainId) {
		this.domainId = domainId;
	}



	public Countries getCountryId() {
		return countryId;
	}

	public void setCountryId(Countries countryId) {
		this.countryId = countryId;
	}

//	public User employee1() {
//		return employee1;
//	}
//
//	public void setUserId(User userId) {
//		this.employee1 = userId;
//	}


	public ArrayList<JobEmployees> getJobEmployeesList() {
		return jobEmployeesList;
	}

	public void setJobEmployeesList(ArrayList<JobEmployees> jobEmployeesList) {
		this.jobEmployeesList = jobEmployeesList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<JobAttributes> getJobAttributes() {
		return jobAttributes;
	}

	public void setJobAttributes(ArrayList<JobAttributes> jobAttributes) {
		this.jobAttributes = jobAttributes;
	}

	public ArrayList<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(ArrayList<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public ArrayList<JobActivityEntity> getJobActivityEntity() {
		return jobActivityEntity;
	}

	public void setJobActivityEntity(ArrayList<JobActivityEntity> jobActivityEntity) {
		this.jobActivityEntity = jobActivityEntity;
	}

	public int getSegment() {
		return segment;
	}

	public void setSegment(int segment) {
		this.segment = segment;
	}

	public int getAllocation() {
		return allocation;
	}

	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}

	

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	
	
}
