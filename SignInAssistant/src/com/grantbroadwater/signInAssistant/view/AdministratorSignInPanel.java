package com.grantbroadwater.signInAssistant.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdministratorSignInPanel extends GPanel {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600, HEIGHT = 400;

	@SuppressWarnings("unused")
	private BorderLayout borderLayout;
	private BoxLayout boxLayout;
	private JPanel leftPanel, mainPanel;
	private JTextField tfName;
	private JPasswordField tfPin;
	private JButton btnSignIn;

	public AdministratorSignInPanel() {
		super(new BorderLayout());
		borderLayout = (BorderLayout) this.getLayout();
		setBackground(Color.WHITE);

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
		leftPanel.setBackground(Color.WHITE);
		Font f = new Font(leftPanel.getFont().getFontName(), leftPanel
				.getFont().getStyle(), 28);
		leftPanel.setFont(f);

		JPanel fillT = new JPanel();
		fillT.setBackground(Color.WHITE);
		fillT.setMaximumSize(new Dimension(WIDTH / 2 - 10, HEIGHT / 3 - 20));
		fillT.setMinimumSize(new Dimension(WIDTH / 2 - 10, HEIGHT / 3 - 20));
		leftPanel.add(fillT);

		JLabel sign = new JLabel("Sign ");
		sign.setPreferredSize(new Dimension(WIDTH / 2 - 10, 30));
		sign.setAlignmentX(Component.CENTER_ALIGNMENT);
		sign.setFont(f);
		leftPanel.add(sign);

		JLabel in = new JLabel("In");
		in.setPreferredSize(new Dimension(WIDTH / 2 - 10, 30));
		in.setAlignmentX(Component.CENTER_ALIGNMENT);
		in.setFont(f);
		leftPanel.add(in);
		leftPanel.add(in);

		JLabel assistant = new JLabel("Assistant");
		assistant.setPreferredSize(new Dimension(WIDTH / 2 - 10, 30));
		assistant.setAlignmentX(Component.CENTER_ALIGNMENT);
		assistant.setFont(f);
		leftPanel.add(assistant);
		leftPanel.add(assistant);

		JPanel fillB = new JPanel();
		fillB.setBackground(Color.WHITE);
		fillB.setMaximumSize(new Dimension(WIDTH / 2 - 10, HEIGHT / 3));
		fillB.setMinimumSize(new Dimension(WIDTH / 2 - 10, HEIGHT / 3));
		leftPanel.add(fillB);

		mainPanel = new JPanel();
		boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxLayout);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));

		JPanel fillTop = new JPanel();
		fillTop.setBackground(Color.WHITE);
		fillTop.setMinimumSize(new Dimension(WIDTH / 2 - 10, HEIGHT / 4));
		mainPanel.add(fillTop);

		tfName = new JTextField();
		tfName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfPin.requestFocus();
			}
		});
		mainPanel.add(makePanel(new JLabel("Name: "), tfName));

		tfPin = new JPasswordField();
		mainPanel.add(makePanel(new JLabel("Pin: "), tfPin));

		btnSignIn = new JButton("Sign In");
		mainPanel.add(makePanel(new JLabel(""), btnSignIn));
		btnSignIn.setPreferredSize(new Dimension(75, 25));

		JPanel fillBottom = new JPanel();
		fillBottom.setBackground(Color.WHITE);
		fillBottom.setMinimumSize(new Dimension(WIDTH / 2 - 10, HEIGHT / 4));
		mainPanel.add(fillBottom);

		this.add(leftPanel, BorderLayout.WEST);
		this.add(mainPanel, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private JPanel makePanel(JLabel lbl, Component tf) {
		JPanel result = new JPanel();
		result.setBackground(Color.WHITE);
		result.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
		result.setAlignmentX(Component.CENTER_ALIGNMENT);

		lbl.setHorizontalAlignment(JLabel.LEADING);
		lbl.setPreferredSize(new Dimension(WIDTH / 2 - 20, 20));
		lbl.setLabelFor(tf);
		result.add(lbl);
		tf.setPreferredSize(new Dimension(WIDTH / 2 - 10, 20));
		result.add(tf);

		return result;
	}

	public void addSignInActionListener(java.awt.event.ActionListener listener) {
		tfPin.addActionListener(listener);
		btnSignIn.addActionListener(listener);
	}

	public void clearTextFields() {
		tfName.setText("");
		tfPin.setText("");
	}

	public String getName() {
		return tfName.getText().trim();
	}

	public String getPin() {
		return new String(tfPin.getPassword());
	}

	public void displayMessageBox(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

}
