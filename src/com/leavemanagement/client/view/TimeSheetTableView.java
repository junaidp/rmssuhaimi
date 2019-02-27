package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Activity;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.User;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

public class TimeSheetTableView extends MaterialColumn {
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private User loggedInUser = null;
	private TimeSheetTree timeSheetTree;

	private int selectedMonth = 0;

	public TimeSheetTableView(final Job job, User loggedInUser2, MaterialListBox listMonth, final boolean chargeable2,
			TimeSheetTree timeSheetTree) {
		selectedMonth = Integer.parseInt(listMonth.getSelectedValue());

		this.timeSheetTree = timeSheetTree;

		MaterialRow columnMain = new MaterialRow();
		ScrollPanel scrollPanelWidth = new ScrollPanel();
		// columnMain.setWidth("1200px");
		this.loggedInUser = loggedInUser2;
		ScrollPanel scrollPanel = new ScrollPanel();

		MaterialButton btnSave = new MaterialButton("Save");
		final FlexTable flex = new FlexTable();
		final FlexTable flex1 = new FlexTable();
		// Window.alert("start ");

		setHandler(job, flex, flex1, listMonth);
		// Window.alert("before adding anything ");
		scrollPanel.setHeight("400px");
		scrollPanel.setWidth("1900px");
		scrollPanel.add(flex1);
		// Window.alert("adding flex1 in scrollpanel");
		ScrollPanel sc = new ScrollPanel();
		sc.setHeight("600px");
		sc.setWidth("1285px");
		// Window.alert("createed sc");
		columnMain.add(flex);
		// Window.alert("added flex in column main ");
		// add(flex);
		// add(scrollPanel);
		// add(btnSave);
		columnMain.add(scrollPanel);
		// Window.alert("added scrollpanel in column main");
		columnMain.add(btnSave);
		sc.add(columnMain);
		// Window.alert("adding cloumn main oto sc ");
		add(sc);

		saveHandler(job, chargeable2, btnSave, flex1, flex);
	}

	private void setHandler(final Job job, final FlexTable flex, final FlexTable flex1, MaterialListBox listMonth) {
		for (int k = 0; k < 31; k++) {
			// Window.alert("in loop k");
			VerticalPanel vpHeading = new VerticalPanel();
			final MaterialLabel lblSum = new MaterialLabel("0");
			final Data data = new Data();
			data.setDay(k);
			lblSum.setWidth("30px");

			Label heading = new Label(k + 1 + "");
			// Window.alert("after adding lbl heading");
			heading.addStyleName("blueBold");
			vpHeading.add(heading);
			heading.setWidth("30px");
			vpHeading.add(lblSum);
			flex.setWidget(0, k + 1, vpHeading);
			// Window.alert("lbltotal before");
			Label lblTotalHour = new Label("Total Hours:");
			lblTotalHour.setWidth("300px");
			flex.setWidget(0, 0, lblTotalHour);
			for (int i = 0; i < job.getActivityLists().size(); i++) {
				Activity activity = job.getActivityLists().get(i);
				Label lblName = new Label(activity.getActivityName());
				// Window.alert("lbname");
				lblName.setWidth("250px");
				lblName.setWordWrap(false);

				flex1.setWidget(i + 1, 0, lblName);

				// Window.alert("after adding lblname");
				final MaterialTextBox text = new MaterialTextBox();
				text.setText("0");
				text.setWidth("30px");
				flex1.setWidget(i + 1, k + 1, text);
				// Window.alert("befotre adding text");
				for (int m = 0; m < activity.getTimeSheets().size(); m++) {
					TimeSheet timeSheet = activity.getTimeSheets().get(m);
					if (timeSheet.getMonth() == selectedMonth && timeSheet.getDay() == k + 1
							&& job.getJobId() == timeSheet.getJobId().getJobId()) {
						text.setValue(timeSheet.getHours() + "");
						// break; //TODO

					}
				}

				try {

					data.setSum(data.getSum() + Float.parseFloat(text.getValue()));
					lblSum.setText(data.getSum() + "");
					// Window.alert("try data sum");
					text.addKeyPressHandler(new KeyPressHandler() {

						@Override
						public void onKeyPress(KeyPressEvent event) {

							if (!text.getText().isEmpty()) {
								data.setOldValue(text.getValue().isEmpty() ? 0 : Float.parseFloat(text.getValue()));
							}
						}

					});
					// timeSheetTree.hoursCalculate(k, timeSheets, data, lblSum,
					// listMonth2, loggedInUser);

					text.addKeyUpHandler(new KeyUpHandler() {

						@Override
						public void onKeyUp(KeyUpEvent event) {
							if (!text.getText().isEmpty()) {
								data.setSum(data.getSum() - data.getOldValue());

								data.setSum(data.getSum() + Float.parseFloat(text.getValue()));

								lblSum.setText(data.getSum() + "");

								// Uncomment this to test TotalHours
								timeSheetTree.updateTotalHours(data.getDay(), data.getOldValue(),
										Float.parseFloat(text.getValue()));

								data.setOldValue(Float.parseFloat(text.getValue()));
							}
						}
					});

				} catch (Exception ex) {
					System.out.println("no textbox here");
				}

				////////////

				// }

				Label lblActicityId = new Label();
				lblActicityId.setText(job.getActivityLists().get(i).getActivityId() + "");
				lblActicityId.setStyleName("white");
				flex1.setWidget(i + 1, 32, lblActicityId);

			}
		}
	}

	private void saveHandler(final Job job, final boolean chargeable2, MaterialButton btnSave, final FlexTable flex1,
			final FlexTable flex) {
		btnSave.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				saveTimeSheet(job, flex1, chargeable2, flex);
			}

		});
	}

	private void saveTimeSheet(Job job, FlexTable flex, boolean chargeable2, FlexTable flex2) {
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
					if (timeSheet.getHours() > 0)
						timeSheetList.add(timeSheet);

				}
			}

		}

		saveTimeSheetToDb(timeSheetList, chargeable2, job, flex, flex2);
	}

	private void saveTimeSheetToDb(ArrayList<TimeSheet> timeSheetList, final boolean chargeable2, final Job job,
			final FlexTable flex, final FlexTable flex2) {
		// final LoadingPopup loadingPopup = new LoadingPopup();
		// loadingPopup.display();
		MaterialLoader.loading(true);
		rpcService.saveTimeSheet(timeSheetList, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail savetime");
				// if (loadingPopup != null) {
				// loadingPopup.remove();
				// }
				MaterialLoader.loading(false);
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("time sheet saved");
				MaterialLoader.loading(false);
				// timeSheetTree.calculateTotalHoursForTimeSheet(result,
				// loggedInUser);
				// if (loadingPopup != null) {
				// loadingPopup.remove();
				// }
				// rpcService.fetchJobsForTimeSheet(loggedInUser, chargeable2,
				// new AsyncCallback<ArrayList<Job>>() {
				//
				// @Override
				// public void onFailure(Throwable caught) {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public void onSuccess(ArrayList<Job> result) {
				// // for (int j = 0; j < result.size(); j++) {
				// // final Job job1 = result.get(j);
				// // flex.clear();
				// // flex2.clear();
				// // setHandler(job, flex2, flex);
				// // }
				//
				// }
				//
				// });

			}
		});
	}

}