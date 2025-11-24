package com;

public interface Menu {
	void displayMenu();
	int getUserChoice();
	void showMessage(String message);
	void showError(String error);
	void waitForEnter();
	void close();
}
