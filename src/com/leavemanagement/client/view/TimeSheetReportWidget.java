package com.leavemanagement.client.view;

import java.util.ArrayList;

import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.shared.UserReportDTO;

public class TimeSheetReportWidget extends MaterialColumn{
	
	Label lblHeading = new Label();
	private MaterialColumn vpnlContainer = new MaterialColumn();
	private MaterialRow hpnl = new MaterialRow();
	private Label totalHours = new Label();
	private Label recoveryRate = new Label();
	private Label timeCost = new Label();
	private Label fee = new Label();
	private float totaltimeCost=0;
	public TimeSheetReportWidget(ArrayList<UserReportDTO> usersList){
		
		add(lblHeading);
		MaterialRow hpnlHeadings = new MaterialRow();
		Label lblUserName = new Label("UserName");
		Label lblHours = new Label("Hours");
		Label lblTimeCost = new Label("Time Cost");
		hpnlHeadings.add(lblUserName);
		hpnlHeadings.add(lblHours);
		hpnlHeadings.add(lblTimeCost);
		lblUserName.setWidth("100px");
		lblHours.setWidth("100px");
		lblTimeCost.setWidth("100px");
		lblUserName.setStyleName("bold");
		lblHours.setStyleName("bold");
		lblTimeCost.setStyleName("bold");
		add(hpnlHeadings);
		lblHeading.setStyleName("blue");
		totalHours.setWidth("100px");
		recoveryRate.setWidth("100px");
		fee.setWidth("100px");
//		float totalHours =0;
		
		for(int i=0;i< usersList.size(); i++){
			UserRepotWidget userReportWidget = new UserRepotWidget();
			userReportWidget.getActualHours().setText(usersList.get(i).getActualHours()+"");
			userReportWidget.getActualTimeCost().setText(usersList.get(i).getTimeCost()+"");
			userReportWidget.getUserName().setText(usersList.get(i).getUserName());
			totaltimeCost = totaltimeCost+ Float.parseFloat(userReportWidget.getActualTimeCost().getText());
//			totalHours = totalHours + usersList.get(i).getTimeCost();
//			totalTimeCost = totalTimeCost + usersList.get(i).getTimeCost();
			
			vpnlContainer.add(userReportWidget);
		}
		Label lblTotal = new Label("Total");
		Label lblFee = new Label("Fee");
		lblTotal.setStyleName("bold");
		Label lblRecoveryRate = new Label("RcoveryRate");
		lblRecoveryRate.setStyleName("bold");
		lblFee.setStyleName("bold");
		lblTotal.setWidth("90px");
		lblRecoveryRate.setWidth("90px");
		
		hpnl.add(lblTotal);
		hpnl.add(totalHours);
		hpnl.add(new Label(totaltimeCost+""));
		
		MaterialRow hpnlFee = new MaterialRow();
		hpnlFee.add(lblFee);
		lblFee.setStyleName("bold");
		hpnlFee.add(fee);
		lblFee.setWidth("90px");
		
		MaterialRow hpnlRecoveryRate = new MaterialRow();
		hpnlRecoveryRate.add(lblRecoveryRate);
		lblRecoveryRate.setStyleName("bold");
		hpnlRecoveryRate.add(recoveryRate);
		hpnlRecoveryRate.setWidth("90px");
		
		add(vpnlContainer);
		add(hpnl);
		add(hpnlFee);
		add(hpnlRecoveryRate);
	}
	public Label getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Label totalHours) {
		this.totalHours = totalHours;
	}
	
	public Label getFee() {
		return fee;
	}
	public void setFee(Label fee) {
		this.fee = fee;
	}
	public Label getRecoveryRate() {
		return recoveryRate;
	}
	public void setRecoveryRate(Label recoveryRate) {
		this.recoveryRate = recoveryRate;
	}
	public Label getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(Label timeCost) {
		this.timeCost = timeCost;
	}
	public float getTotaltimeCost() {
		return totaltimeCost;
	}
	public void setTotaltimeCost(float totaltimeCost) {
		this.totaltimeCost = totaltimeCost;
	}
	

}
