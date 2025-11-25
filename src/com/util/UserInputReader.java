package com.util;

import java.math.BigDecimal;
import java.util.Scanner;

import com.Menu;

public class UserInputReader {
	private final Menu menu;
	private final Scanner scanner;
	
	public UserInputReader(Menu menu, Scanner scanner) {
		this.menu = menu;
		this.scanner = scanner;
	}
	
	public String readLine(String prompt) {
		menu.showMessage(prompt);
		return scanner.nextLine().trim();
	}
	
	public String readRequiredString(String prompt) {
		while (true) {
			String value = readLine(prompt);
			if (!value.isBlank()) {
				return value;
			}
			menu.showError("Value cannot be empty.");
		}
	}
	
	public int readInt(String prompt) {
		while (true) {
			String value = readLine(prompt);
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				menu.showError("Please enter a valid integer.");
			}
		}
	}
	
	public double readDouble(String prompt) {
		while (true) {
			String value = readLine(prompt);
			try {
				return Double.parseDouble(value);
			} catch (NumberFormatException e) {
				menu.showError("Please enter a valid number.");
			}
		}
	}
	
	public BigDecimal readBigDecimal(String prompt) {
		while (true) {
			String value = readLine(prompt);
			try {
				return new BigDecimal(value);
			} catch (NumberFormatException e) {
				menu.showError("Please enter a valid decimal number.");
			}
		}
	}
}

