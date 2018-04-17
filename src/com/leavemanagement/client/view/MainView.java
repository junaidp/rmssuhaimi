package com.leavemanagement.client.view;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;

import gwt.material.design.addins.client.richeditor.MaterialRichEditor;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;

import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.presenter.MainPresenter.Display;


public class MainView extends MaterialColumn implements IsWidget,Display {
	
	
	private MaterialColumn vp;
	private MaterialColumn vpnlAvailableLeaves = new MaterialColumn();
	private MaterialListBox listLeaves= new MaterialListBox();
	private DateBox from = new DateBox();
	private DateBox to = new DateBox();
	private Label lblNoOfDays = new Label("");
	private MaterialRichEditor reason = new MaterialRichEditor();
	private MaterialButton btnSubmit = new MaterialButton("Submit");
	private Anchor logOff = new Anchor("Log off");
	private Label loggedInUserName = new Label();
	private Anchor  leaveHistory = new Anchor("Leave History");
	private Anchor changePassword = new Anchor("Change Password");
	private Anchor adminView = new Anchor("Admin");
	
	public MainView(){
		 
		
		MaterialRow hpnlWelcome = new MaterialRow();
		hpnlWelcome.setWidth("100%");
       	MaterialRow hpnlSpace = new MaterialRow();
	    hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);
		loggedInUserName.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		loggedInUserName.getElement().getStyle().setBorderWidth(4, Unit.PX);

		loggedInUserName.setStyleName("blue");
		hpnlWelcome.add(loggedInUserName);
		MaterialRow hpnlHeader = new MaterialRow();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
		Label lblHeader = new Label("Leave Management System");
		lblHeader.setStyleName("headerSignin");
		hpnlWelcome.setStyleName("headerSignin");
		//hpnlHeader.add(lblHeader);
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
		lblAvailableLeaves.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		lblAvailableLeaves.getElement().getStyle().setBorderWidth(4, Unit.PX);

		lblRequest.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		lblRequest.getElement().getStyle().setBorderWidth(4, Unit.PX);

		add(lblRequest);
		add(listLeaves);
		MaterialRow hpnlDates = new MaterialRow();
		MaterialColumn vpnlFrom = new MaterialColumn();
		MaterialColumn vpnlTo = new MaterialColumn();
		Label From = new Label("From");
		vpnlFrom.add(From);
		vpnlFrom.add(from);
		vpnlTo.add(new Label("To"));
		vpnlTo.add(to);
		hpnlDates.add(vpnlFrom);
		hpnlDates.add(vpnlTo);
		add(hpnlDates);
		add(lblNoOfDays);
		
		Label lblReason = new Label("Reason");
		lblReason.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		lblReason.getElement().getStyle().setBorderWidth(4, Unit.PX);

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

	public MaterialListBox getListLeaves() {
		return listLeaves;
	}

	public void setListLeaves(MaterialListBox listLeaves) {
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

	public MaterialRichEditor getReason() {
		return reason;
	}

	public void setReason(MaterialRichEditor reason) {
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
