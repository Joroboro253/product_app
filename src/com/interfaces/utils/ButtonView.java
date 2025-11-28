package com.interfaces.utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class ButtonView {
	
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 50;
	private static final String FONT_NAME = "Arial";
	private static final int FONT_STYLE = Font.BOLD;
	private static final int FONT_SIZE = 14;
	
	private JButton button;
	
	public ButtonView(String title) {
		this.button = create(title);
	}
	
	public ButtonView(String title, ActionListener action) {
		this.button = create(title);
		this.button.addActionListener(action);
	}
	
	public JButton create(String title) {
		JButton button = new JButton(title);
		button.setFocusable(false);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.setFont(new Font("Arial", Font.PLAIN, 24));
		return button;
	}
	
	

	
	
	
}
