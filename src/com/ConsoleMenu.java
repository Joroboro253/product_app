package com;

import java.util.Scanner;

public class ConsoleMenu implements Menu {
	private final Scanner scanner;
	
	public ConsoleMenu(Scanner scanner) {
		this.scanner = scanner;
	}
	
	@Override
	public void displayMenu() {
		System.out.println("\n=== Menu ====");
		System.out.println("1. Load products from CSV file");
		System.out.println("2. Print all products");
		System.out.println("3. Search by code or brand");
		System.out.println("4. Add new product");
		System.out.println("5. Apply sorting");
		System.out.println("6. Buy a TV");
		System.out.println("7. Exit");
	}

	@Override
	public int getUserChoice() {
		 System.out.println("Select an option: ");
		 try {
			 return Integer.parseInt(scanner.nextLine().trim());
		 } catch (NumberFormatException e) {
			 return -1;
		 }
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);		
	}

	@Override
	public void showError(String error) {
		System.out.println(error);
	}

	@Override
	public void waitForEnter() {
		System.out.println("Print enter to continue...");
		scanner.nextLine();
	}

	@Override
	public void close() {
		scanner.close();	
	}

}
