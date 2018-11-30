package com.leavemanagement.client.view;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.JobActivityEntity;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

public class JobActivities extends MaterialRow {
	int totalhours=0; 
	ArrayList<JobActivityEntity> jobActivities =new ArrayList<JobActivityEntity>();
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	public JobActivities(){
		
		MaterialLabel lblDesignation = new MaterialLabel("Designation");
		MaterialLabel lblPlanning = new MaterialLabel("Planning");
		MaterialLabel lblReporting = new MaterialLabel("Reporting");
		MaterialLabel lblExecution = new MaterialLabel("Execution");
		MaterialLabel lblFollowUp = new MaterialLabel("Follow Up");
		MaterialLabel lblTotalHours = new MaterialLabel("Total Hours");
		
		final MaterialListBox listBoxDesignation = new MaterialListBox();
		listBoxDesignation.addItem("Senior Auditor");
		listBoxDesignation.addItem("Chief Auditor");
		final MaterialTextBox txtBoxPlanning = new MaterialTextBox();
		final MaterialTextBox txtBoxReporting = new MaterialTextBox();
		final MaterialTextBox txtBoxExecution = new MaterialTextBox();
		final MaterialTextBox txtBoxFollowUp = new MaterialTextBox();
		final MaterialTextBox txtBoxTotalHours = new MaterialTextBox();
		MaterialButton btnSave = new MaterialButton("Save");
		
		
		//Styling
		lblDesignation.addStyleName("blueBold");
		lblPlanning.addStyleName("blueBold");
		lblExecution.addStyleName("blueBold");
		lblReporting.addStyleName("blueBold");
		lblFollowUp.addStyleName("blueBold");
		lblTotalHours.addStyleName("blueBold");
		
		txtBoxExecution.setWidth("100px");
		listBoxDesignation.setWidth("100px");
		txtBoxFollowUp.setWidth("100px");
		txtBoxPlanning.setWidth("100px");
		txtBoxReporting.setWidth("100px");
		txtBoxTotalHours.setWidth("100px");
		//End styling
		
		FlexTable flex = new FlexTable();

		flex.setWidget(1, 0, lblDesignation);
		flex.setWidget(2, 0, listBoxDesignation);
		
		flex.setWidget(1, 1, lblPlanning);
		flex.setWidget(2, 1, txtBoxPlanning);
		
		flex.setWidget(1, 2, lblReporting);
		flex.setWidget(2, 2, txtBoxReporting);
		
		flex.setWidget(1, 3, lblExecution);
		flex.setWidget(2, 3, txtBoxExecution);
		
		flex.setWidget(1, 4, lblFollowUp);
		flex.setWidget(2, 4, txtBoxFollowUp);
		
		flex.setWidget(3, 2, btnSave);
		
		flex.setWidget(3, 3, lblTotalHours);
		flex.setWidget(3, 4, txtBoxTotalHours);
		
		
		
		add(flex);
		
		
		calculateHours(txtBoxPlanning, txtBoxReporting, txtBoxExecution, txtBoxFollowUp, txtBoxTotalHours);
		saveHandler(listBoxDesignation, txtBoxPlanning, txtBoxReporting, txtBoxExecution, txtBoxFollowUp, btnSave);
	}

	private void saveHandler(final MaterialListBox listBoxDesignation, final MaterialTextBox txtBoxPlanning,
			final MaterialTextBox txtBoxReporting, final MaterialTextBox txtBoxExecution,
			final MaterialTextBox txtBoxFollowUp, MaterialButton btnSave) {
		btnSave.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				JobActivityEntity jobActivityEntity = new JobActivityEntity();
				jobActivityEntity.setPlanning(Integer.parseInt(txtBoxPlanning.getText()));
				jobActivityEntity.setReporting(Integer.parseInt(txtBoxReporting.getText()));
				jobActivityEntity.setExecution(Integer.parseInt(txtBoxExecution.getText()));
				jobActivityEntity.setFollowup(Integer.parseInt(txtBoxFollowUp.getText()));
				jobActivityEntity.setDesignation(listBoxDesignation.getSelectedIndex());
				jobActivities.add(jobActivityEntity);
				
			}
		});
	}
	
	private void calculateHours(final MaterialTextBox txtBoxPlanning, final MaterialTextBox txtBoxReporting,
			final MaterialTextBox txtBoxExecution, final MaterialTextBox txtBoxFollowUp,
			final MaterialTextBox txtBoxTotalHours) {
		txtBoxPlanning.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				totalhours = totalhours+ Integer.parseInt(txtBoxPlanning.getSelectedText());
				txtBoxTotalHours.setText(totalhours+"");
				
			}
		});
		
		txtBoxExecution.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				totalhours = totalhours+ Integer.parseInt(txtBoxExecution.getSelectedText());
				txtBoxTotalHours.setText(totalhours+"");
				Window.alert(""+totalhours);
			}
		});

		txtBoxReporting.addValueChangeHandler(new ValueChangeHandler<String>() {
		
		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			totalhours = totalhours+ Integer.parseInt(txtBoxReporting.getSelectedText());
			txtBoxTotalHours.setText(totalhours+"");
			Window.alert(""+totalhours);
		}
	});
		txtBoxFollowUp.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				totalhours = totalhours+ Integer.parseInt(txtBoxFollowUp.getSelectedText());
				txtBoxTotalHours.setText(totalhours+"");
			}
		});
	}

	public ArrayList<JobActivityEntity> getJobActivities() {
		return jobActivities;
	}

	public void setJobActivities(ArrayList<JobActivityEntity> jobActivities) {
		this.jobActivities = jobActivities;
	}
}
