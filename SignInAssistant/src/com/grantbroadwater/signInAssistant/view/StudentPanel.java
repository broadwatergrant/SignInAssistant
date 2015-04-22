package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentListener;

public class StudentPanel extends GPanel {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400, HEIGHT = 400;

	private JPasswordField tfPin;
	private JLabel lblConfirm, lblDisplay;
	private JPanel headPanel, centerPanel, footPanel;

	public StudentPanel() {
		super(new BorderLayout());
		setBackground(Color.WHITE);

		JPanel mainPanel = new JPanel(new BorderLayout());

		headPanel = createHeadPanel();
		centerPanel = createCenterPanel();
		footPanel = createFootPanel();

		mainPanel.add(headPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(footPanel, BorderLayout.SOUTH);

		JPanel footer = new JPanel();
		footer.setBackground(Color.WHITE);
		JLabel lblCredits = new JLabel("Grant Broadwater");
		lblCredits.setForeground(Color.LIGHT_GRAY);
		footer.add(lblCredits);

		add(mainPanel, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);

		this.setConfirmVisibility(false);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private JPanel createHeadPanel() {
		JPanel housingPanel = new JPanel(new BorderLayout());
		housingPanel.setBackground(Color.WHITE);

		JPanel top = new JPanel();
		top.setBackground(Color.WHITE);
		top.add(new JLabel(" "));
		JPanel bottom = new JPanel();
		bottom.setBackground(Color.WHITE);
		bottom.add(new JLabel(""));

		housingPanel.add(top, BorderLayout.NORTH);
		JPanel lblPnl = new JPanel();
		lblPnl.setBackground(Color.WHITE);
		JLabel lbl = new JLabel("Sign In Assistant");
		Font f = new Font(getFont().getFontName(), getFont().getStyle(), 24);
		lbl.setFont(f);
		lblPnl.add(lbl);
		housingPanel.add(bottom, BorderLayout.CENTER);
		housingPanel.add(lblPnl, BorderLayout.SOUTH);

		return housingPanel;
	}

	private JPanel createCenterPanel() {
		JPanel housingPanel = new JPanel();
		housingPanel.setLayout(new BoxLayout(housingPanel, BoxLayout.Y_AXIS));
		housingPanel.setBackground(Color.WHITE);

		JPanel lblPnl = new JPanel();
		lblPnl.setBackground(Color.WHITE);
		lblDisplay = new JLabel("Pin:");
		// lblPnl.add(lblDisplay);

		JPanel tfPnl = new JPanel();
		tfPnl.setBackground(Color.WHITE);
		tfPin = new JPasswordField(10);
		lblDisplay.setLabelFor(tfPin);
		tfPnl.add(lblDisplay);
		tfPnl.add(tfPin);

		housingPanel.add(lblPnl);
		housingPanel.add(tfPnl);

		return housingPanel;
	}

	private JPanel createFootPanel() {
		JPanel housingPanel = new JPanel(new BorderLayout());
		housingPanel.setBackground(Color.WHITE);

		JPanel lblPnl = new JPanel();
		lblPnl.setBackground(Color.WHITE);
		lblConfirm = new JLabel("Sign In/Out as: ");
		lblConfirm.setForeground(new Color(0, 153, 51));
		lblConfirm.setFont(new Font(this.getFont().getFontName(), this
				.getFont().getStyle(), 18));
		lblPnl.add(lblConfirm);
		housingPanel.add(lblPnl, BorderLayout.NORTH);
		housingPanel.add(new JLabel(""), BorderLayout.CENTER);
		JPanel bottom = new JPanel();
		bottom.setBackground(Color.WHITE);
		bottom.add(new JLabel(" "));
		housingPanel.add(bottom, BorderLayout.SOUTH);

		return housingPanel;
	}

	public String getPin() {
		return new String(tfPin.getPassword());
	}

	public void clearPinText() {
		tfPin.setText("");
	}

	public void addStudentPanelConfirmListener(ActionListener listener) {
		tfPin.addActionListener(listener);
	}

	public void addStudentPanelDocumentListener(DocumentListener listener) {
		tfPin.getDocument().addDocumentListener(listener);
	}

	public void setConfirmText(String text) {
		lblConfirm.setText(text);
	}

	public void setConfirmVisibility(boolean value) {
		if (!value) {
			this.setConfirmText(" ");
		}
	}

	public void notifyPinIsIncorrect() {
		lblDisplay.setText("Incorrect Pin");
		lblDisplay.setForeground(Color.RED);
	}

	public void removeIncorrectPinNotification() {
		lblDisplay.setText("Pin: ");
		lblDisplay.setForeground(Color.BLACK);
	}

	public void refocus() {
		this.requestFocus();
		tfPin.requestFocus();
	}

}
