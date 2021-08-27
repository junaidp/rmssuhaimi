package com.leavemanagement.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.leavemanagement.shared.Activity;
import com.leavemanagement.shared.AttributeRating;
import com.leavemanagement.shared.Company;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.JobUsersDTO;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeaveTypes;
import com.leavemanagement.shared.LeavesDTO;
import com.leavemanagement.shared.LeavesDTOForAllUsers;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.Roles;
import com.leavemanagement.shared.SubLineofService;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.TimeSheetReportDTO;
import com.leavemanagement.shared.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException, Exception;

	ArrayList<LeavesDTO> fetchLeavesRemaining(User userId) throws IllegalArgumentException, Exception;

	User signIn(String userid, String password) throws Exception;

	ArrayList<LeaveTypes> fetchLeaveTypes() throws IllegalArgumentException, Exception;

	String saveLeaveRequest(LeaveRecord leaveRecord) throws Exception;

	String fetchDatesDifference(Date from, Date to) throws Exception;

	ArrayList<LeaveRecord> fetchLeavesRecord(int userId) throws Exception;

	String approveDeclineRequest(LeaveRecord leaveRecord) throws Exception;

	String fetchOldPassword(int userId) throws Exception;

	String updatePassword(User user) throws Exception;

	String addUser(User user) throws Exception;

	String addCompany(Company company, User user) throws Exception;

	ArrayList<User> fetchAllUsersExceptDirector() throws Exception;

	ArrayList<User> fetchAllUsers() throws Exception;

	ArrayList<LeaveRecord> fetchPendingLeavesRecord() throws Exception;

	ArrayList<LeavesDTOForAllUsers> fetchLeavesRemainingForAllUsers() throws IllegalArgumentException, Exception;

	ArrayList<LeaveRecord> fetchPendingLeavesRecordOfLoggedInUser(int userId) throws Exception;

	ArrayList<Roles> fetchAllRoles() throws IllegalArgumentException, Exception;

	String deleteUser(int userId) throws Exception;

	JobAttributesDTO fetchJobAttributes() throws Exception;

	String saveJob(Job job) throws Exception;

	ArrayList<Job> fetchJobs(User loggedInUser) throws Exception;

	ArrayList<Domains> fetchDomains(int lineOfServiceId) throws Exception;

	ArrayList<LineofService> fetchLineOfService(int domainId) throws Exception;

	ArrayList<Domains> fetchDomains() throws Exception;

	ArrayList<SubLineofService> fetchSublineofServices(int domainId) throws Exception;

	String updatePhase(Phases phase) throws Exception;

	String deletePhase(Phases phase) throws Exception;

	String deleteJobEmployee(int jobEmployeeI) throws Exception;

	String deleteJob(int jobId) throws Exception;

	String saveJobAttribute(JobAttributes jobAttributes);

	String deleteJobAttribute(int jobAttributeId);

	String saveTimeSheet(ArrayList<TimeSheet> timeSheet);

	ArrayList<TimeSheetReportDTO> fetchTimeReport(int selectedJob, int selectedMonth, int selecteduser,
			int selectedJobType);

	ArrayList<LineofService> getLineOfServices() throws Exception;

	String saveRating(AttributeRating attributeRating) throws Exception;

	Integer fetchMonthAllowedhours(int month) throws Exception;

	ArrayList<Job> fetchJobsForTimeSheet(User loggedInUser, boolean chargeable, int selectedMonth2, int selectedYear2)
			throws Exception;

	ArrayList<JobUsersDTO> fetchUsersWithJobs() throws Exception;

	ArrayList<AttributeRating> fetchjobUserRating(int userId, int jobId) throws Exception;

	ArrayList<Job> fetchAllJobs() throws Exception;

	String closeJob(int jobId) throws Exception;

	String fetchJobWiseReport(HashMap<String, Integer> map);

	String fetchAllReport(HashMap<String, Integer> map);

	String fetchAllReportPDF(HashMap<String, Integer> map);

	String logOut();

	User fetchLoggedInUser();

	ArrayList<Activity> fetchActivityReport(int lineOfServiceId) throws Exception;

	ArrayList<Job> fetchSelectedJobForTimeSheet(User loggedInUser, boolean chargeable, int jobId) throws Exception;

	ArrayList<Job> fetchJobsWithStatus(String status) throws Exception;
}
