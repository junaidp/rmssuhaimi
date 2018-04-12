package com.leavemanagement.client.view;

import com.google.gwt.user.client.Window;
import gwt.material.design.client.ui.MaterialButton;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.ui.MaterialColumn;
import com.leavemanagement.client.presenter.ChangePasswordPresenter.Display;

public class ChangePasswordView extends DecoratorPanel implements IsWidget,Display {

	PasswordTextBox oldPassword = new PasswordTextBox();
	PasswordTextBox newPassword = new PasswordTextBox();
	PasswordTextBox confrimPassword = new PasswordTextBox();
	MaterialButton btnSubmit = new MaterialButton("Submit");

	public ChangePasswordView(){
		btnSubmit.setStyleName("btnStyle");
		Label lblHeading = new Label("Change Password");
//		lblHeading.setStyleName("blueBackground");
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth()-100+"px");
		
		MaterialColumn vpnl = new MaterialColumn();
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, new Label("old password"));
		flex.setWidget(0, 1, oldPassword);
		flex.setWidget(1, 0, new Label("new password"));
		flex.setWidget(1, 1, newPassword);
		flex.setWidget(2, 0, new Label("confirm new password"));
		flex.setWidget(2, 1, confrimPassword);
		flex.setWidget(3, 1, btnSubmit);
		add(vpnl);
		MaterialRow hp = new MaterialRow();
		vpnl.add(hp);
		hp.add(lblHeading);
		hp.setWidth("100%");
		vpnl.add(flex);
		
		oldPassword.setWidth("200px");
		newPassword.setWidth("200px");
		confrimPassword.setWidth("200px");
		btnSubmit.setWidth("100px");
		
		
	}

	public TextBox getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(PasswordTextBox oldPassword) {
		this.oldPassword = oldPassword;
	}

	public TextBox getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(PasswordTextBox newPassword) {
		this.newPassword = newPassword;
	}

	public TextBox getConfrimPassword() {
		return confrimPassword;
	}

	public void setConfrimPassword(PasswordTextBox confrimPassword) {
		this.confrimPassword = confrimPassword;
	}

	public MaterialButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(MaterialButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
}
