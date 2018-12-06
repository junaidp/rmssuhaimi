package com.leavemanagement.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Attr;
import com.leavemanagement.client.view.JobCreationView;
import com.leavemanagement.shared.AttributeRating;
import com.leavemanagement.shared.Company;
import com.leavemanagement.shared.Countries;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.HibernateDetachUtility;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobActivityEntity;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.JobEmployees;
import com.leavemanagement.shared.JobUsersDTO;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeaveTypes;
import com.leavemanagement.shared.LeavesDTO;
import com.leavemanagement.shared.LeavesDTOForAllUsers;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.MonthAllowedHours;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.Roles;
import com.leavemanagement.shared.SubLineofService;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.TimeSheetReportDTO;
import com.leavemanagement.shared.User;
import com.leavemanagement.shared.UserReportDTO;

public class MySQLRdbHelper {

	private SessionFactory sessionFactory;
	Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User getAuthentication(String userid, String password) throws Exception
	{

		User users = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();

			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("name", userid));
			crit.add(Restrictions.eq("password", password));
			crit.add(Restrictions.eq("status", "active"));
			crit.createAlias("roleId", "role");
			crit.createAlias("companyId", "company");
			List rsList = crit.list();
			for(Iterator it=rsList.iterator();it.hasNext();	)
			{
				users = (User)it.next();
				System.out.println(users.getName() +" Signed In on"+ new Date());
			}

		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getAuthentication", ex.getMessage()), ex);
			System.out.println("Exception occured in getAuthentication"+ ex.getMessage());

			throw new Exception("Exception occured in getAuthentication");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

