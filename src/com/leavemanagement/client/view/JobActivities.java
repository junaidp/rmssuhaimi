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
import gwt.material.design.client.ui.MaterialToast;

public class JobActivities extends MaterialRow {
	int totalhours=0; 
	ArrayList<JobActivityEntity> jobActivities =new ArrayList<JobActivityEntity>();
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private MaterialTextBox txtBoxTotalHours = new MaterialTextBox();
	private MaterialTextBox txtBoxPlanning = new MaterialTextBox();
	private MaterialTextBox txtBoxReporting = new MaterialTextBox();
	private MaterialTextBox txtBoxExecution = new MaterialTextBox();
	private MaterialTextBox txtBoxFollowUp = new MaterialTextBox();
	private  MaterialListBox listBoxDesignation = new MaterialListBox();
	ArrayList<JobActivityEntity> savedActivites;
	

	public JobActivities(){

		MaterialLabel lblDesignation = new MaterialLabel("Designation");
		MaterialLabel lblPlanning = new MaterialLabel("Planning");
		MaterialLabel lblReporting = new MaterialLabel("Reporting");
		MaterialLabel lblExecution = new MaterialLabel("Execution");
		MaterialLabel lblFollowUp = new MaterialLabel("Follow Up");
		MaterialLabel lblTotalHours = new MaterialLabel("Total Hours");

		listBoxDesignation.addItem("0","Senior Auditor");
		listBoxDesignation.addItem("1","Chief Auditor");
		MaterialButton btnSave = new MaterialButton("Save");


		//Styling
		lblDesignation.addStyleName("blueBold");
		lblPlanning.addStyleName("blueBold");
		lblExecution.addStyleName("blueBold");
		lblReporting.addStyleName("blueBold");
		lblFollowUp.addStyleName("blueBold");
		lblTotalHours.addStyleName("blueBold");

		setTextBoxes(listBoxDesignation);
		

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


		calculateHours();
		setHandlers(listBoxDesignation, btnSave);
		
		
		
	}

	private void setTextBoxes(final MaterialListBox listBoxDesignation) {
		txtBoxExecution.setWidth("100px");
		listBoxDesignation.setWidth("100px");
		txtBoxFollowUp.setWidth("100px");
		txtBoxPlanning.setWidth("100px");
		txtBoxReporting.setWidth("100px");
		txtBoxTotalHours.setWidth("100px");
		//End styling
		
		txtBoxFollowUp.setAllowBlank(false);
		txtBoxPlanning.setAllowBlank(false);
		txtBoxReporting.setAllowBlank(false);
		txtBoxExecution.setAllowBlank(false);
		txtBoxTotalHours.setEnabled(false);
		
		
		txtBoxFollowUp.setPlaceholder("0");
		txtBoxPlanning.setPlaceholder("0");
		txtBoxReporting.setPlaceholder("0");
		txtBoxExecution.setPlaceholder("0");
		
		
	}

	private void emptyTextBoxes() {
		 	txtBoxFollowUp.clear();
			txtBoxPlanning.clear();
			txtBoxReporting.clear();
			txtBoxExecution.clear();
		
	}

	private void setHandlers(final MaterialListBox listBoxDesignation, MaterialButton btnSave) {
		
		listBoxDesignation.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				emptyTextBoxes();
				if(savedActivites != null && savedActivites.size()>0)
				poupulateSavedActivites();
				
			}

			
		});
		
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
				emptyTextBoxes();
				MaterialToast.fireToast("Hours saved for : "+ listBoxDesignation.getSelectedItemText());
				try{
				listBoxDesignation.setSelectedIndex(listBoxDesignation.getSelectedIndex()+1);
				}catch(Exception ex){
					// no more data in listbox
				}

			}
		});
	}

	private void calculateHours() {
		
		
		
		txtBoxPlanning.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				calculateHours(txtBoxPlanning);

			}


		});

		txtBoxExecution.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				calculateHours(txtBoxExecution);
			}
		});

		txtBoxReporting.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				calculateHours(txtBoxReporting);
			}
		});
		
		txtBoxFollowUp.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				calculateHours(txtBoxFollowUp);
			}
		});
	}

	private void calculateHours( MaterialTextBox textBox) {
		try{
		textBox.reset();
		int hours = Integer.parseInt(textBox.getText());
		totalhours = totalhours+ hours;
		txtBoxTotalHours.setText(totalhours+"");
		
		}catch(Exception ex){
			textBox.setError("Enter valid number");
		}
		
	}

	public ArrayList<JobActivityEntity> getJobActivities() {
		return jobActivities;
	}

	public void setJobActivities(ArrayList<JobActivityEntity> jobActivities) {
		this.jobActivities = jobActivities;
	}
	
	public void poupulateSavedActivites(ArrayList<JobActivityEntity> activites){
		savedActivites = activites;
		for(int i=0; i<activites.size(); i++){
			if(activites.get(i).getDesignation() == Integer.parseInt(listBoxDesignation.getSelectedValue())){
				
				txtBoxExecution.setText(activites.get(i).getExecution()+"");
				txtBoxFollowUp.setText(activites.get(i).getFollowup()+"");
				txtBoxPlanning.setText(activites.get(i).getPlanning()+"");
				txtBoxReporting.setText(activites.get(i).getReporting()+"");
				
			}
			txtBoxTotalHours.setText(activites.get(i).getExecution()+activites.get(i).getFollowup()+activites.get(i).getPlanning()+activites.get(i).getReporting()+"");
			
			
		}
		
	}
	
	public void poupulateSavedActivites(){
		for(int i=0; i<savedActivites.size(); i++){
			if(savedActivites.get(i).getDesignation() == Integer.parseInt(listBoxDesignation.getSelectedValue())){
				
				txtBoxExecution.setText(savedActivites.get(i).getExecution()+"");
				txtBoxFollowUp.setText(savedActivites.get(i).getFollowup()+"");
				txtBoxPlanning.setText(savedActivites.get(i).getPlanning()+"");
				txtBoxReporting.setText(savedActivites.get(i).getReporting()+"");
			//	txtBoxTotalHours.setText(activites.get(i).getTotalHours+"");
				
			}
			
		}
		
	}
}
