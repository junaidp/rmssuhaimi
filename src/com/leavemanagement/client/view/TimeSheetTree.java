package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Allocations;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.User;

import gwt.material.design.addins.client.tree.MaterialTree;
import gwt.material.design.addins.client.tree.MaterialTreeItem;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;

public class TimeSheetTree extends Composite {
	int selectedMonth = 0;
	@UiField
	MaterialTree tree;
	@UiField
	MaterialColumn rowMonth;
	
	MaterialColumn c1 = new MaterialColumn();
	ScrollPanel p1 = new ScrollPanel();
	private boolean chargeable = false;
	MaterialListBox listMonth = new MaterialListBox();
	MaterialCheckBox chkChargeable = new MaterialCheckBox();
	private static TimeSheetTreeUiBinder uiBinder = GWT.create(TimeSheetTreeUiBinder.class);
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	
	interface TimeSheetTreeUiBinder extends UiBinder<Widget, TimeSheetTree> {
	}

	public TimeSheetTree(final User loggedInUser) {
		
		

		initWidget(uiBinder.createAndBindUi(this));

		rowMonth.add(listMonth);
		rowMonth.add(chkChargeable);
		chkChargeable.setText(Allocations.CHARGEABLE.getName());
		listMonth.addItem("0", "Select Month");
		listMonth.addItem("1", "Jan");
		listMonth.addItem("2", "Feb");
		//		listMonth.addItem("Mar", "3");
		listMonth.addItem("3","Mar");
		listMonth.addItem("4","Apr");
		listMonth.addItem("5", "May");
		listMonth.addItem("6", "Jun");
		listMonth.addItem("7", "Jul");
		listMonth.addItem("8","Aug");
		listMonth.addItem("9", "Sep");
		listMonth.addItem("10","Oct");
		listMonth.addItem("11", "Nov");
		listMonth.addItem("12", "Dec");
		
//		fetchJobs(loggedInUser);
		
		listMonth.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				tree.clear();
				selectedMonth = Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
				fetchJobs(loggedInUser);
			}
		});
		
		chkChargeable.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
			tree.clear();
				chargeable = event.getValue();
			//	if(selectedMonth > 0){
					fetchJobs(loggedInUser);
			//	}
			}
		});
	

	}

	private void fetchJobs(final User loggedInUser) {
		rpcService.fetchJobsForTimeSheet(loggedInUser, chargeable, new AsyncCallback<ArrayList<Job>>() {

			@Override
			public void onSuccess( final ArrayList<Job> result) {
					
				for ( int i = 0; i < result.size(); i++) {
					final Job job = result.get(i);
					final MaterialTreeItem	treeItem1 = new MaterialTreeItem();
					treeItem1.setIconType(IconType.FOLDER_SHARED);

					treeItem1.setText(result.get(i).getJobName());
					tree.add(treeItem1);
					clickHandler(loggedInUser, job, treeItem1,listMonth);
					
				}

			}

			private void clickHandler(final User loggedInUser, final Job job, final MaterialTreeItem treeItem1, final MaterialListBox listMonth) {
				treeItem1.addDoubleClickHandler(new DoubleClickHandler() {
					
					@Override
					public void onDoubleClick(DoubleClickEvent event) {
						final TimeSheetTableView  timeSheetTable = new TimeSheetTableView(job,loggedInUser,listMonth);

						c1.clear();
						p1.clear();
						c1.add(timeSheetTable);
						p1.setHeight("400px");
						p1.setWidth("1360px");
						p1.add(c1);
						treeItem1.add(p1);
					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs "+ caught.getLocalizedMessage());

			}
		});
	}

}