		return users;
	}

	public ArrayList<LeavesDTO> fetchAvailableLeaves(User userId) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			ArrayList<LeavesDTO> leavesDTOs = new ArrayList<LeavesDTO>();
			ArrayList<LeaveTypes> leaveTypes =  fetchLeaveTypes();
			for(int i=0; i< leaveTypes.size(); i++){
				LeavesDTO leavesDTO = new LeavesDTO();
				int leaveTypeId = leaveTypes.get(i).getLeaveTypeId();
				long leaveAvailedforThisLeaveType  = fetchCurrentUserLeaveforSelectedLeaveType(leaveTypeId, userId.getUserId(), session);
				leavesDTO.setLeaveType(leaveTypes.get(i));
				int allowed = 0;
				if(leaveTypeId ==4){
					allowed = userId.getExamLeaves();
				}
				else{
					allowed = leaveTypes.get(i).getAllowed();
				}
				long available = allowed - leaveAvailedforThisLeaveType;
				leavesDTO.setLeavesAvaible(available);
				leavesDTOs.add(leavesDTO);
			}
			return leavesDTOs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAvailableLeaves", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchAvailableLeaves"+ ex.getMessage());

			throw new Exception("Exception occured in fetchAvailableLeaves");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

	}

	private long fetchCurrentUserLeaveforSelectedLeaveType(int leaveTypeId,
			int userId, Session session) throws Exception {
		try{
			Criteria crit = session.createCriteria(LeaveRecord.class);
			crit.createAlias("userId", "user");
			crit.createAlias("user.roleId", "role");
			crit.createAlias("user.companyId", "company");
			crit.add(Restrictions.ne("user.status", "inActive"));
			crit.createAlias("leaveTypeId", "lType");
			crit.add(Restrictions.eq("user.userId", userId));
			crit.add(Restrictions.eq("lType.leaveTypeId", leaveTypeId));
			crit.add(Restrictions.eq("status", "Approved"));
			List rsList = crit.list();
			long totalDays = 0;
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				LeaveRecord leaveRecord =  (LeaveRecord)it.next();

				//				long diff = Math.abs(leaveRecord.getStartDate().getTime() - leaveRecord.getEndDate().getTime());
				//				
				long diff = getDiffInDates(leaveRecord.getStartDate(), leaveRecord.getEndDate());
				//				long diffDays = diff / (24 * 60 * 60 * 1000);
				//				diffDays =diffDays+1;
				//				long diffDays = (diff + 12 * 60 * 60 * 1000) / (24 * 60 * 60 * 1000)
				totalDays = totalDays+ diff+1;
			}
			return totalDays;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchCurrentUserLeaveforSelectedLeaveType", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchCurrentUserLeaveforSelectedLeaveType"+ ex.getMessage());

			throw new Exception("Exception occured in fetchCurrentUserLeaveforSelectedLeaveType");//Add this Line Accordingly in all method
		}
		finally{
			//			session.close();
		}

	}

	public long getDiffInDates(Date date1, Date date2)throws Exception{
		//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//	    Date date1 = df.parse("10/08/2013");
		//	    Date date2 = df.parse("21/08/2013");
		long numberOfDays = 0;
		try{
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date1);
			cal2.setTime(date2);

			while (cal1.before(cal2)) {
				if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
						&&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
					numberOfDays++;
					cal1.add(Calendar.DATE,1);
				}else {
					cal1.add(Calendar.DATE,1);
				}
			}


		}catch(Exception ex){
			System.out.println(ex);
		}
		return numberOfDays;
	}

	public ArrayList<LeaveTypes> fetchLeaveTypes() throws Exception{

		Session session = null;
		ArrayList<LeaveTypes> leaveTypes = new ArrayList<LeaveTypes>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(LeaveTypes.class);
			List rsList = crit.list();
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				LeaveTypes leaveType =  (LeaveTypes)it.next();
				leaveTypes.add(leaveType);
			}
			return leaveTypes;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchLeaveTypes", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchLeaveTypes"+ ex.getMessage());

			throw new Exception("Exception occured in getAuthentication");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public User fetchUser(int userId)throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("id", userId));
			crit.createAlias("roleId", "role");
			crit.createAlias("companyId", "company");
			User user = (User) crit.list().get(0);
			return user;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchUser", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchUser"+ ex.getMessage());

			throw new Exception("Exception occured in fetchUser");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public String fetchUsersEmail(int userId,Session session){
		String email ="";
		try{
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("userId", userId));
			User user = (User) crit.list().get(0);
			email = user.getEmail();
			
		}catch(Exception ex){
			System.out.println("fail fetchuseremail");
		}
		return email;
	}
	
	public String saveLeaveRequest(LeaveRecord leaveRecord) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.save(leaveRecord);
			User user = fetchUser(leaveRecord.getUserId().getReportingTo());
			session.flush();
			sendEmail("Leave Request Received: from "+ leaveRecord.getUserId().getName() +", Reason : " +leaveRecord.getReason(), user.getEmail(), "mfaheempiracha@gmail.com", "Leave Request Received");

		}catch(Exception ex){
			logger.warn(String.format("Exception occured in saveLeaveRequest", ex.getMessage()), ex);
			System.out.println("Exception occured in saveLeaveRequest"+ ex.getMessage());

			throw new Exception("Exception occured in saveLeaveRequest");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
		return "Leave Request Submitted";
	}

	public ArrayList<LeaveRecord> fetchLeaveRecord(int userId, int companyId) throws Exception {
		Session session = null;
		ArrayList<LeaveRecord> leaveRecords = new ArrayList<LeaveRecord>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(LeaveRecord.class);
			crit.createAlias("userId", "user");
			crit.createAlias("user.roleId", "role");
			crit.createAlias("user.companyId", "company");
			crit.add(Restrictions.ne("user.status", "inActive"));
			crit.createAlias("leaveTypeId", "lType");
			crit.add(Restrictions.eq("company.companyId", companyId));
			crit.add(Restrictions.ne("status", "pending"));
			crit.addOrder(Order.desc("startDate"));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				LeaveRecord leaveRecord =  (LeaveRecord)it.next();
				leaveRecords.add(leaveRecord);
				long diff = Math.abs(leaveRecord.getStartDate().getTime() - leaveRecord.getEndDate().getTime());
				long days = diff / (24 * 60 * 60 * 1000);
				days = days+1;
				leaveRecord.setDays(days+"");

			}
			return leaveRecords;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchLeaveRecord", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchLeaveRecord"+ ex.getMessage());

			throw new Exception("Exception occured in fetchLeaveRecord");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

	}

	private boolean leavesAvailable(User userId, int leaveTypeId, int noOfDays)throws Exception {
		boolean leavesAvailable = false;
		try{
			ArrayList<LeavesDTO> leavesDTO = fetchAvailableLeaves(userId);
			if(leavesDTO!=null){
				for(int i=0; i< leavesDTO.size(); i++){
					if(leavesDTO.get(i).getLeaveType().getLeaveTypeId() == leaveTypeId && leavesDTO.get(i).getLeavesAvaible()-noOfDays>=0){
						leavesAvailable = true;
						break;
					}
				}
			}
			return leavesAvailable;
		}catch(Exception ex){
			System.out.println("error in leavesAvailable");
			throw new Exception("Exception occured in leavesAvailable");
		}

	}

	public String approveDeclineRequest(LeaveRecord leaveRecord) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			boolean notAvailable = leaveRecord.getStatus().equalsIgnoreCase("Approved") && !leavesAvailable(leaveRecord.getUserId(),leaveRecord.getLeaveType().getLeaveTypeId(), Integer.parseInt(leaveRecord.getDays()));
			if(notAvailable){

				return "Not enough required number of leaves available for this leave type";
			}

			session.update(leaveRecord);
			session.flush();
			sendEmail("Your Leave Application for "+ leaveRecord.getLeaveType().getLeaveTypeName() +" has been " + leaveRecord.getStatus() , leaveRecord.getUserId().getEmail(), "", "Leave Application : "+ leaveRecord.getStatus());

			if(leaveRecord.getStatus().equalsIgnoreCase("Approved")){
				return "Request Approved";
			}else{
				return "Request Declined";
			}
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in approveRequest", ex.getMessage()), ex);
			System.out.println("Exception occured in approveRequest"+ ex.getMessage());

			throw new Exception("Exception occured in approveRequest");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

	}

	public String fetchOldPassword(int userId) throws Exception {
		Session session = null;
		String oldPassword = "";
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("id", userId));
			crit.createAlias("roleId", "role");
			crit.createAlias("companyId", "company");
			if(crit.list().size()>0){
				User user = (User) crit.list().get(0);
				oldPassword = user.getPassword();
			}
			return oldPassword;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchOldPassword", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchOldPassword"+ ex.getMessage());

			throw new Exception("Exception occured in fetchOldPassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public String updatePassword(User user) throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.update(user);
			session.flush();
			return "Password Updated";
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePassword", ex.getMessage()), ex);
			System.out.println("Exception occured in updatePassword"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public String addUser(User user) throws Exception {
		Session session = null;
		boolean newUser = false;
		if(user.getUserId()==0){
			newUser=true;
		}
		try{
			session = sessionFactory.openSession();
			session.saveOrUpdate(user);
			session.flush();
			if(newUser){
				return "User Added";
			}else{
				return "Record updated";
			}
		}
		catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePassword", ex.getMessage()), ex);
			System.out.println("Exception occured in updatePassword"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}
	
	


	public boolean sendEmail(String body, String sendTo, String cc, String subject) {

		final String username = "hyphenconsult@gmail.com";
		final String password = "ilzhkshpmtqduzuc";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		javax.mail.Session sessionMail = javax.mail.Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {



			Message message = new MimeMessage(sessionMail);
			message.setFrom(new InternetAddress("hyphenconsult@gmail.com"));
			if(cc.equals("")){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
			}else{
				//////
				String addresses[] = { sendTo, cc};

				InternetAddress[] addressTo = new InternetAddress[addresses.length];
				for (int i = 0; i < addresses.length; i++)
				{
					addressTo[i] = new InternetAddress(addresses[i]);
				}

				message.setRecipients(Message.RecipientType.TO, addressTo);
				/////
			}

			message.setSubject(subject);
			message.setContent(body, "text/html");


			Transport.send(message);

			System.out.println("email sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return false;
	}

	public ArrayList<User> fetchAllUsersExceptDirector(int companyId) throws Exception {
		Session session = null;
		ArrayList<User> users = new  ArrayList<User>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(User.class);
			crit.createAlias("roleId", "role");
			crit.createAlias("companyId", "company");
			crit.add(Restrictions.eq("company.companyId", companyId));
			crit.add(Restrictions.ne("role.roleId", 5));
			crit.add(Restrictions.ne("status", "inActive"));
			crit.add(Restrictions.ne("status", "Closed"));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				User user =  (User)it.next();
				users.add(user);
			}
			return users;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAllUsers", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchAllUsers"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

	}

	public ArrayList<User> fetchAllUsers(int companyId) throws Exception {
		Session session = null;
		ArrayList<User> users = new  ArrayList<User>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(User.class);
			crit.createAlias("roleId", "role");
			crit.createAlias("companyId", "company");
			crit.add(Restrictions.ne("status", "inActive"));
			crit.add(Restrictions.ne("status", "Closed"));
			crit.add(Restrictions.eq("company.companyId", companyId));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				User user =  (User)it.next();
				users.add(user);
			}
			return users;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAllUsers", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchAllUsers"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

	}
	
	public ArrayList<User> fetchAllUsers() throws Exception {
		Session session = null;
		ArrayList<User> users = new  ArrayList<User>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(User.class);
			crit.createAlias("roleId", "role");
			crit.createAlias("companyId", "company");
			crit.add(Restrictions.ne("status", "inActive"));
			crit.add(Restrictions.ne("status", "Closed"));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				User user =  (User)it.next();
				users.add(user);
			}
			return users;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAllUsers", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchAllUsers"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}

	}

	public ArrayList<LeaveRecord> fetchPendingLeavesRecordOfLoggedInUser(int userId, int companyId)throws Exception {
		Session session = null;
		ArrayList<LeaveRecord> leaveRecords = new ArrayList<LeaveRecord>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(LeaveRecord.class);
			crit.createAlias("userId", "user");

			crit.createAlias("user.roleId", "role");
			crit.createAlias("user.companyId", "company");
			crit.createAlias("leaveTypeId", "lType");
			crit.add(Restrictions.eq("user.userId", userId));
			crit.add(Restrictions.ne("user.status", "inActive"));
			crit.addOrder(Order.desc("startDate"));
			//			crit.add(Restrictions.eq("status", "pending"));
			crit.add(Restrictions.eq("company.companyId", companyId));
			List rsList = crit.list();
			for(Iterator it=rsList.iterator();it.hasNext();)
			{

				LeaveRecord leaveRecord =  (LeaveRecord)it.next();
				leaveRecords.add(leaveRecord);
				long days = getDiffInDates(leaveRecord.getStartDate(), leaveRecord.getEndDate());

				days = days+1;
				leaveRecord.setDays(days+"");

			}
			return leaveRecords;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchLeaveRecord", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchLeaveRecord"+ ex.getMessage());

			throw new Exception("Exception occured in fetchLeaveRecord");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public ArrayList<LeaveRecord> fetchPendingLeavesRecord(int companyId, int loggedInUserId)throws Exception {
		Session session = null;
		ArrayList<LeaveRecord> leaveRecords = new ArrayList<LeaveRecord>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(LeaveRecord.class);
			crit.createAlias("userId", "user");
			crit.createAlias("user.roleId", "role");
			crit.createAlias("user.companyId", "company");
			crit.add(Restrictions.ne("user.status", "inActive"));
			crit.createAlias("leaveTypeId", "lType");
			crit.addOrder(Order.desc("startDate"));
			crit.add(Restrictions.eq("status", "pending"));
			crit.add(Restrictions.eq("company.companyId", companyId));
			crit.add(Restrictions.eq("user.reportingTo", loggedInUserId));

			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				LeaveRecord leaveRecord =  (LeaveRecord)it.next();
				leaveRecords.add(leaveRecord);
				long days = getDiffInDates(leaveRecord.getStartDate(), leaveRecord.getEndDate());

				days = days+1;
				leaveRecord.setDays(days+"");

			}
			return leaveRecords;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchLeaveRecord", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchLeaveRecord"+ ex.getMessage());

			throw new Exception("Exception occured in fetchLeaveRecord");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public ArrayList<LeavesDTOForAllUsers> fetchLeavesRemainingForAllUsers(int companyId) throws Exception {
		Session session = null;
		ArrayList<User> users = fetchAllUsersExceptDirector(companyId);
		try{
			session = sessionFactory.openSession();
			ArrayList<LeavesDTOForAllUsers> leavesDTOs = new ArrayList<LeavesDTOForAllUsers>();
			ArrayList<LeaveTypes> leaveTypes =  fetchLeaveTypes();
			for(int j=0; j< users.size(); j++){
				LeavesDTOForAllUsers leavesDTOForAllUsers = new LeavesDTOForAllUsers();
				for(int i=0; i< leaveTypes.size(); i++){
					LeavesDTO leavesDTO = new LeavesDTO();
					int leaveTypeId = leaveTypes.get(i).getLeaveTypeId();
					long leaveAvailedforThisLeaveType  = fetchCurrentUserLeaveforSelectedLeaveType(leaveTypeId, users.get(j).getUserId(), session);
					leavesDTO.setLeaveType(leaveTypes.get(i));
					leavesDTOForAllUsers.setUser(users.get(j));
					int allowed = 0;
					if(leaveTypeId ==4){
						allowed = users.get(j).getExamLeaves();
					}
					else{
						allowed = leaveTypes.get(i).getAllowed();
					}
					long available = allowed - leaveAvailedforThisLeaveType;
					leavesDTO.setLeavesAvaible(available);
					leavesDTO.setAllowed(allowed);
					leavesDTO.setLeavesAvailed((int) leaveAvailedforThisLeaveType);
					leavesDTOForAllUsers.getLeavesDTO().add(leavesDTO);


				}
				leavesDTOs.add(leavesDTOForAllUsers);
			}
			return leavesDTOs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAvailableLeaves", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchAvailableLeaves"+ ex.getMessage());

			throw new Exception("Exception occured in fetchAvailableLeaves");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public ArrayList<Roles> fetchAllRoles()throws Exception {
		Session session = null;
		ArrayList<Roles> roles = new  ArrayList<Roles>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Roles.class);

			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Roles role =  (Roles)it.next();
				roles.add(role);
			}
			return roles;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchAllRoles", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchAllRoles"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public String addCompany(Company company, User user) throws Exception{
		Session session = null;

		try{
			session = sessionFactory.openSession();
			session.saveOrUpdate(company);
			user.setCompanyId(company);
			session.save(user);
			user.setReportingTo(user.getUserId());
			session.update(user);

			session.flush();
			return "company added";
		}
		catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePassword", ex.getMessage()), ex);
			System.out.println("Exception occured in updatePassword"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public String deleteUser(int userId)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			User user = (User) session.get(User.class, userId);
			user.setStatus("inActive");
			session.update(user);
			session.flush();
			return "user deleted";
		}

		catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePassword", ex.getMessage()), ex);
			System.out.println("Exception occured in updatePassword"+ ex.getMessage());

			throw new Exception("Exception occured in updatePassword");//Add this Line Accordingly in all method
		}
		finally{
			session.close();
		}
	}

	public JobAttributesDTO getJobAttributes() throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			ArrayList<LineofService> lineofServices =	getLineOfServices();
			ArrayList<Countries> countries =getCountries(session);
			ArrayList<User> users = fetchAllUsers();
			ArrayList<SubLineofService> subLineofServices = fetchSubLineOfServices(1);
			ArrayList<Domains> domains = fetchDomains();
			
			JobAttributesDTO jobAttributesDTO = new JobAttributesDTO();
			jobAttributesDTO.setCountries(countries);
			jobAttributesDTO.setDomains(domains);
			jobAttributesDTO.setLineofService(lineofServices);
			jobAttributesDTO.setSubLineofService(subLineofServices);
			jobAttributesDTO.setUsers(users);
			
			return jobAttributesDTO;
			
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getJobAttributes", ex.getMessage()), ex);
			System.out.println("Exception occured in getJobAttributes"+ ex.getMessage());

			throw new Exception("Exception occured in getJobAttributes");
		}
		finally{

		}
	}

	public ArrayList<LineofService> getLineOfServices() throws Exception{
		ArrayList<LineofService> lineofServices = new ArrayList<LineofService>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(LineofService.class);
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				LineofService lineofService =  (LineofService)it.next();
				lineofServices.add(lineofService);
			}

			return lineofServices;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getLineOfServices", ex.getMessage()), ex);
			System.out.println("Exception occured in getLineOfServices"+ ex.getMessage());

			throw new Exception("Exception occured in getLineOfServices");
		}
		finally{

		}
	}
	
	
	public ArrayList<SubLineofService> fetchSubLineOfServices(int domainId) throws Exception{
		ArrayList<SubLineofService> subLineofServices = new ArrayList<SubLineofService>();
		Session session;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(SubLineofService.class);
			crit.createAlias("domainId", "domain");
			crit.createAlias("domain.lineofServiceId", "domainline");
			crit.add(Restrictions.eq("domain.domainId", domainId));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				SubLineofService lineofService =  (SubLineofService)it.next();
				subLineofServices.add(lineofService);
			}

			return subLineofServices;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getSubLineOfServices", ex.getMessage()), ex);
			System.out.println("Exception occured in getSubLineOfServices"+ ex.getMessage());

			throw new Exception("Exception occured in getSubLineOfServices");
		}
		finally{

		}
	}
	
	private ArrayList<Countries> getCountries(Session session) throws Exception{
		ArrayList<Countries> countries = new ArrayList<Countries>();

		try{
			Criteria crit = session.createCriteria(Countries.class);
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Countries country =  (Countries)it.next();
				countries.add(country);
			}

			return countries;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getCountries", ex.getMessage()), ex);
			System.out.println("Exception occured in getCountries"+ ex.getMessage());

			throw new Exception("Exception occured in getCountries");
		}
		finally{

		}
	}
	
	
	
	public ArrayList<Domains> fetchDomains(int lineofServiceId) throws Exception{
		ArrayList<Domains> domains = new ArrayList<Domains>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Domains.class);
			crit.createAlias("lineofServiceId", "lineofService");
			//crit.add(Restrictions.eq("lineofService.lineofServiceId", lineofServiceId));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Domains domain =  (Domains)it.next();
				domains.add(domain);
			}

			return domains;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getDomains", ex.getMessage()), ex);
			System.out.println("Exception occured in getDomains"+ ex.getMessage());

			throw new Exception("Exception occured in getDomains");
		}
		finally{

		}
	}
	
	public ArrayList<Domains> fetchDomains() throws Exception{
		ArrayList<Domains> domains = new ArrayList<Domains>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Domains.class);
			crit.createAlias("lineofServiceId", "lineofService");
			////////////crit.add(Restrictions.eq("lineofService.lineofServiceId", lineofServiceId));
			List rsList = crit.list();

			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Domains domain =  (Domains)it.next();
				domains.add(domain);
			}

			return domains;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in getDomains", ex.getMessage()), ex);
			System.out.println("Exception occured in getDomains"+ ex.getMessage());

			throw new Exception("Exception occured in getDomains");
		}
		finally{

		}
	}
	
	

	public String saveJob(Job job) throws Exception{
		Session session = null;
		Transaction tr = null;
		try{
			
			session = sessionFactory.openSession();
			tr = session.beginTransaction();
			job.setStatus("Active");
			session.saveOrUpdate(job);
			
			//saveEmployeeJob(job.getJobEmployeesList(),job.getSupervisorId().getUserId(), job.getPrincipalConsultantId().getUserId(), job.getJobId(), session);
		ArrayList<JobEmployees> jobEmployeeList = new ArrayList<JobEmployees>();
			for(int i=0; i< job.getJobActivities().size(); i++){
				if(job.getJobActivities().get(i).getTotalHours()>0 ){
					JobEmployees jobEmployee = new JobEmployees();
					jobEmployee.setEmployeeId(job.getJobActivities().get(i).getUserId());
					jobEmployee.setJobId(job.getJobId());
					jobEmployeeList.add(jobEmployee);
					
				}
			}
			
			saveEmployeeJob(jobEmployeeList, job.getJobId(), session);
			
			session.flush();
			setJobActivities(job.getJobActivities(), session, job);
		//	addPhase(job, session);
			tr.commit();
//			
//			for(int i=0; i< job.getJobEmployeesList().size(); i++){
//				String email = fetchUsersEmail(job.getJobEmployeesList().get(i).getEmployeeId().getUserId(), session);
//				String body= "Dear "+ job.getJobEmployeesList().get(i).getEmployeeId().getName()+" : "+ "A new job has been created named (" + job.getJobName() +") and assigned to you";
//				sendEmail(body, email, "", "Job Created");
//			}
//			String email = fetchUsersEmail(job.getSupervisorId().getUserId(), session);
//			String body= "Dear "+ job.getSupervisorId().getName()+" : "+ "A new job has been created named (" + job.getJobName() +") and assigned to you";
//			
//			sendEmail(body, email, "", "Job Created");///////??
				
			
			
			return "job created";
		}catch(Exception ex){
			tr.rollback();
			logger.warn(String.format("Exception occured in saveJob", ex.getMessage()), ex);
			System.out.println("Exception occured in saveJob"+ ex.getMessage());

			throw new Exception("Exception occured in saveJob");
		}
		finally{
			session.close();
		}
		
	}

	private void setJobActivities(ArrayList<JobActivityEntity> jobActivityList, Session session, Job job) {
		try{
			for(int i=0; i< jobActivityList.size(); i++){
				
				JobActivityEntity  jobActivity = jobActivityList.get(i);
				
				/*jobActivity.setUserId(jobActivityList.get(i).getUserId());
				jobActivity.setPlanning(jobActivityList.get(i).getPlanning());
				jobActivity.setExecution(jobActivityList.get(i).getExecution());
				jobActivity.setFollowup(jobActivityList.get(i).getFollowup());
				jobActivity.setReporting(jobActivityList.get(i).getReporting());
				*/
				jobActivity.setJobId(job);
					session.saveOrUpdate(jobActivity);
					session.flush();
			}
			
			}catch(Exception ex){
				System.out.println(ex);
			}
			
		}
		
	

