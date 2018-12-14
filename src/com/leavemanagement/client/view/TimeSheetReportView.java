package com.leavemanagement.client.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.ui.MaterialButton;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialToast;

import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Allocations;
import com.leavemanagement.shared.Branches;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.TimeSheetReportDTO;
import com.leavemanagement.shared.User;

public class TimeSheetReportView extends MaterialColumn{
	
	private 	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private MaterialLabel lblJobName = new MaterialLabel("Job Name");
	private MaterialLabel lblCompanyName = new MaterialLabel("Company Name");
	private MaterialLabel lblMonth = new MaterialLabel("Month");
	private MaterialLabel lblAllocation = new MaterialLabel("Allocation");
	private MaterialLabel lblLineOfService = new MaterialLabel("Line Of Service");
	private MaterialLabel lblDomain = new MaterialLabel("Domain");
	private MaterialLabel lblUser = new MaterialLabel("User");
	private MaterialListBox listUsers = new MaterialListBox();
	private MaterialListBox listJobs = new MaterialListBox();
	private MaterialListBox listBoxCompany = new MaterialListBox();
	private MaterialListBox listBoxAllocation = new MaterialListBox();
	private MaterialListBox listBoxDomain= new MaterialListBox();
	private MaterialListBox listBoxLineOfServices = new MaterialListBox();
	private MaterialListBox listMonth = new MaterialListBox();
	private MaterialListBox listJobType = new MaterialListBox();
	private User loggedInUser = null;
	private MaterialButton btnSearchAllReport = new MaterialButton("Search");
	private MaterialButton btnSearchJobWiseReport = new MaterialButton("Search");
	private MaterialColumn vpnlResult = new MaterialColumn();
	Column<TimeSheet, String> jobName;
	Column<TimeSheet, String> jobType;
	Column<TimeSheet, String> users;
	Column<TimeSheet, String> actualHours;
	Column<TimeSheet, String> budgetedHours;
	Column<TimeSheet, String> actualTimeCost;
	private CellTable<TimeSheet> table = new CellTable<TimeSheet>();
	
