package com.leavemanagement.client.view;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialRow;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class AddAttributeWidget extends MaterialRow {

	TextBox txtAttribute = new TextBox();
	ListBox lstAttribute = new ListBox();
	MaterialButton btnSave = new MaterialButton("Save");
	MaterialButton btnDelete = new MaterialButton("Delete");
	private int jobId=0;

	public AddAttributeWidget() {
		lstAttribute.addItem( "0","10%");
		lstAttribute.addItem( "1","20%");
		lstAttribute.addItem( "2","30%");
		lstAttribute.addItem( "3","40%");
		lstAttribute.addItem( "4","50%");

		btnSave.setWidth("100px");
		btnDelete.setWidth("100px");
		
		add(txtAttribute);
		add(lstAttribute);
		add(btnSave);
		add(btnDelete);
		
		txtAttribute.setHeight("20px");
		lstAttribute.setHeight("30px");
		btnSave.setHeight("30px");
		btnDelete.setHeight("30px");


	}

	public TextBox getTxtAttribute() {
		return txtAttribute;
	}

	public void setTxtAttribute(TextBox txtAttribute) {
		this.txtAttribute = txtAttribute;
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

	public MaterialButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(MaterialButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void readOnlyView(){
		btnDelete.setVisible(false);
		btnSave.setVisible(false);
		txtAttribute.setEnabled(false);
		lstAttribute.setEnabled(false);
		
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}
