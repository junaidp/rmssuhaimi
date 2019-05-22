package com.leavemanagement.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
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
	FlexTable flex;
	@UiField
	MaterialRow rowTotal;
	Label heading;
	MaterialColumn c1 = new MaterialColumn();
	ScrollPanel panelScroll = new ScrollPanel();
	private boolean chargeable = false;
	MaterialListBox listMonth = new MaterialListBox();
	MaterialCheckBox chkChargeable = new MaterialCheckBox();
	Boolean booleanJobClick = false;
	Label lblTotalHour = new Label("Total Hours for all Jobs:");
	MaterialColumn vpHeading;
	private MaterialColumn vpLabel = new MaterialColumn();
	private ArrayList<Job> listjob = new ArrayList<Job>();
	private static TimeSheetTreeUiBinder uiBinder = GWT.create(TimeSheetTreeUiBinder.class);

	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);

	interface TimeSheetTreeUiBinder extends UiBinder<Widget, TimeSheetTree> {
	}

	public TimeSheetTree(final User loggedInUser) {

		initWidget(uiBinder.createAndBindUi(this));
		System.out.println("hello");
		// for (int k = 0; k < 31; k++) {
		// // Window.alert("in loop k");
		//
		// VerticalPanel vpHeading = new VerticalPanel();
		// Label heading = new Label(k + 1 + "");
		// heading.addStyleName("blueBold");
		// heading.setWidth("30px");
		// vpHeading.add(heading);
		//
		// flex.getElement().getStyle().setMarginLeft(250, Unit.PX);
		// flex.setWidget(0, k + 1, vpHeading);
		//
		// }
		rowMonth.add(listMonth);
		rowMonth.add(chkChargeable);
		//
		// lblTotalHour.setWidth("300px");
		lblTotalHour.setVisible(false);

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
				lblTotalHour.setVisible(true);
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
				// rowTotal.getElement().getStyle().setMarginLeft(20, Unit.PX);
				rowTotal.clear();

				// tree.clear();
				calculateTotalHoursForTimeSheet(result, loggedInUser);
				// end
				for (int i = 0; i < result.size(); i++) {
					final Job job = result.get(i);
					final MaterialTreeItem treeItem1 = new MaterialTreeItem();
					treeItem1.setIconType(IconType.FOLDER_SHARED);

					final Data data = new Data();
					data.setDataDisplayed(false);

					treeItem1.setText(result.get(i).getJobName());
					tree.add(treeItem1);

					clickHandler(loggedInUser, job, treeItem1);
				}

			}

			private void clickHandler(final User loggedInUser, final Job job, final MaterialTreeItem treeItem1) {
				treeItem1.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						if (!listjob.contains(job)) {
							if (booleanJobClick == false) {
								booleanJobClick = true;
								fetchSelectedJobForTimeSheet(loggedInUser, job, treeItem1);
							}
							booleanJobClick = false;
							listjob.add(job);
						}

					}
				});
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs " + caught.getLocalizedMessage());

			}
		});
	}

	private void fetchSelectedJobForTimeSheet(final User loggedInUser, Job job, final MaterialTreeItem treeItem1) {

		rpcService.fetchSelectedJobForTimeSheet(loggedInUser, chargeable, job.getJobId(),
				new AsyncCallback<ArrayList<Job>>() {

					@Override
					public void onSuccess(ArrayList<Job> job) {
						displayInTree(loggedInUser, job.get(0), treeItem1, listMonth, chargeable);

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("faile fetch selectedd job" + caught.getLocalizedMessage());

					}
				});

	}

	public void calculateTotalHoursForTimeSheet(List<Job> result, User loggedInUser) {
		rowTotal.add(vpLabel);
		for (int k = 1; k <= 31; k++) {

			vpLabel.add(lblTotalHour);
			MaterialLabel lblSum = new MaterialLabel("0");
			vpHeading = new MaterialColumn();
			lblSum.setWidth("9px");
			Data data = new Data();
			data.setSum(0);

			heading = new Label(k + "");
			// Window.alert("after adding lbl heading");
			heading.addStyleName("blueBold1");
			lblTotalHour.addStyleName("blueBold1");

			vpHeading.add(heading);
			vpHeading.add(lblSum);

			rowTotal.add(vpHeading);
			for (int i = 0; i < result.size(); i++) {
				hoursCalculate(k, result.get(i).getTimeSheets(), data, lblSum, listMonth, loggedInUser);
			}

			lblSum.setText(data.getSum() + "");
		}

	}

	// on keyup
	protected void updateTotalHours(int day, float currentHours, float newHours) {

		try {
			MaterialColumn vpHeading = (MaterialColumn) rowTotal.getWidget(day + 1);
			MaterialLabel lblSum = (MaterialLabel) vpHeading.getWidget(1);
			float oldHours = Float.parseFloat(lblSum.getText());
			float totalHours = oldHours - currentHours;
			totalHours = totalHours + newHours;

			lblSum.setText(totalHours + "");
		} catch (Exception ex) {
			System.out.println("error in update Total Hours(TimeSheetTree");
		}
	}

	public void hoursCalculate(int k, ArrayList<TimeSheet> timeSheets, Data data, MaterialLabel lblSum,
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

		MaterialTreeItem tree2 = new MaterialTreeItem();
		ScrollPanel scrolltimesheet = new ScrollPanel();
		scrolltimesheet.setHeight("400px");
		// scrolltimesheet.setWidth("1500px");

		final TimeSheetTableView timeSheetTable = new TimeSheetTableView(job, loggedInUser, listMonth, chargeable2,
				this);
		scrolltimesheet.add(timeSheetTable);
		tree2.add(scrolltimesheet);

		treeItem1.add(tree2);

	}

}
