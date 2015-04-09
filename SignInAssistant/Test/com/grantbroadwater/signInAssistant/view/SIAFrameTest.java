package com.grantbroadwater.signInAssistant.view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		frame.setSize(AdministratorSignInPanel.WIDTH, AdministratorSignInPanel.HEIGHT);
		frame.setVisible(true);
		
		String[] options = {"Option 0", "Option 1"};
		int result = JOptionPane.showOptionDialog(frame, "test", "Test", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		System.out.println(result);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		
		frame.showPanel(1);
		frame.setSize(400, 400);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		
		
	}

}
