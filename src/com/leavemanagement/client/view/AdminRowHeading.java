package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Label;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;

public class AdminRowHeading extends MaterialRow{
	
	public AdminRowHeading(){
	Label lblName = new Label("Name");
	Label lblType = new Label("Type");
	Label lblFrom = new Label("From");
	Label lblTo = new Label("To");
	Label lblDays = new Label("Days");
	Label lblDecision = new Label("Decision");
	
	
	lblName.setWidth("120px");
	lblType.setWidth("120px");
	lblFrom.setWidth("140px");
	lblTo.setWidth("140px");
	lblDays.setWidth("120px");
	lblDecision.setWidth("120px");
	
	lblName.setStyleName("blue");
	lblType.setStyleName("blue");
	lblFrom.setStyleName("blue");
	lblTo.setStyleName("blue");
	lblDays.setStyleName("blue");
	lblDecision.setStyleName("blue");
	
	MaterialColumn colLblName = new MaterialColumn();
	colLblName.add(lblName);
	add(colLblName);
	MaterialColumn colLblType = new MaterialColumn();
	colLblType.add(lblType);
	add(colLblType);
	MaterialColumn colLblFrom = new MaterialColumn();
	colLblFrom.add(lblFrom);
	add(colLblFrom);
	MaterialColumn colLblTo = new MaterialColumn();
	colLblTo.add(lblTo);
    add(colLblTo);
	MaterialColumn colLblDays = new MaterialColumn();
    colLblDays.add(lblDays);
	add(colLblDays);
	MaterialColumn colLblDecision = new MaterialColumn();
    colLblDecision.add(lblDecision);
	add(colLblDecision);
	}
	
	

}
