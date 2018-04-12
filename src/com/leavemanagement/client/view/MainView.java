package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Anchor;
import gwt.material.design.client.ui.MaterialButton;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import gwt.material.design.client.ui.MaterialColumn;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.presenter.MainPresenter.Display;


public class MainView extends MaterialColumn implements IsWidget,Display {
	
	
	private MaterialColumn vp;
	private MaterialColumn vpnlAvailableLeaves = new MaterialColumn();
	private ListBox listLeaves= new ListBox();
	private DateBox from = new DateBox();
	private DateBox to = new DateBox();
	private Label lblNoOfDays = new Label("");
	private TextArea reason = new TextArea();
	private MaterialButton btnSubmit = new MaterialButton("Submit");
	private Anchor logOff = new Anchor("Log off");
	private Label loggedInUserName = new Label();
	private Anchor  leaveHistory = new Anchor("Leave History");
	private Anchor changePassword = new Anchor("Change Password");
	private Anchor adminView = new Anchor("Admin");
	
	public MainView(){
		btnSubmit.setStyleName("btnStyle");
		setWidth("100%");
		MaterialRow hpnlWelcome = new MaterialRow();
		hpnlWelcome.setWidth("100%");
		MaterialRow hpnlSpace = new MaterialRow();
		hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);
		loggedInUserName.setStyleName("blue");
		hpnlWelcome.add(loggedInUserName);
		MaterialRow hpnlHeader = new MaterialRow();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
		Label lblHeader = new Label("Leave Management System");
		lblHeader.setStyleName("headerSignin");
		hpnlWelcome.setStyleName("headerSignin");
		hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		Label lblAvailableLeaves = new Label("Available Leaves");
		MaterialColumn vpnlLeave  = new MaterialColumn();
		vpnlLeave.setWidth("100%");
//		MaterialRow hpnlSpaceLeave = new MaterialRow();
//		hpnlSpaceLeave.setWidth("800px");
//		hpnlLeave.add(hpnlSpaceLeave);
		vpnlLeave.add(changePassword);
		vpnlLeave.add(leaveHistory);
		vpnlLeave.add(adminView);
		vpnlLeave.add(logOff);
		add(hpnlHeader);
		add(vpnlLeave);
		add(lblAvailableLeaves);
		add(vpnlAvailableLeaves);
		
		Label lblRequest = new Label("Leave Request");
		lblRequest.setStyleName("blue");
		lblAvailableLeaves.setStyleName("blue");
		add(lblRequest);
		add(listLeaves);
		MaterialRow hpnlDates = new MaterialRow();
		MaterialColumn vpnlFrom = new MaterialColumn();
		MaterialColumn vpnlTo = new MaterialColumn();
		vpnlFrom.add(new Label("From"));
		vpnlFrom.add(from);
		vpnlTo.add(new Label("To"));
		vpnlTo.add(to);
		hpnlDates.add(vpnlFrom);
		hpnlDates.add(vpnlTo);
		add(hpnlDates);
		add(lblNoOfDays);
		Label lblReason = new Label("Reason");
		lblReason.setStyleName("blue");
		add(lblReason);
		add(reason);
		add(btnSubmit);
		reason.setSize("400px", "150px");
		
		
	}
	
//	public Widget asWidget() {
//		return btnSend;
//
//	}
	

	public MaterialColumn getVpnlAvailableLeaves() {
		return vpnlAvailableLeaves;
	}

	public void setVpnlAvailableLeaves(MaterialColumn vpnlAvailableLeaves) {
		this.vpnlAvailableLeaves = vpnlAvailableLeaves;
	}

	public ListBox getListLeaves() {
		return listLeaves;
	}

	public void setListLeaves(ListBox listLeaves) {
		this.listLeaves = listLeaves;
	}

	public DateBox getFrom() {
		return from;
	}

	public void setFrom(DateBox from) {
		this.from = from;
	}

	public DateBox getTo() {
		return to;
	}

	public void setTo(DateBox to) {
		this.to = to;
	}

	public Label getLblNoOfDays() {
		return lblNoOfDays;
	}

	public void setLblNoOfDays(Label lblNoOfDays) {
		this.lblNoOfDays = lblNoOfDays;
	}

	public TextArea getReason() {
		return reason;
	}

	public void setReason(TextArea reason) {
		this.reason = reason;
	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public Anchor getLogOff() {
		return logOff;
	}

	public void setLogOff(Anchor logOff) {
		this.logOff = logOff;
	}

	public Label getLoggedInUserName() {
		return loggedInUserName;
	}

	public void setLoggedInUserName(Label loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	public Anchor getLeaveHistory() {
		return leaveHistory;
	}

	public void setLeaveHistory(Anchor leaveHistory) {
		this.leaveHistory = leaveHistory;
	}

	public Anchor getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Anchor changePassword) {
		this.changePassword = changePassword;
	}

	public Anchor getAdminView() {
		return adminView;
	}

	public void setAdminView(Anchor adminView) {
		this.adminView = adminView;
	}

}