/*	private void saveEmployeeJob(ArrayList<JobEmployees> jobEmployeesList,  int jobId, Session session) {
		try{
		for(int i=0; i< jobEmployeesList.size(); i++){
			JobEmployees jobEmployees = jobEmployeesList.get(i);
			jobEmployees.setJobId(jobId);
//			if(employeeJobAlreadySaved(jobId, jobEmployees.getEmployeeId().getUserId(), session)){
//			session.update(jobEmployees);
//			session.flush();
//			}else{
				session.saveOrUpdate(jobEmployees);
				session.flush();
//			}
		}
		
		}catch(Exception ex){
			System.out.println(ex);
		}
		
	}*/
	
	private void saveEmployeeJob(ArrayList<JobEmployees> jobEmployeesList,  int jobId, Session session) {
		try{
		for(int i=0; i< jobEmployeesList.size(); i++){
			JobEmployees jobEmployees = jobEmployeesList.get(i);
			jobEmployees.setJobId(jobId);
			if(employeeJobAlreadySaved(jobId, jobEmployees.getEmployeeId().getUserId(), session)){
			//session.update(jobEmployees);
			//session.flush();
			}else{
				session.saveOrUpdate(jobEmployees);
				session.flush();
			}
		}
		
		}catch(Exception ex){
			System.out.println(ex);
		}
		
	}

	private boolean employeeJobAlreadySaved(int jobId, int userId, Session session) {
		Criteria crit = session.createCriteria(JobEmployees.class);
		crit.createAlias("employeeId", "employee");
		crit.add(Restrictions.eq("employee.userId", userId));
		crit.add(Restrictions.eq("jobId", jobId));
		if(crit.list().size()>0){
			return true;
		}else{
			return false;
		}
		
	}

