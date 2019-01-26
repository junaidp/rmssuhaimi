package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Activity;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialTextBox;

public class TimeSheetTableView extends MaterialColumn {
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private User loggedInUser = null;

	private int selectedMonth = 0;

	public TimeSheetTableView(final Job job, User loggedInUser2, MaterialListBox listMonth) {
		selectedMonth = Integer.parseInt(listMonth.getSelectedValue());

		this.loggedInUser = loggedInUser2;
		MaterialButton btnSave = new MaterialButton("Save");
		final FlexTable flex = new FlexTable();
		for (int k = 0; k < 31; k++) {

			Label heading = new Label(k + 1 + "");
			heading.addStyleName("blueBold");
			flex.setWidget(0, k + 1, heading);
			flex.getFlexCellFormatter().setHorizontalAlignment(0, k + 1, HasHorizontalAlignment.ALIGN_CENTER);

		}

		for (int i = 0; i < job.getActivityLists().size(); i++) {
			Activity activity = job.getActivityLists().get(i);
			Label lblName = new Label(activity.getActivityName());
			lblName.setWordWrap(false);

			flex.setWidget(i + 1, 0, lblName);

			for (int j = 0; j < 31; j++) {

				MaterialTextBox text = new MaterialTextBox();
				text.setWidth("30px");
				flex.setWidget(i + 1, j + 1, text);
				for (int m = 0; m < activity.getTimeSheets().size(); m++) {
					TimeSheet timeSheet = activity.getTimeSheets().get(m);
					if (timeSheet.getMonth() == selectedMonth && timeSheet.getDay() == j + 1
							&& job.getJobId() == timeSheet.getJobId().getJobId()) {
						text.setText(timeSheet.getHours() + "");
					}
				}
			}

			Label lblActicityId = new Label();
			lblActicityId.setText(job.getActivityLists().get(i).getActivityId() + "");
			lblActicityId.setStyleName("white");
			flex.setWidget(i + 1, 32, lblActicityId);

		}

		add(flex);
		add(btnSave);

		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				saveTimeSheet(job, flex);
			}

		});
	}

	private void saveTimeSheet(Job job, FlexTable flex) {
		float totalHours = 0;
		ArrayList<TimeSheet> timeSheetList = new ArrayList<TimeSheet>();
		for (int i = 0; i < flex.getRowCount() - 1; i++) {
			for (int j = 0; j < 31; j++) {
				MaterialTextBox text = (MaterialTextBox) flex.getWidget(i + 1, j + 1);
				text.setWidth("30px");
				if (text.getText() != null && !text.getText().equals("")) {
					TimeSheet timeSheet = new TimeSheet();
					timeSheet.setDay(j + 1);
					timeSheet.setHours(Float.parseFloat(text.getText()));
					totalHours = totalHours + timeSheet.getHours();
					timeSheet.setMonth(selectedMonth);
					Label activityField = (Label) flex.getWidget(i + 1, 32);
					Activity activity = new Activity();
					activity.setActivityId(Integer.parseInt(activityField.getText()));
					timeSheet.setActivity(activity);
					timeSheet.setJobId(job);
					User user = new User();
					user.setUserId(loggedInUser.getUserId());
					timeSheet.setUserId(user);
					timeSheet.setStatus(0);
					timeSheetList.add(timeSheet);

				}
			}

		}
		saveTimeSheetToDb(timeSheetList);
	}

	private void saveTimeSheetToDb(ArrayList<TimeSheet> timeSheetList) {
		rpcService.saveTimeSheet(timeSheetList, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail savetime");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("time sheet saved");
			}
		});
	}

}
