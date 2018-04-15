package com.leavemanagement.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialColumn;

public class MaterialTab extends Composite {

	private static MaterialTabUiBinder uiBinder = GWT.create(MaterialTabUiBinder.class);

	interface MaterialTabUiBinder extends UiBinder<Widget, MaterialTab> {
	}

	public MaterialTab() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@UiField
	MaterialColumn tab1;
	
	@UiField
	MaterialColumn tab2;

	@UiField
	MaterialColumn tab3;

	public MaterialColumn getTab1() {
		return tab1;
	}

	public MaterialColumn getTab2() {
		return tab2;
	}

	public MaterialColumn getTab3() {
		return tab3;
	}



}
