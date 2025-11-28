package com;

import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {
	private static final String CSV_FILE_PATH = "products.csv";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Menu menu = new ConsoleMenu(scanner);
		MenuController controller = new MenuController(menu, CSV_FILE_PATH, scanner);
	
		controller.run();
	}
}
