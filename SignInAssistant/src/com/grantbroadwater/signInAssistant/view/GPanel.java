package com.grantbroadwater.signInAssistant.view;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class GPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int instanceCount = 0;

	public GPanel() {
		super();
		instanceCount++;
	}

	public GPanel(LayoutManager layout) {
		super(layout);
		instanceCount++;
	}

	public final String getCardLayoutName(){
		return this.getClass().getName() + instanceCount;
	}
	
	public abstract Dimension getPreferredSize();

}
