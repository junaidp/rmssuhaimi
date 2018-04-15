package com.leavemanagement.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.event.ChangePasswordEvent;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialLink;

public class HeaderPresenter implements Presenter

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private User LoggedInUser = new User();



	public interface Display {
		Widget asWidget();
		MaterialLink getaddUser();
		MaterialLink getchangePassword();
		MaterialLink getaddCompany();
		MaterialLink getlogOff();


	}

	public HeaderPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.LoggedInUser = loggedInUser;
		if(loggedInUser.getRoleId().getRoleId()!=5){
			display.getaddUser().setVisible(false);
		}
		if(loggedInUser.getUserId()==1 && loggedInUser.getName().equals("faheem")){
			display.getaddCompany().setVisible(true);
		}
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	public void bind() {
		display.getaddUser().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				History.newItem("addUser");	
			}
		});
		display.getchangePassword().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new ChangePasswordEvent(LoggedInUser));
			
			}
		});
		display.getaddCompany().addClickHandler(new ClickHandler() {


			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				History.newItem("addCompany");

			}
		});

	}


}



