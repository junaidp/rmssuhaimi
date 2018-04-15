package com.leavemanagement.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.presenter.HeaderPresenter.Display;

import gwt.material.design.client.ui.MaterialLink;

public class HeaderView extends Composite implements Display {

	private static HeaderViewUiBinder uiBinder = GWT.create(HeaderViewUiBinder.class);

	interface HeaderViewUiBinder extends UiBinder<Widget, HeaderView> {
	}
	@UiField
	MaterialLink addUser;
	@UiField
	MaterialLink changePassword;
	@UiField
	MaterialLink addCompany;
	@UiField
	MaterialLink logOff;
	 
	public HeaderView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public MaterialLink getaddUser() {
		// TODO Auto-generated method stub
return addUser;
}

	@Override
	public MaterialLink getchangePassword() {
		return changePassword;
	}

	@Override
	public MaterialLink getaddCompany() {
   return addCompany;
	}

	@Override
	public MaterialLink getlogOff() {
		// TODO Auto-generated method stub
    return logOff;
	}

 

	 
 

	

}
