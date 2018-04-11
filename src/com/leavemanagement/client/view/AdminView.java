package com.leavemanagement.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.client.presenter.AdminPresenter.Display;
import com.leavemanagement.shared.User;


public class AdminView extends MaterialColumn implements IsWidget,Display {
	
	private MaterialColumn vpnlContainer = new MaterialColumn();
	private Anchor logOff = new Anchor("Log off");
	private Button btnBack = new Button("Back");
	private Anchor addUser = new Anchor("Add User");
	private Anchor changePassword = new Anchor("Change Password");
	private Anchor addCompany = new Anchor("Add Company");
	
	private MaterialColumn vpnlLeaveRecords = new MaterialColumn();
	private MaterialColumn vpnlEMployeeLeavesRecord = new MaterialColumn();
	private MaterialColumn vpnlJobCreation = new MaterialColumn();
	private MaterialColumn vpnlJobPlanning = new MaterialColumn();
	private JobCreationView jobCreationView;
	
	
	public AdminView(User loggedInUser){
		jobCreationView =new JobCreationView(null, loggedInUser);
		addCompany.setVisible(false);
		btnBack.addStyleName("btnStyle");
		MaterialRow hpnlWelcome = new MaterialRow();
		hpnlWelcome.setWidth("100%");
		MaterialRow hpnlSpace = new MaterialRow();
		hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);
		hpnlWelcome.add(logOff);
		MaterialRow hpnlHeader = new MaterialRow();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
		Label lblHeader = new Label("People Management System");
		lblHeader.setStyleName("headerSignin");
		hpnlHeader.setStyleName("headerSignin");
		hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		
		add(hpnlHeader);
		MaterialColumn vpnlLeave  = new MaterialColumn();
		vpnlLeave.setWidth("100%");
		add(vpnlLeave);
		vpnlLeave.add(addUser);
		vpnlLeave.add(changePassword);
		vpnlLeave.add(addCompany);
		TabPanel tabPanel = new TabPanel();
		tabPanel.add(vpnlLeaveRecords ,    "Pending Leave Requests");
		tabPanel.add(vpnlContainer, "Leaves History");
		tabPanel.add(vpnlEMployeeLeavesRecord, "Employees Leave Record");
		tabPanel.add(jobCreationView, "Job Creation");
		tabPanel.add(new AttributesView(loggedInUser), "Attributes");
		tabPanel.add(new RatingView(loggedInUser), "Rating");
		tabPanel.add(new TimeSheetView(loggedInUser), "Time Sheet");
		if(loggedInUser.getReportingTo()==5){
		tabPanel.add(new TimeSheetReportView(loggedInUser), "Time Report");
		}
		
		
		add(tabPanel);
		tabPanel.selectTab(0);
		
		logOff.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}});
	}


	public MaterialColumn getVpnlContainer() {
		return vpnlContainer;
	}


	public void setVpnlContainer(MaterialColumn vpnlContainer) {
		this.vpnlContainer = vpnlContainer;
	}


	public Button getBtnBack() {
		return btnBack;
	}


	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}


	public Anchor getAddUser() {
		return addUser;
	}


	public void setAddUser(Anchor addUser) {
		this.addUser = addUser;
	}


	public MaterialColumn getVpnlLeaveRecords() {
		return vpnlLeaveRecords;
	}


	public void setVpnlLeaveRecords(MaterialColumn vpnlLeaveRecords) {
		this.vpnlLeaveRecords = vpnlLeaveRecords;
	}


	public MaterialColumn getVpnlEMployeeLeavesRecord() {
		return vpnlEMployeeLeavesRecord;
	}


	public void setVpnlEMployeeLeavesRecord(MaterialColumn vpnlEMployeeLeavesRecord) {
		this.vpnlEMployeeLeavesRecord = vpnlEMployeeLeavesRecord;
	}


	public Anchor getChangePassword() {
		return changePassword;
	}


	public void setChangePassword(Anchor changePassword) {
		this.changePassword = changePassword;
	}


	public Anchor getAddCompany() {
		return addCompany;
	}


	public void setAddCompany(Anchor addCompany) {
		this.addCompany = addCompany;
	}


	public MaterialColumn getVpnlJobCreation() {
		return vpnlJobCreation;
	}


	public void setVpnlJobCreation(MaterialColumn vpnlJobCreation) {
		this.vpnlJobCreation = vpnlJobCreation;
	}


	public JobCreationView getJobCreationView() {
		return jobCreationView;
	}


	public void setJobCreationView(JobCreationView jobCreationView) {
		this.jobCreationView = jobCreationView;
	}
	

	
}