	public TimeSheetReportView(User loggedInUser){
		this.loggedInUser = loggedInUser;
		listMonth.addItem("0","All Months"  );
		listMonth.addItem( "1","Jan");
		listMonth.addItem( "2","Feb");
		listMonth.addItem("3","Mar");
		listMonth.addItem("4","Apr" );
		listMonth.addItem("5","May" );
		listMonth.addItem("6","Jun" );
		listMonth.addItem( "7","Jul");
		listMonth.addItem( "8","Aug");
		listMonth.addItem("9","Sep" );
		listMonth.addItem("10","Oct" );
		listMonth.addItem("11","Nov" );
		listMonth.addItem("12","Dec" );
		
		fetchJobs();
		fetchUsers();
		fetchJobType();
		
		 
		FlexTable flex = new FlexTable();
		flex.setWidget(0,1, lblJobName);
		flex.setWidget(0,2,listJobs);
		
		flex.setWidget(1,1, lblCompanyName);
		flex.setWidget(1,2,listBoxCompany);
		
		flex.setWidget(2,1, lblMonth);
		flex.setWidget(2,2,listMonth);
		
		flex.setWidget(3,1, lblAllocation);
		flex.setWidget(3,2,listBoxAllocation);
		
		flex.setWidget(4,1, lblLineOfService);
		flex.setWidget(4,2,listJobType);
		
		flex.setWidget(5,1, lblDomain);
		flex.setWidget(5,2,listBoxDomain);
		
		flex.setWidget(6,1, lblUser);
		flex.setWidget(6,2,listUsers);
		
		
		flex.setWidget(7,2,btnSearchAllReport);
		
		
		add(flex);
		
		listBoxAllocation.addItem( "0","All Allocations" );
		for (Allocations allocations : Allocations.values()) {
			listBoxAllocation.addItem(allocations.getValue()+"", allocations.getName());
		}
		listBoxCompany.addItem( "0","All Companies" );
		for (Branches branches : Branches.values()) {
			listBoxCompany.addItem(branches.getValue()+"", branches.getName());
		}
		add(vpnlResult);
		
		btnSearchAllReport.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchAllReport();
			}});
			
		
		
		btnSearchJobWiseReport.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchJobWiseReport();
			}});
		
	}


	private void fetchJobWiseReport() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		//map.put("jobId", newListboxofjobslistValue)
		//map.put("companyId", new listBox of Branches Value)
		rpcService.fetchJobWiseReport(map, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchJobWiseReport failes"+ caught.getLocalizedMessage());
				
			}

			@Override
			public void onSuccess(String result) {
				Window.open("/JobWiseReport/report.xls", "_blank", "");
				MaterialToast.fireToast("Report Generated !");
			}
		});
	}


	private HashMap<String, Integer> fetchAllReport() {
		int selectedJob = (Integer.parseInt(listJobs.getSelectedValue()));
		int selectedCompany = (Integer.parseInt(listBoxCompany.getSelectedValue()));
		int selectedMonth = (Integer.parseInt(listMonth.getSelectedValue()));
		int selectedAllocation = (Integer.parseInt(listBoxAllocation.getSelectedValue()));
		int selectedLineOfService = (Integer.parseInt(listJobType.getSelectedValue()));
		int selectedDomain = (Integer.parseInt(listBoxDomain.getSelectedValue()));
		int selectedUser = (Integer.parseInt(listUsers.getSelectedValue()));
		
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();	
		map.put("jobId", selectedJob);
		map.put("companyId", selectedCompany);
		map.put("monthId", selectedMonth);
		map.put("allocationId", selectedAllocation);
		map.put("lineOfServiceId", selectedLineOfService);
		map.put("domainId", selectedDomain);
		map.put("userId", selectedUser);
		
		
		rpcService.fetchAllReport(map, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchAllReport failed" + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(String result) {
			
				Window.open("/FullReport/report.xls", "_blank", "");
				MaterialToast.fireToast("Report Generated !");
			}
		});
		return map;
	}


	private void fetchDomain() {
		rpcService.fetchDomains(new AsyncCallback<ArrayList<Domains>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetch domain failed"+caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<Domains> result) {
				listBoxDomain.addItem( "0","All Domains" );
				for (int i = 0; i < result.size(); i++) {
					listBoxDomain.addItem(result.get(i).getDomainId()+"",result.get(i).getName());
				}
				
			}
		});
	}
	
	private void setTable() {
		table.setEmptyTableWidget(new HTML("No Record found"));
		table.setRowCount(0);

		jobName = new Column<TimeSheet, String>(new TextCell()) {
			@Override
			public String getValue(TimeSheet object) {
				return object.getJobId().getJobName();
			}
		};
		
		table.addColumn(jobName,"Name");
		
		jobType = new Column<TimeSheet, String>(new TextCell()) {
			@Override
			public String getValue(TimeSheet object) {
				return object.getJobId().getDomainId().getName();
			}
		};
		
		table.addColumn(jobType,"Job Type");
		
		users = new Column<TimeSheet, String>(new TextCell()) {
			@Override
			public String getValue(TimeSheet object) {
				return object.getJobId().getJobName();
			}
		};
		
		table.addColumn(users,"User");
		
		jobName = new Column<TimeSheet, String>(new TextCell()) {
			@Override
			public String getValue(TimeSheet object) {
				return object.getJobId().getJobName();
			}
		};
		
		table.addColumn(jobName,"Name");
	
	
	
	}
	private void fetchTimeReport() {
		int selectedMonth = Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));

		int selecteduser = Integer.parseInt(listUsers.getValue(listUsers.getSelectedIndex()));
		int selectedJob = Integer.parseInt(listJobs.getValue(listJobs.getSelectedIndex()));
		int selectedJobType = Integer.parseInt(listJobType.getValue(listJobType.getSelectedIndex()));
		
		
		
		rpcService.fetchTimeReport(selectedJob, selectedMonth, selecteduser, selectedJobType,  new AsyncCallback<ArrayList<TimeSheetReportDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchTimeReport");
			}

			@Override
			public void onSuccess(ArrayList<TimeSheetReportDTO> result) {
				vpnlResult.clear();
				for(int i=0; i< result.size(); i++){
					TimeSheetReportWidget timeSheetReportWidget = new TimeSheetReportWidget(result.get(i).getUsersList());
					timeSheetReportWidget.lblHeading.setText(result.get(i).getJobName()+" ("+result.get(i).getJobType()+") ");
					timeSheetReportWidget.getTotalHours().setText(result.get(i).getTotal()+"");
					
					String recoveryRat = NumberFormat.getFormat(".00").format(result.get(i).getActualRecoveryRate());
					timeSheetReportWidget.getRecoveryRate().setText(recoveryRat);
					
					//timeSheetReportWidget.getRecoveryRate().setText(result.get(i).getActualRecoveryRate()+"");
					timeSheetReportWidget.getFee().setText(result.get(i).getFee()+"");
					timeSheetReportWidget.getTimeCost().setText(result.get(i).getTimeCost()+"");
					vpnlResult.add(timeSheetReportWidget);
				}
//				Label lbl = new Label(result);
//				lbl.setStyleName("blue");
				
			}
		});
	}
	
	private void fetchJobs() {
		rpcService.fetchAllJobs(new AsyncCallback<ArrayList<Job>>() {
			
			@Override
			public void onSuccess(ArrayList<Job> result) {
				listJobs.clear();
				listJobs.addItem( "0","All Jobs" );
				for(int i=0; i< result.size(); i++){
					listJobs.addItem(  result.get(i).getJobId()+"",result.get(i).getJobName());
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs");
			}
		});
	}
	
	public void fetchUsers(){
		rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<User> result) {
				listUsers.clear();
				listUsers.addItem( "0","All users" );
				for(int i=0; i< result.size(); i++){
					listUsers.addItem(  result.get(i).getUserId()+"",result.get(i).getName());
				}
			}
		});
	}
	
	public void fetchJobType(){
		rpcService.getLineOfServices(new AsyncCallback<ArrayList<LineofService>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<LineofService> result) {
				listJobType.clear();
				listJobType.addItem( "0","All Job Types" );
				for(int i=0; i< result.size(); i++){
					listJobType.addItem(  result.get(i).getLineofServiceId()+"",result.get(i).getName());
				}
				
				fetchDomain();
			}
		});
	}
	
}

