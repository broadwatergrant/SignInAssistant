package com.grantbroadwater.signInAssistant.view;

import java.awt.Dimension;
import java.awt.LayoutManager;

public class AdministratorSignInPanel extends GPanel {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600, HEIGHT = 400;

	public AdministratorSignInPanel() {
		super();
	}

	public AdministratorSignInPanel(LayoutManager layout) {
		super(layout);
		
	}
	
	public Dimension getPreferedSize(){
		return new Dimension(WIDTH, HEIGHT);
	}

}
