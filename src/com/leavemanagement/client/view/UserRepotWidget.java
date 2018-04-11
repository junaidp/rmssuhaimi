package com.leavemanagement.client.view;

import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;

public class UserRepotWidget extends MaterialRow{
	private Label actualHours = new Label();
	private Label actualTimeCost = new Label();
	private Label userName = new Label();
	
	public UserRepotWidget(){
		add(userName);
		add(actualHours);
		add(actualTimeCost);
		userName.setWidth("100px");
		actualHours.setWidth("100px");
		actualTimeCost.setWidth("100px");
	}

	public Label getActualHours() {
		return actualHours;
	}

	public void setActualHours(Label actualHours) {
		this.actualHours = actualHours;
	}

	public Label getActualTimeCost() {
		return actualTimeCost;
	}

	public void setActualTimeCost(Label actualTimeCost) {
		this.actualTimeCost = actualTimeCost;
	}

	public Label getUserName() {
		return userName;
	}

	public void setUserName(Label userName) {
		this.userName = userName;
	}

}