//	private void addPhase(Job job, Session session)throws Exception {
//		try{
//			for(int i=0; i< job.getJobPhases().size(); i++){
//				Phases phase = job.getJobPhases().get(i);
//				phase.setJobId(job);
//				session.saveOrUpdate(phase);
//				session.flush();
//			}
//		}catch(Exception ex){
//			logger.warn(String.format("Exception occured in addPhase", ex.getMessage()), ex);
//			System.out.println("Exception occured in addPhase"+ ex.getMessage());
//
//			throw new Exception("Exception occured in addPhase");
//		}
//		finally{
//
//		}
//	}

	public ArrayList<Job> fetchJobs(User loggedInUser) throws Exception{
		ArrayList<Job> jobs = new ArrayList<Job>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.createAlias("lineofServiceId", "lineofService");
			//crit.createAlias("subLineofServiceId", "subLineofService");
			//crit.createAlias("subLineofService.domainId", "sublineDomain");
			//crit.createAlias("sublineDomain.lineofServiceId", "ddomainlineofservice");
			
			crit.createAlias("domainId", "domain");
			crit.createAlias("domain.lineofServiceId", "domainlineofservice");
//			crit.createAlias("sublineDomain.lineofServiceId", "subdomainlineofservice");
			
			crit.createAlias("countryId", "count");
//			crit.createAlias("userId", "user");
//			crit.createAlias("user.roleId", "role");
//			crit.createAlias("user.companyId", "company");
			
			
			//crit.createAlias("supervisorId", "supervisor");
			//crit.createAlias("supervisor.roleId", "roles");
			//crit.createAlias("supervisor.companyId", "companys");
			
			
			//crit.createAlias("principalConsultantId", "principalConsultant");
			//crit.createAlias("principalConsultant.roleId", "rolep");
			//crit.createAlias("principalConsultant.companyId", "companyp");
			
			crit.add(Restrictions.ne("status", "InActive"));
			//crit.add(Restrictions.ne("client", "office"));
			crit.add(Restrictions.ne("status", "Closed"));
			
			if(loggedInUser.getRoleId().getRoleId()!=5){
				ArrayList<Integer> jobIds = getUserJobs(loggedInUser.getUserId(), session);
				Disjunction disc = Restrictions.disjunction();
				for(int i=0; i<jobIds.size(); i++){
				disc.add(Restrictions.eq("jobId", jobIds.get(i)));
				}
				crit.add(disc);
				
			}
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Job job =  (Job)it.next();
				//job.setJobPhases(fetchJobPhases(job.getJobId()));
				job.setJobEmployeesList(fetchJobEmployees(session, job.getJobId()));
				job.setJobAttributes(fetchjobAttributes(session, job.getJobId()));
				job.setTimeSheets(fetchJobTimeSheets(session, job.getJobId(), loggedInUser.getRoleId().getRoleId(), loggedInUser.getUserId()));
				HibernateDetachUtility.nullOutUninitializedFields(job, HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getLineofServiceId(), HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getDomainId(), HibernateDetachUtility.SerializationType.SERIALIZATION);
			//	HibernateDetachUtility.nullOutUninitializedFields(job.getPrincipalConsultantId(), HibernateDetachUtility.SerializationType.SERIALIZATION);
			//	HibernateDetachUtility.nullOutUninitializedFields(job.getSubLineofServiceId(), HibernateDetachUtility.SerializationType.SERIALIZATION);
				HibernateDetachUtility.nullOutUninitializedFields(job.getCountryId(), HibernateDetachUtility.SerializationType.SERIALIZATION);
			//	HibernateDetachUtility.nullOutUninitializedFields(job.getSupervisorId(), HibernateDetachUtility.SerializationType.SERIALIZATION);
				job.setJobActivities(fetchJobActivities(session, job.getJobId()));

				jobs.add(job);
			}
				
				
				return jobs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobs"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobs");
		}
		finally{
			session.close();
		}
	}

	public ArrayList<Job> fetchJobsForTimeSheet(User loggedInUser, boolean chargeable) throws Exception{
		ArrayList<Job> jobs = new ArrayList<Job>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.createAlias("lineofServiceId", "lineofService");
//			crit.createAlias("subLineofServiceId", "subLineofService");
//			crit.createAlias("subLineofService.domainId", "sublineDomain");
//			crit.createAlias("sublineDomain.lineofServiceId", "ddomainlineofservice");
			
			crit.createAlias("domainId", "domain");
			crit.createAlias("domain.lineofServiceId", "domainlineofservice");
//			crit.createAlias("sublineDomain.lineofServiceId", "subdomainlineofservice");
			
			crit.createAlias("countryId", "count");
//			crit.createAlias("userId", "user");
//			crit.createAlias("user.roleId", "role");
//			crit.createAlias("user.companyId", "company");
			
			
//			crit.createAlias("supervisorId", "supervisor");
//			crit.createAlias("supervisor.roleId", "roles");
//			crit.createAlias("supervisor.companyId", "companys");
//			
//			
//			crit.createAlias("principalConsultantId", "principalConsultant");
//			crit.createAlias("principalConsultant.roleId", "rolep");
//			crit.createAlias("principalConsultant.companyId", "companyp");
			
			crit.add(Restrictions.ne("status", "InActive"));
			crit.add(Restrictions.ne("status", "Closed"));
			if(chargeable){
				crit.add(Restrictions.eq("allocation", 1));
			}
//			crit.add(Restrictions.ne("client", "office"));
			
//			if(loggedInUser.getRoleId().getRoleId()!=5){
				ArrayList<Integer> jobIds = getUserJobs(loggedInUser.getUserId(), session);
				Disjunction disc = Restrictions.disjunction();
				for(int i=0; i<jobIds.size(); i++){
				disc.add(Restrictions.eq("jobId", jobIds.get(i)));
				}
				crit.add(disc);
				
//			}
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Job job =  (Job)it.next();
			//	job.setJobPhases(fetchJobPhases(job.getJobId()));
				job.setJobEmployeesList(fetchJobEmployees(session, job.getJobId()));
				job.setJobAttributes(fetchjobAttributes(session, job.getJobId()));
				job.setTimeSheets(fetchJobTimeSheets(session, job.getJobId(), loggedInUser.getRoleId().getRoleId(), loggedInUser.getUserId()));
				jobs.add(job);
			}

			return jobs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobs"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobs");
		}
		finally{
			session.close();
		}
	}

	
	private ArrayList<JobActivityEntity> fetchJobActivities(Session session, int jobId) throws Exception {
		ArrayList<JobActivityEntity> listJobActivity = new ArrayList<JobActivityEntity>();
		try{
			Criteria crit = session.createCriteria(JobActivityEntity.class);
			crit.createAlias("jobId", "job");
			crit.createAlias("userId", "user");
			crit.add(Restrictions.eq("job.jobId", jobId));
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				JobActivityEntity jobActivityEntity =  (JobActivityEntity)it.next();
				listJobActivity.add(jobActivityEntity);
			}
			return listJobActivity;	
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobActivities", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobActivities"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobActivities");
		}
		
	}

	private ArrayList<TimeSheet> fetchJobTimeSheets(Session session, int jobId, int roleId, int userId)throws Exception {
		ArrayList<TimeSheet> listTimeSheet = new ArrayList<TimeSheet>();
		try{
			Criteria crit = session.createCriteria(TimeSheet.class);
			crit.createAlias("userId", "user");
			crit.createAlias("user.roleId", "role");
			crit.createAlias("user.companyId", "company");
			crit.createAlias("jobId", "job");
			crit.createAlias("job.lineofServiceId", "lineofService1");
			//crit.createAlias("job.subLineofServiceId", "subLineofService1");
			//crit.createAlias("job.supervisorId", "supervisor");
			crit.createAlias("job.domainId", "domain1");
			crit.createAlias("job.countryId", "count1");
			crit.createAlias("activity", "activity");
			//crit.createAlias("job.principalConsultantId", "principalConsultant");
			crit.add(Restrictions.eq("job.jobId", jobId));
			crit.add(Restrictions.eq("user.userId", userId));
			crit.add(Restrictions.ne("activity.activityId", 0));
			
			if(roleId==5){
//				crit.add(Restrictions.eq("status", 1));
			}
			
			crit.addOrder(Order.asc("activity.activityId"));
			
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				TimeSheet timeSheet =  (TimeSheet)it.next();
				listTimeSheet.add(timeSheet);
			}
			return listTimeSheet;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobTimeSheets", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobTimeSheets"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobTimeSheets");
		}
	}

	private ArrayList<JobAttributes> fetchjobAttributes(Session session, int jobId)throws Exception {
		ArrayList<JobAttributes> jobAttributesList = new ArrayList<JobAttributes>();
		try{
			Criteria crit = session.createCriteria(JobAttributes.class);
			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				JobAttributes jobAttributes =  (JobAttributes)it.next();
				jobAttributesList.add(jobAttributes);
			}
			return jobAttributesList;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchjobAttributes", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchjobAttributes"+ ex.getMessage());

			throw new Exception("Exception occured in fetchjobAttributes");
		}
		
	}

	private ArrayList<Integer> getUserJobs(int userId, Session session) {
		ArrayList<Integer>jobIds = new ArrayList<Integer>();
		try{
			Criteria crit = session.createCriteria(JobEmployees.class);
			crit.createAlias("employeeId", "employee");
			crit.add(Restrictions.eq("employee.userId", userId));
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				JobEmployees jobEmployees =  (JobEmployees)it.next();
				int jobId = jobEmployees.getJobId();
				jobIds.add(jobId);
			}
			
		}catch(Exception  ex){
			System.out.println("getUserJobs failed");
		}
		return jobIds;
		
	}

	private ArrayList<JobEmployees> fetchJobEmployees(Session session, int jobId)throws Exception {
		try{
			ArrayList<JobEmployees> jobEmployeesList = new ArrayList<JobEmployees>();
		
			Criteria crit = session.createCriteria(JobEmployees.class);
			crit.createAlias("employeeId", "employee");
			crit.createAlias("employee.companyId", "employeeComp");
			crit.createAlias("employee.roleId", "employeeRole");
			crit.add(Restrictions.eq("jobId", jobId));
			List rsList = crit.list();
					
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				JobEmployees jobEmployees =  (JobEmployees)it.next();
				jobEmployeesList.add(jobEmployees);
			}
			return jobEmployeesList;
		}catch(Exception ex){
			System.out.println("fetchJobEmployees failed");
			throw ex;
		}
	}

	private ArrayList<Phases> fetchJobPhases(int jobId)throws Exception {
		Session session = null;
		ArrayList<Phases> phasesList = new ArrayList<Phases>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Phases.class);
			crit.createAlias("jobId", "job");
			crit.createAlias("job.lineofServiceId", "lineofService1");
			crit.createAlias("job.subLineofServiceId", "subLineofService1");
			crit.createAlias("job.supervisorId", "supervisor");
			crit.createAlias("job.domainId", "domain1");
			crit.createAlias("job.countryId", "count1");
			crit.createAlias("job.principalConsultantId", "principalConsultant");
			
