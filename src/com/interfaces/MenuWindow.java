package com.interfaces;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.interfaces.utils.ButtonView;

public class MenuWindow {
	
	private JFrame window;
	private JPanel panel;
	private JButton loadButton;
	private JButton printButton;
	private JButton searchButton;
	private JButton addButton;
	private JButton sortButton;
	private JButton buyButton;
	private JButton exitButton;
	
	public MenuWindow() {
		buildMenu();
	}

	private void buildMenu() {
		window = new JFrame();
		window.setTitle("Product`s application menu");
		window.setSize(800, 500);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		panel = new JPanel(new GridBagLayout());
		
		loadButton = ButtonView.create("Load", () -> loadAction());
		printButton = ButtonView.create("Print", () -> printAction());
		searchButton = ButtonView.create("Search", () -> searchAction());
		addButton = ButtonView.create("Add", () -> addAction());
		sortButton = ButtonView.create("Sort", () -> sortAction());
		buyButton = ButtonView.create("Buy a TV", () -> buyAction());
		exitButton = ButtonView.create("Exit", () -> System.exit(0));
		
		
	}
	
}
