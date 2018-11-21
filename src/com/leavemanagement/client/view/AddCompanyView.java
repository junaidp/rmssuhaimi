package com.leavemanagement.client.view;

import com.google.gwt.user.client.Window;
import gwt.material.design.client.ui.MaterialButton;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.client.presenter.AddCompanyPresenter.Display;

public class AddCompanyView extends VerticalPanel implements Display {
	
	private MaterialButton btnSend = new MaterialButton("Save");
	MaterialTextBox txtUser = new MaterialTextBox();
	PasswordTextBox txtPassword = new PasswordTextBox();
	PasswordTextBox txtConfrimPassword = new PasswordTextBox();
	MaterialTextBox txtEmail = new MaterialTextBox();
	MaterialTextBox txtCompanyName = new MaterialTextBox();
	
	
	private MaterialButton btnUpdate = new MaterialButton("update");
	
	
	public AddCompanyView(){
		MaterialColumn vpnl = new MaterialColumn();
		add(vpnl);
		Label lblHeading = new Label("Add a new Company");
		lblHeading.addStyleName("blueBold");
		lblHeading.setWidth(Window.getClientWidth()-100+"px");
		MaterialRow hp = new MaterialRow();
		hp.setWidth("100%");
		vpnl.add(hp);
		hp.add(lblHeading);
		FlexTable flex = new FlexTable();
		Label lblUser = new Label("UserName");
		Label lblPassword = new Label("Password");
		Label lblConfirmPassword = new Label("Confirm Password");
		Label lblEmail = new Label("Email");
		Label lblCompanyName = new Label("Company Name");
		flex.setWidget(0, 0, lblUser);
		flex.setWidget(0, 1, txtUser);
		flex.setWidget(1, 0, lblPassword);
		flex.setWidget(1, 1, txtPassword);
		flex.setWidget(2, 0, lblConfirmPassword);
		flex.setWidget(2, 1, txtConfrimPassword);
		flex.setWidget(3, 0, lblEmail);
		flex.setWidget(3, 1, txtEmail);
		flex.setWidget(4, 0, lblCompanyName);
		flex.setWidget(4, 1, txtCompanyName);
		
		
		vpnl.add(flex);
		MaterialRow hpnButton = new MaterialRow();
		MaterialRow hpnSpace = new MaterialRow();
		hpnSpace.setWidth("150px");
		hpnButton.add(hpnSpace);
		hpnButton.add(btnSend);
		vpnl.add(hpnButton);
		layout();
		
	}
	
	public void layout(){
		
			txtUser.setWidth("200px");
			 txtPassword.setWidth("200px");
			 txtConfrimPassword.setWidth("200px");
			 txtEmail.setWidth("200px");
			
			 btnUpdate.setWidth("80px");
			 btnSend.setWidth("80px");
			 txtCompanyName.setWidth("200px");
	}

	public MaterialButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(MaterialButton btnSend) {
		this.btnSend = btnSend;
	}

	public MaterialTextBox getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(MaterialTextBox txtUser) {
		this.txtUser = txtUser;
	}

	public PasswordTextBox getTxtPassword() {
		return txtPassword;
	}

	

	public MaterialTextBox getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(MaterialTextBox txtEmail) {
		this.txtEmail = txtEmail;
	}

	public PasswordTextBox getTxtConfrimPassword() {
		return txtConfrimPassword;
	}

	public void setTxtConfrimPassword(PasswordTextBox txtConfrimPassword) {
		this.txtConfrimPassword = txtConfrimPassword;
	}

	public void setTxtPassword(PasswordTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}

	public MaterialTextBox getTxtCompanyName() {
		return txtCompanyName;
	}

	public void setTxtCompanyName(MaterialTextBox txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}

	

}
