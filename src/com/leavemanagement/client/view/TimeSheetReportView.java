package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.ui.MaterialButton;
import com.google.gwt.user.client.ui.HTML;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;

import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.TimeSheetReportDTO;
import com.leavemanagement.shared.User;

public class TimeSheetReportView extends MaterialColumn{
	
	private 	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private MaterialListBox listUsers = new MaterialListBox();
	private MaterialListBox listJobs = new MaterialListBox();
	private MaterialListBox listMonth = new MaterialListBox();
	private MaterialListBox listJobType = new MaterialListBox();
	private User loggedInUser = null;
	private MaterialButton btnSearch = new MaterialButton("Search");
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
		listMonth.addItem("11","Nev" );
		listMonth.addItem("12","Dec" );
		
		fetchJobs();
		fetchUsers();
		fetchJobType();
		
		MaterialRow hpnlSearch = new MaterialRow();
		add(hpnlSearch);
		
		hpnlSearch.add(listJobs);
		hpnlSearch.add(listMonth);
		hpnlSearch.add(listUsers);
		hpnlSearch.add(listJobType);
		hpnlSearch.add(btnSearch);
		add(vpnlResult);
		
		btnSearch.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchTimeReport();
			}});
		
		
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
					timeSheetReportWidget.getRecoveryRate().setText(result.get(i).getActualRecoveryRate()+"");
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
			}
		});
	}

}