//			crit.createAlias("job.userId", "user1");
//			crit.createAlias("user1.roleId", "role");
//			crit.createAlias("user1.companyId", "company");
			crit.createAlias("domain1.lineofServiceId", "domain1lineofservice");

			
			
			crit.createAlias("supervisor.roleId", "roles");
			crit.createAlias("supervisor.companyId", "companys");
			
			
			crit.createAlias("principalConsultant.roleId", "rolep");
			crit.createAlias("principalConsultant.companyId", "companyp");
			
			crit.add(Restrictions.eq("job.jobId", jobId));
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Phases phase =  (Phases)it.next();
				phasesList.add(phase);
			}
			return phasesList;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobPhases", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobPhases"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobPhases");
		}
		finally{
			session.close();
		}
		
	}

	public String updatePhase(Phases phase) throws Exception{
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.update(phase);
			session.flush();
			return "phase updated";
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePhase", ex.getMessage()), ex);
			System.out.println("Exception occured in updatePhase"+ ex.getMessage());

			throw new Exception("Exception occured in updatePhase");
		}
		finally{
			session.close();
		}
		
	}

	public String deletePhase(Phases phase)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.delete(phase);
			session.flush();
			return "phase deleted";
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in updatePhase", ex.getMessage()), ex);
			System.out.println("Exception occured in updatePhase"+ ex.getMessage());

			throw new Exception("Exception occured in updatePhase");
		}
		finally{
			session.close();
		}
	}

	public String deleteJobEmployee(int jobEmployeeId)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			JobEmployees jobEmployee = (JobEmployees) session.get(JobEmployees.class, jobEmployeeId);
			session.delete(jobEmployee);
			session.flush();
			return "jobEmployee deleted";
		}catch(Exception ex){
			throw ex;
		}finally{
			session.close();
		}
		
	}

	public String deleteJob(int jobId)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.add(Restrictions.eq("jobId", jobId));
			Job job = (Job) crit.list().get(0);
			job.setStatus("InActive");
			session.update(job);
			session.flush();
			return "job deleted";
		}catch(Exception ex){
			throw ex;
		}finally{
			session.close();
		}
	}



	public String deleteJobAttribute(int jobAttributeId) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			JobAttributes jobAttribute = (JobAttributes) session.get(JobAttributes.class, jobAttributeId);
			session.delete(jobAttribute);
			session.flush();
		}catch(Exception ex){
			System.out.println("fail job delte ");
		}
		return "job deleted";
	}

	public String saveJobAttribute(JobAttributes jobAttribute) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.saveOrUpdate(jobAttribute);
			session.flush();
		}catch(Exception ex){
			System.out.println("fail job attributes save");
		}finally{
			
		}
		return "job attributes saved";
	}

	public String saveTimeSheet(ArrayList<TimeSheet> timeSheet) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			deletePreviousTimeSheet(timeSheet.get(0).getMonth(), timeSheet.get(0).getUserId().getUserId(), session);
			
			for(int i=0; i< timeSheet.size(); i++){
				session.saveOrUpdate(timeSheet.get(i));
				session.flush();
			}
			
			
			
		}catch(Exception ex){
			System.out.println("fail saveTimeSheet");
		}finally{
			
		}
		return null;
		
	}

	private void deletePreviousTimeSheet(int month, int userId , Session session) {
		try{
			Criteria crit = session.createCriteria(TimeSheet.class);
			crit.createAlias("userId", "user");
			crit.add(Restrictions.eq("user.userId", userId));
			crit.add(Restrictions.eq("month", month));
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				TimeSheet timeSheet =  (TimeSheet)it.next();
				session.delete(timeSheet);
				session.flush();
			
		}
		
	}catch(Exception ex){
		System.out.println("fail job delte");
	}
	}

	public ArrayList<TimeSheetReportDTO> fetchTimeReport(int selectedJob, int selectedMonth, int selectedUser, int selectedJobType) {
		Session session = null;
		int numberOfHours=0;
		ArrayList<TimeSheetReportDTO> timeSheetlist = new ArrayList<TimeSheetReportDTO>();
		try{
			session = sessionFactory.openSession();
			
			Criteria crit = session.createCriteria(TimeSheet.class);
			
			crit.createAlias("userId", "user");
			crit.createAlias("user.roleId", "role");
			crit.createAlias("user.companyId", "company");
			crit.createAlias("jobId", "job");
			crit.createAlias("job.lineofServiceId", "lineofService1");
			crit.createAlias("job.subLineofServiceId", "subLineofService1");
		//	crit.createAlias("job.supervisorId", "supervisor");
			crit.createAlias("job.domainId", "domain1");
			crit.createAlias("job.countryId", "count1");
		//	crit.createAlias("job.principalConsultantId", "principalConsultant");
			
			
			
			if(selectedJob!=0){
				crit.add(Restrictions.eq("job.jobId", selectedJob));
			}
			if(selectedMonth!=0){
				crit.add(Restrictions.eq("month", selectedMonth));
			}
			if(selectedUser!=0){
				crit.add(Restrictions.eq("user.userId", selectedUser));
			}
			
			if(selectedJobType!=0){
				crit.add(Restrictions.eq("lineofService1.lineofServiceId", selectedJobType));
			}
			
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				boolean matchFound=false; 
				TimeSheet timeSheet =  (TimeSheet)it.next();
				TimeSheetReportDTO timeSheetReportDTO = new TimeSheetReportDTO();
				timeSheetReportDTO.setJobName(timeSheet.getJobId().getJobName());
				//timeSheetReportDTO.setFee(timeSheet.getJobId().getClientFee());
				timeSheetReportDTO.setJobType(timeSheet.getJobId().getLineofServiceId().getName());
				timeSheetReportDTO.setJobId(timeSheet.getJobId().getJobId());
				timeSheetReportDTO.setTotal(timeSheet.getHours());
				int userCost =  timeSheet.getUserId().getRoleId().getChargeRate();
				
				for(int i=0;i<timeSheetlist.size(); i++){
					
					if(timeSheetlist.get(i).getJobId() == timeSheet.getJobId().getJobId()){
						
						if(timeSheetlist.get(i).getUsersList()==null ||timeSheetlist.get(i).getUsersList().size()<1){
							addNewuserTotheJob(timeSheet, userCost, timeSheetlist.get(i));
						}else{
							boolean userMatchFound=false;
							float totalTimeCost = 0;
						for(int j=0; j< timeSheetlist.get(i).getUsersList().size(); j++){
							if(timeSheetlist.get(i).getUsersList().get(j).getUserId() == timeSheet.getUserId().getUserId()){
								timeSheetlist.get(i).getUsersList().get(j).setActualHours(timeSheetlist.get(i).getUsersList().get(j).getActualHours() + timeSheet.getHours());
								timeSheetlist.get(i).getUsersList().get(j).setTimeCost(userCost * timeSheetlist.get(i).getUsersList().get(j).getActualHours());
								timeSheetlist.get(i).setTotal(timeSheetlist.get(i).getTotal()+timeSheet.getHours());
								totalTimeCost = totalTimeCost+ timeSheetlist.get(i).getUsersList().get(j).getTimeCost();
								timeSheetlist.get(i).setTimeCost(totalTimeCost);
								timeSheetlist.get(i).setActualRecoveryRate(timeSheetlist.get(i).getFee()/timeSheetlist.get(i).getTotal());
							
								userMatchFound = true;
								break;
							}
//							else{
//								addNewuserTotheJob(timeSheet, userCost, timeSheetlist.get(i));
//							}
						}
						if(!userMatchFound){
							addNewuserTotheJob(timeSheet, userCost, timeSheetlist.get(i));
							timeSheetlist.get(i).setTotal(timeSheetlist.get(i).getTotal()+timeSheet.getHours());
							timeSheetlist.get(i).setActualRecoveryRate(timeSheetlist.get(i).getFee()/timeSheetlist.get(i).getTotal());
							
						}
					}
						matchFound = true;
					}
				}
				if(!matchFound){
				timeSheetlist.add(timeSheetReportDTO);
				addNewuserTotheJob(timeSheet, userCost, timeSheetReportDTO);
				}
				
//				numberOfHours = numberOfHours+ timeSheet.getHours();
			
		}
		
	}catch(Exception ex){
		System.out.println("fail job delte");
	}
