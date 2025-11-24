package com;

import java.util.List;
import java.util.Scanner;

public class MenuController {
	private List<Product> products;
	private final Menu menu;
	private final String csvFilePath;
	private final Scanner scanner;
	
	public MenuController(Menu menu, String csvFilePath, Scanner scanner) {
		this.menu = menu;
		this.csvFilePath = csvFilePath;
		this.scanner = scanner;
		this.products = null;
	}
	
	public void run() {
        menu.showMessage("=========================Products============================");

        while (true) {
        	menu.displayMenu();
        	int choice = menu.getUserChoice();
        	
        	if (choice == -1) {
        		menu.showError("Invalid input. Please enter a number.");
        		menu.waitForEnter();
        		continue;
        	}
        	
        	try {
        		if (!handleChoice(choice)) {
        			break;
        		}
        	} catch (Exception e) {
        		menu.showError("An error occured: " + e.getMessage());
        	}
        	
        	menu.waitForEnter();
        }
        
        menu.close();
	}

	private boolean handleChoice(int choice) {
		switch (choice) {
			case 1:
				loadProductsFromCSV();
				return true;
			case 2: 
				printAllProducts();
			case 3:
				handleSearchMenu();
			case 4:
				addNewProduct();
			case 5:
				applySorting();
			case 6: 
				buyTV();
			case 7:
				menu.showMessage("Exiting...");
				return false;
			default:
				menu.showError("Invalid option. Please try again.");
				return true;
		}
	}

	private void loadProductsFromCSV() {
		menu.showMessage("\nLoading products from " + csvFilePath + "...");
		List<Product> products = ProductParser.parseProduct(csvFilePath);
		
	}
	
	private void buyTV() {
		// TODO Auto-generated method stub
		
	}

	private void applySorting() {
		// TODO Auto-generated method stub
		
	}

	private void addNewProduct() {
		// TODO Auto-generated method stub
		
	}

	private void printAllProducts() {
		// TODO Auto-generated method stub
		
	}

	
	
	private void handleSearchMenu() {
		System.out.println("Do You want search by: ");
		System.out.println("1 - Code");
		System.out.println("2 - Brand");
		System.out.println("Type your choose: ");
		
		int searchOption = scanner.nextInt();
		scanner.nextLine();
		
		switch (searchOption) {
			case 1:
				System.out.println("Enter product code: ");
				String productCode = scanner.nextLine();
				searchByCode(productCode);
				break;
			case 2:
				System.out.println("Enter product brand: ");
				String productBrand = scanner.nextLine();
				searchByBrand(productBrand);
				break;
			default:
				System.out.println("Invalid Option");
		}
	}
	
	private Product searchByCode(String code) {
		if (code == null || code.isBlank()) {
			return null;
		}
		
		for (Product product : products) {
			if (product.getCode().equalsIgnoreCase(code.trim())) {
				return product;
			}
		}
		
		return null;
	}
	
	private Product searchByBrand(String brand) {
		if (brand == null || brand.isBlank()) {
			return null;
		}
		
		for (Product product : products) {
			if (product.getBrand().equalsIgnoreCase(brand.trim()))
		}
	}

	
	
}
