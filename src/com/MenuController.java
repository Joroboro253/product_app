package com;

import java.util.Scanner;

import com.service.ProductService;

public class MenuController {
	private final Menu menu;
	private final ProductService productService;
	
	public MenuController(Menu menu, String csvFilePath, Scanner scanner) {
		this.menu = menu;
		this.productService = new ProductService(menu, scanner, csvFilePath);
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
		boolean shouldContinue = true;
		
		switch (choice) {
			case 1:
				productService.loadProductsFromCSV();
				break;
			case 2:
				productService.printAllProducts();
				break;
			case 3:
				productService.handleSearchMenu();
				break;
			case 4:
				productService.addNewProduct();
				break;
			case 5:
				productService.applySorting();
				break;
			case 6:
				productService.buyTV();
				break;
			case 7:
				menu.showMessage("Exiting...");
				shouldContinue = false;
				break;
			default:
				menu.showError("Invalid option. Please try again.");
		}
		
		return shouldContinue;
	}
}