//		return "Total Number of hours worked: " + numberOfHours;
		return timeSheetlist;
	}

	private void addNewuserTotheJob(TimeSheet timeSheet, int userCost, TimeSheetReportDTO timeSheetReportDTO) {
		UserReportDTO userReportDTO = new UserReportDTO();
		userReportDTO.setActualHours(timeSheet.getHours());
//		userReportDTO.setBudgetedHours(budgetedHours);
		userReportDTO.setTimeCost(timeSheet.getHours() * userCost);
		userReportDTO.setUserId(timeSheet.getUserId().getUserId());
		userReportDTO.setUserName(timeSheet.getUserId().getName());
		timeSheetReportDTO.setTimeCost(userReportDTO.getTimeCost());
		timeSheetReportDTO.getUsersList().add(userReportDTO);
	}

//	public String saveRating(int attributeId, String rating, int score, int userId)throws Exception {
//		Session session = null;
//		try{
//			session = sessionFactory.openSession();
//			JobAttributes jobAttributes = (JobAttributes) session.get(JobAttributes.class, attributeId);
//			jobAttributes.setScore(score);
//			jobAttributes.setRating(rating);
//			jobAttributes.setUserId(userId);
//			session.update(jobAttributes);
//			session.flush();
//			return "rating saved";
//		}catch(Exception ex){
//			System.out.println("fail saveRating");
//			throw ex;
//		}
//	}
	
	public String saveRating(AttributeRating attributeRating)throws Exception {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AttributeRating.class);
			crit.add(Restrictions.eq("attributeId", attributeRating.getAttributeId()));
			crit.add(Restrictions.eq("userId", attributeRating.getUserId()));
			
			
			List rsList = crit.list();
			
			if(rsList.size()>0){
				AttributeRating attributeRat = (AttributeRating) rsList.get(0);
				attributeRat.setRating(attributeRating.getRating());
				attributeRat.setScore(attributeRating.getScore());
				attributeRat.setLevel(attributeRating.getScore());
				session.update(attributeRat);
				
			}else{
				session.save(attributeRating);
			}
			session.flush();
			
