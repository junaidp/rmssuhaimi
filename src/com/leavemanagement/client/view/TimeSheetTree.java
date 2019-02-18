package com.leavemanagement.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Allocations;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.User;

import gwt.material.design.addins.client.tree.MaterialTree;
import gwt.material.design.addins.client.tree.MaterialTreeItem;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;

public class TimeSheetTree extends Composite {
	int selectedMonth = 0;
	@UiField
	MaterialTree tree;
	@UiField
	MaterialColumn rowMonth;
	@UiField
	MaterialRow rowTotal;
	Label heading;
	MaterialColumn c1 = new MaterialColumn();
	ScrollPanel panelScroll = new ScrollPanel();
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
		listMonth.addItem("3", "Mar");
		listMonth.addItem("4", "Apr");
		listMonth.addItem("5", "May");
		listMonth.addItem("6", "Jun");
		listMonth.addItem("7", "Jul");
		listMonth.addItem("8", "Aug");
		listMonth.addItem("9", "Sep");
		listMonth.addItem("10", "Oct");
		listMonth.addItem("11", "Nov");
		listMonth.addItem("12", "Dec");
		// panelScroll.setHeight("800px");
		// panelScroll.add(tree);
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
				fetchJobs(loggedInUser);
			}
		});

	}

	private void fetchJobs(final User loggedInUser) {
		rpcService.fetchJobsForTimeSheet(loggedInUser, chargeable, new AsyncCallback<ArrayList<Job>>() {

			@Override
			public void onSuccess(final ArrayList<Job> result) {

				// start
				rowTotal.clear();
				// tree.clear();
				calculateTotalHoursForTimeSheet(result, listMonth, loggedInUser);
				// end
				for (int i = 0; i < result.size(); i++) {
					final Job job = result.get(i);
					final MaterialTreeItem treeItem1 = new MaterialTreeItem();
					treeItem1.setIconType(IconType.FOLDER_SHARED);

					treeItem1.setText(result.get(i).getJobName());
					tree.add(treeItem1);
					//
					treeItem1.addDoubleClickHandler(new DoubleClickHandler() {

						@Override
						public void onDoubleClick(DoubleClickEvent event) {
							// tree.clear();
							// selectedMonth =
							// Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
							// fetchJobs(loggedInUser);
							displayInTree(loggedInUser, job, treeItem1, listMonth, chargeable);

						}

					});
					// treeItem1.addClickHandler(new ClickHandler() {
					//
					// @Override
					// public void onClick(ClickEvent event) {
					// // tree.clear();
					// selectedMonth =
					// Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
					// // fetchJobs(loggedInUser);
					// displayInTree(loggedInUser, job, treeItem1, listMonth,
					// chargeable);
					//
					// }
					// });

				}

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs " + caught.getLocalizedMessage());

			}
		});
	}

	private void calculateTotalHoursForTimeSheet(List<Job> result, MaterialListBox listMonth, User loggedInUser) {

		for (int k = 1; k < 32; k++) {

			MaterialColumn vpHeading = new MaterialColumn();
			MaterialLabel lblSum = new MaterialLabel("0");

			lblSum.setWidth("20px");
			Data data = new Data();
			data.setSum(0);

			heading = new Label(k + "");
			// Window.alert("after adding lbl heading");
			heading.addStyleName("blueBold");
			vpHeading.add(heading);
			vpHeading.add(lblSum);

			rowTotal.add(vpHeading);
			for (int i = 0; i < result.size(); i++) {
				hoursCalculate(k, result.get(i).getTimeSheets(), data, lblSum, listMonth, loggedInUser);
			}

			lblSum.setText(data.getSum() + "");
		}

	}

	private void hoursCalculate(int k, ArrayList<TimeSheet> timeSheets, Data data, MaterialLabel lblSum,
			MaterialListBox listMonth2, User loggedInUser) {

		for (int i = 0; i < timeSheets.size(); i++) {
			selectedMonth = Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
			if (k == timeSheets.get(i).getDay() && (selectedMonth == timeSheets.get(i).getMonth())) {
				float total = timeSheets.get(i).getHours();
				data.setSum(data.getSum() + total);
				// fetchJobs(loggedInUser);
			}

		}

	}

	private void displayInTree(final User loggedInUser, final Job job, final MaterialTreeItem treeItem1,
			final MaterialListBox listMonth, boolean chargeable2) {

		c1.clear();
		// p1.clear();
		// p1.setHeight("400px");
		// p1.setWidth("1360px");
		// p1.add(c1);
		// treeItem1.add(p1);
		treeItem1.add(c1);
		final TimeSheetTableView timeSheetTable = new TimeSheetTableView(job, loggedInUser, listMonth, chargeable2);
		c1.add(timeSheetTable);
	}

}
