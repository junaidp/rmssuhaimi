package com.leavemanagement.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.AttributeRating;

public class AddRatingWidget extends MaterialRow {

	Label txtAttribute = new Label();
	ListBox lstAttribute = new ListBox();
	MaterialButton btnSave = new MaterialButton("Save");
	MaterialButton btnEdit = new MaterialButton("Edit");
	ListBox listScore = new ListBox();
	Label lblRating = new Label();
	private int attributeIdRatingId=0;
	
	private int jobId=0;
	private int attributeId = 0;
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);

	public AddRatingWidget(final ListBox listUsers) {
		lstAttribute.addItem("10%", "0");
		lstAttribute.addItem("20%", "1");
		lstAttribute.addItem("30%", "2");
		lstAttribute.addItem("40%", "3");
		lstAttribute.addItem("50%", "4");
		lstAttribute.setEnabled(false);
		listScore.addItem("0");
		listScore.addItem("1");
		listScore.addItem("2");
		listScore.addItem("3");
		
		btnEdit.setVisible(false);
		btnSave.setWidth("100px");
		btnEdit.setWidth("100px");
		
		add(txtAttribute);
		add(lstAttribute);
		add(listScore);
		add(lblRating);
		txtAttribute.setWidth("300px");
		lblRating.setWidth("100px");
		add(btnSave);
		add(btnEdit);
		lblRating.setStyleName("blue");
		
		txtAttribute.setHeight("20px");
		lstAttribute.setHeight("30px");
		btnSave.setHeight("30px");
		

		calculate();
		btnEdit.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				enable();
			}});
		
		btnSave.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				int score = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
				String rating = lblRating.getText();
				String name = txtAttribute.getText();
				int level = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
				int userId =0;
				try{
				userId = Integer.parseInt(listUsers.getValue(listUsers.getSelectedIndex()));
				}catch(Exception ex){
					Window.alert("please select user");
				}
				AttributeRating attributeRating = new AttributeRating();
				attributeRating.setAttributeId(attributeId);
				attributeRating.setRating(rating);
				attributeRating.setScore(score);
//				attributeRating.setLevel(level);
				attributeRating.setUserId(userId);
				attributeRating.setJobId(jobId);
//				attributeRating.setAttributeIdRatingId(attributeIdRatingId);
				rpcService.saveRating(attributeRating, new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fail save rating");
					}

					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}});
				
			}});
	}

	

	private void calculate() {
		listScore.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				calcultateAndupdate();
			}

			});
		
		lstAttribute.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				calcultateAndupdate();
			}

			});
	}
	
	private void calcultateAndupdate() {
		float score = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
		String attributeValue = lstAttribute.getItemText(lstAttribute.getSelectedIndex());
		attributeValue = attributeValue.substring(0, attributeValue.length()-1);
		float attrValue = Float.parseFloat(attributeValue) /100;
		float rating = score* attrValue;
		String ratings =rating+"";
		if(ratings.length()>4){
		ratings = ratings.substring(0, 4);
		}
		lblRating.setText(ratings);
	}



	public ListBox getLstAttribute() {
		return lstAttribute;
	}

	public void setLstAttribute(ListBox lstAttribute) {
		this.lstAttribute = lstAttribute;
	}

	public MaterialButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(MaterialButton btnSave) {
		this.btnSave = btnSave;
	}

	
	public void readOnlyView(){
		btnSave.setVisible(false);
		listScore.setEnabled(false);
		lstAttribute.setEnabled(false);
		btnEdit.setVisible(false);
		listScore.setEnabled(false);
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}



	public Label getLblRating() {
		return lblRating;
	}



	public void setLblRating(Label lblRating) {
		this.lblRating = lblRating;
	}



	public Label getTxtAttribute() {
		return txtAttribute;
	}



	public void setTxtAttribute(Label txtAttribute) {
		this.txtAttribute = txtAttribute;
	}



	public ListBox getListScore() {
		return listScore;
	}



	public void setListScore(ListBox listScore) {
		this.listScore = listScore;
	}



	public int getAttributeId() {
		return attributeId;
	}



	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}



	public void disable() {
		btnSave.setEnabled(false);
		listScore.setEnabled(false);
		lstAttribute.setEnabled(false);
		btnEdit.setVisible(true);
		
	}
	public void enable() {
		btnSave.setEnabled(true);
		listScore.setEnabled(true);
//		lstAttribute.setEnabled(true);
		
		
	}
	public void refreshFields(){
		enable();
		listScore.setSelectedIndex(0);
		lblRating.setText("");
		btnEdit.setVisible(false);
//		lstAttribute.setSelectedIndex(0);
	}



	public int getAttributeIdRatingId() {
		return attributeIdRatingId;
	}



	public void setAttributeIdRatingId(int attributeIdRatingId) {
		this.attributeIdRatingId = attributeIdRatingId;
	}



	
}
