package com.grantbroadwater.signInAssistant.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SIAKeyListener implements KeyListener {

	Controller controller;
	
	public SIAKeyListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		if((e.getKeyCode() == KeyEvent.VK_P) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
			System.out.println("Envent triggered");
			controller.reselectPin();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
