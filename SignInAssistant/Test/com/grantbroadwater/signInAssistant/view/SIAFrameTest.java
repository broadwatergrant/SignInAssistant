package com.grantbroadwater.signInAssistant.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

public class SIAFrameTest {

	@Test
	public void test() {
		SIAFrame frame = new SIAFrame();
		
		JPanel pnl1 = new JPanel();
		pnl1.add(new JLabel("Panel 1"));
		JPanel pnl2 = new JPanel();
		pnl2.add(new JLabel("Panel 2"));
		
		JPanel[] panels = {pnl1, pnl2};
		String[] panelNames = {"panel1", "panel2"};
		
		frame.setPanels(panels, panelNames);
		frame.showPanel(0);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.showPanel(1);
		frame.setSize(400, 400);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