//			boolean found = false;
//			JobAttributes jobAttributes = null;
//			for(Iterator it=rsList.iterator();it.hasNext();)
//			{
//				jobAttributes =  (JobAttributes)it.next();
//				jobAttributes.setScore(score);
//				jobAttributes.setRating(rating);
//				if(jobAttributes.getUserId() == userId){
//					found = true;
//				}
//			}
//				
//				if(found){
//				session.update(jobAttributes);
//				session.flush();
//			}else{
//				int jobId = jobAttributes.getJobId();
//				int levelSaved = jobAttributes.getLevel();
//				int scoreSaved = jobAttributes.getScore();
//				
//				String nameSaved = jobAttributes.getName();
//				String ratingSaved = jobAttributes.getRating();
//				
//				saveNewRating(jobId, levelSaved,score,userId,nameSaved, rating   );
//				}
//			
	
	
			return "rating saved";
		}catch(Exception ex){
			System.out.println("fail saveRating");
			throw ex;
		}
	}

	private void saveNewRating(int jobId, int levelSaved, int scoreSaved,
			int userSaved, String nameSaved, String ratingSaved) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
				JobAttributes jobAttributes =  new JobAttributes();
				jobAttributes.setScore(scoreSaved);
				jobAttributes.setRating(ratingSaved);
				jobAttributes.setUserId(userSaved);
				jobAttributes.setName(nameSaved);
				jobAttributes.setLevel(levelSaved);
				jobAttributes.setJobId(jobId);
				session.save(jobAttributes);
				session.flush();
		}catch(Exception ex){
			System.out.println("error saving new attribute"+ ex);
		}
		
	}

	public Integer fetchMonthAllowedhours(int month) throws Exception{
		Session session = null;
		int allowedhours =0;
		try{
			
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(MonthAllowedHours.class);
			crit.add(Restrictions.eq("month", month));
			MonthAllowedHours monthAllowedHours = (MonthAllowedHours) crit.list().get(0);
			allowedhours = monthAllowedHours.getHours();
			return allowedhours;
		}catch(Exception ex){
			throw ex;
		}
		
	}

	public ArrayList<Job> tim(User loggedInUser)throws Exception {
		ArrayList<Job> jobs = new ArrayList<Job>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.createAlias("lineofServiceId", "lineofService");
//			crit.createAlias("subLineofServiceId", "subLineofService");
//			crit.createAlias("subLineofService.domainId", "sublineDomain");
//			crit.createAlias("sublineDomain.lineofServiceId", "ddomainlineofservice");
//			
			crit.createAlias("domainId", "domain");
			crit.createAlias("domain.lineofServiceId", "domainlineofservice");
//			crit.createAlias("sublineDomain.lineofServiceId", "subdomainlineofservice");
			
			crit.createAlias("countryId", "count");
//			crit.createAlias("userId", "user");
//			crit.createAlias("user.roleId", "role");
//			crit.createAlias("user.companyId", "company");
			
			
//			crit.createAlias("supervisorId", "supervisor");
//			crit.createAlias("supervisor.roleId", "roles");
//			crit.createAlias("supervisor.companyId", "companys");
//			
			
//			crit.createAlias("principalConsultantId", "principalConsultant");
//			crit.createAlias("principalConsultant.roleId", "rolep");
//			crit.createAlias("principalConsultant.companyId", "companyp");
			
			crit.add(Restrictions.ne("status", "InActive"));
//			crit.add(Restrictions.eq("client", "office"));
			
			if(loggedInUser.getRoleId().getRoleId()!=5){
				ArrayList<Integer> jobIds = getUserJobs(loggedInUser.getUserId(), session);
				Disjunction disc = Restrictions.disjunction();
				for(int i=0; i<jobIds.size(); i++){
				disc.add(Restrictions.eq("jobId", jobIds.get(i)));
				}
				crit.add(disc);
				
			}
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Job job =  (Job)it.next();
				//job.setJobPhases(fetchJobPhases(job.getJobId()));
				job.setJobEmployeesList(fetchJobEmployees(session, job.getJobId()));
				job.setJobAttributes(fetchjobAttributes(session, job.getJobId()));
				job.setTimeSheets(fetchJobTimeSheets(session, job.getJobId(), loggedInUser.getRoleId().getRoleId(), loggedInUser.getUserId()));
				jobs.add(job);
			}

			return jobs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobForTimeSheets", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobForTimeSheets"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobForTimeSheets");
		}
		finally{
			session.close();
		}
	}

	public ArrayList<JobUsersDTO> fetchUsersWithJobs() throws Exception {
		ArrayList<Job> jobs = new ArrayList<Job>();
		Session session = null;
		ArrayList<JobUsersDTO> jobUsersDTOs = new ArrayList<JobUsersDTO>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.createAlias("lineofServiceId", "lineofService");
			//crit.createAlias("subLineofServiceId", "subLineofService");
			//crit.createAlias("subLineofService.domainId", "sublineDomain");
			//crit.createAlias("sublineDomain.lineofServiceId", "ddomainlineofservice");
			crit.createAlias("domainId", "domain");
			crit.createAlias("domain.lineofServiceId", "domainlineofservice");
			crit.createAlias("countryId", "count");
			//crit.createAlias("supervisorId", "supervisor");
			//crit.createAlias("supervisor.roleId", "roles");
		//	crit.createAlias("supervisor.companyId", "companys");
			//crit.createAlias("principalConsultantId", "principalConsultant");
		//	crit.createAlias("principalConsultant.roleId", "rolep");
		//	crit.createAlias("principalConsultant.companyId", "companyp");
			crit.add(Restrictions.ne("status", "InActive"));
			crit.add(Restrictions.ne("status", "Closed"));
			
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Job job =  (Job)it.next();
				ArrayList<User> users = fetchUsersonSelectedJob(job.getJobId());
				JobUsersDTO jobUsersDTO = new JobUsersDTO();
				jobUsersDTO.setJob(job);
				jobUsersDTO.setUsers(users);
//				job.setJobPhases(fetchJobPhases(job.getJobId()));
				job.setJobEmployeesList(fetchJobEmployees(session, job.getJobId()));
				job.setJobAttributes(fetchjobAttributes(session, job.getJobId()));
//				job.setTimeSheets(fetchJobTimeSheets(session, job.getJobId(), loggedInUser.getRoleId().getRoleId()));
//				jobs.add(job);
				jobUsersDTOs.add(jobUsersDTO);
				
			}

			return jobUsersDTOs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobs"+ ex.getMessage());

			throw new Exception("Exception occured in fetchJobs");
		}
		finally{
			session.close();
		}
	}
	
	public ArrayList<User> fetchUsersonSelectedJob(int jobId)throws Exception{
		Session session = null;
		 ArrayList<User> users = new ArrayList<User>();
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(JobEmployees.class);
			crit.createAlias("employeeId", "employee");
			crit.createAlias("employee.companyId", "employeeComp");
			crit.createAlias("employee.roleId", "employeeRole");
			crit.add(Restrictions.eq("jobId", jobId));
			
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				JobEmployees JobEmployees =  (JobEmployees)it.next();
				User user = JobEmployees.getEmployeeId();
				users.add(user);
		}
			return users;
		}catch(Exception ex){
			System.out.println("fail : fetchUsersonSelectedJob: "+ ex);
			throw ex;
		}
		
	}
	
	
	public ArrayList<AttributeRating> fetchjobUserRating(int userId, int jobId)throws Exception {
		ArrayList<AttributeRating> jobAttributesList = new ArrayList<AttributeRating>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(AttributeRating.class);
			crit.add(Restrictions.eq("jobId", jobId));
			crit.add(Restrictions.eq("userId", userId));
			List rsList = crit.list();
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				AttributeRating attributeRating =  (AttributeRating)it.next();
				jobAttributesList.add(attributeRating);
			}
			return jobAttributesList;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchjobAttributes", ex.getMessage()), ex);
			System.out.println("Exception occured in AttributeRating"+ ex.getMessage());

			throw new Exception("Exception occured in AttributeRating");
		}
		
	}

	public ArrayList<Job> fetchAllJobs()throws Exception {
		ArrayList<Job> jobs = new ArrayList<Job>();
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.createAlias("lineofServiceId", "lineofService");
//			crit.createAlias("subLineofServiceId", "subLineofService");
//			crit.createAlias("subLineofService.domainId", "sublineDomain");
//			crit.createAlias("sublineDomain.lineofServiceId", "ddomainlineofservice");
			
			crit.createAlias("domainId", "domain");
			crit.createAlias("domain.lineofServiceId", "domainlineofservice");
//			crit.createAlias("sublineDomain.lineofServiceId", "subdomainlineofservice");
			
			crit.createAlias("countryId", "count");
//			crit.createAlias("userId", "user");
//			crit.createAlias("user.roleId", "role");
//			crit.createAlias("user.companyId", "company");
			
			
//			crit.createAlias("supervisorId", "supervisor");
//			crit.createAlias("supervisor.roleId", "roles");
//			crit.createAlias("supervisor.companyId", "companys");
//			
//			
//			crit.createAlias("principalConsultantId", "principalConsultant");
//			crit.createAlias("principalConsultant.roleId", "rolep");
//			crit.createAlias("principalConsultant.companyId", "companyp");
			
			//crit.add(Restrictions.ne("status", "InActive"));
//			crit.add(Restrictions.ne("client", "office"));
			
			
			List rsList = crit.list();
			
			
			for(Iterator it=rsList.iterator();it.hasNext();)
			{
				Job job =  (Job)it.next();
				//job.setJobPhases(fetchJobPhases(job.getJobId()));
				job.setJobEmployeesList(fetchJobEmployees(session, job.getJobId()));
				job.setJobAttributes(fetchjobAttributes(session, job.getJobId()));
//				job.setTimeSheets(fetchJobTimeSheets(session, job.getJobId(), loggedInUser.getRoleId().getRoleId()));
				HibernateDetachUtility.nullOutUninitializedFields(job, HibernateDetachUtility.SerializationType.SERIALIZATION);

				jobs.add(job);
			}

			return jobs;
		}catch(Exception ex){
			logger.warn(String.format("Exception occured in fetchJobs", ex.getMessage()), ex);
			System.out.println("Exception occured in fetchJobs"+ ex.getMessage());

			throw new Exception("Exception occured in fetchAllJobs");
		}
		finally{
			session.close();
		}
	}

	public String closeJob(int jobId) {
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Criteria crit = session.createCriteria(Job.class);
			crit.add(Restrictions.eq("jobId", jobId));
			Job job = (Job) crit.list().get(0);
			job.setStatus("Closed");
			session.update(job);
			session.flush();
			return "job closed";
		}catch(Exception ex){
			throw ex;
		}finally{
			session.close();
		}
	}
	
	

}
