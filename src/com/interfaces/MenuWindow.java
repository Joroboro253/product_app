package com.interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.model.Product;
import com.interfaces.utils.ButtonView;
import com.service.ProductService;

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

    private final ProductService productService;
	
	public MenuWindow(ProductService productService) {
        this.productService = productService;
		buildMenu();
	}

    public void show() {
        if (window == null) {
            buildMenu();
        }
        window.setVisible(true);
    }

	private void buildMenu() {
		window = new JFrame();
		window.setTitle("Product`s application menu");
		window.setSize(800, 500);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		panel = new JPanel(new GridBagLayout());
        window.setContentPane(panel);
		
		loadButton = ButtonView.create("Load");
        loadButton.addActionListener(e -> loadAction());
        addMenuButton(loadButton, 0);
        
		printButton = ButtonView.create("Print");
        printButton.addActionListener(e -> printAction());
        addMenuButton(printButton, 1);
        
		searchButton = ButtonView.create("Search");
        searchButton.addActionListener(e -> searchAction());
        addMenuButton(searchButton, 2);

        addButton = ButtonView.create("Add Product");
        addButton.addActionListener(e -> addAction());
        addMenuButton(addButton, 3);

        sortButton = ButtonView.create("Sort Products");
        sortButton.addActionListener(e -> sortAction());
        addMenuButton(sortButton, 4);

        buyButton = ButtonView.create("Buy Products");
        buyButton.addActionListener(e -> buyAction());
        addMenuButton(buyButton, 5);
        
        exitButton = ButtonView.create("Exit");
        exitButton.addActionListener(e -> exitAction());
        addMenuButton(exitButton, 6);
	}

    private void addMenuButton(JButton button, int row) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(8, 32, 8, 32);
        panel.add(button, constraints);
    }

    private void loadAction() {
        loadButton.setEnabled(false);
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                return productService.loadProductsFromCSV();
            }

            @Override
            protected void done() {
                loadButton.setEnabled(true);
                try {
                    if (Boolean.TRUE.equals(get())) {
                        new LoadSuccessfulWindow();
                    } else {
                        showErrorDialog("Products were not loaded. Please try again.");
                    }
                } catch (Exception e) {
                    Throwable cause = e.getCause() != null ? e.getCause() : e;
                    showErrorDialog("Failed to load products: " + cause.getMessage());
                }
            }
        }.execute();
    }

    private void printAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new PrintAllProductsWindow(products));
    }

    private void exitAction() {
        int option = JOptionPane.showConfirmDialog(
                window,
                "Are you sure you want to exit?",
                "Exit application",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            window.dispose();
            System.exit(0);
        }
    }

    private void buyAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new ByingWindow(productService));
    }

    private void sortAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new SortWindow(productService));
    }

    private void addAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new AddProductWindow(productService));
    }

    private void searchAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new SearchWindow(new ArrayList<>(products)));
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(window, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
package com.interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.model.Product;
import com.interfaces.utils.ButtonView;
import com.service.ProductService;

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

    private final ProductService productService;
	
	public MenuWindow(ProductService productService) {
        this.productService = productService;
		buildMenu();
	}

    public void show() {
        if (window == null) {
            buildMenu();
        }
        window.setVisible(true);
    }

	private void buildMenu() {
		window = new JFrame();
		window.setTitle("Product`s application menu");
		window.setSize(800, 500);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		panel = new JPanel(new GridBagLayout());
        window.setContentPane(panel);
		
		loadButton = ButtonView.create("Load");
        loadButton.addActionListener(e -> loadAction());
        addMenuButton(loadButton, 0);
        
		printButton = ButtonView.create("Print");
        printButton.addActionListener(e -> printAction());
        addMenuButton(printButton, 1);
        
		searchButton = ButtonView.create("Search");
        searchButton.addActionListener(e -> searchAction());
        addMenuButton(searchButton, 2);

        addButton = ButtonView.create("Add Product");
        addButton.addActionListener(e -> addAction());
        addMenuButton(addButton, 3);

        sortButton = ButtonView.create("Sort Products");
        sortButton.addActionListener(e -> sortAction());
        addMenuButton(sortButton, 4);

        buyButton = ButtonView.create("Buy Products");
        buyButton.addActionListener(e -> buyAction());
        addMenuButton(buyButton, 5);
        
        exitButton = ButtonView.create("Exit");
        exitButton.addActionListener(e -> exitAction());
        addMenuButton(exitButton, 6);
	}

    private void addMenuButton(JButton button, int row) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(8, 32, 8, 32);
        panel.add(button, constraints);
    }

    private void loadAction() {
        loadButton.setEnabled(false);
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                return productService.loadProductsFromCSV();
            }

            @Override
            protected void done() {
                loadButton.setEnabled(true);
                try {
                    if (Boolean.TRUE.equals(get())) {
                        new LoadSuccessfulWindow();
                    } else {
                        showErrorDialog("Products were not loaded. Please try again.");
                    }
                } catch (Exception e) {
                    Throwable cause = e.getCause() != null ? e.getCause() : e;
                    showErrorDialog("Failed to load products: " + cause.getMessage());
                }
            }
        }.execute();
    }

    private void printAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new PrintAllProductsWindow(products));
    }

    private void exitAction() {
        int option = JOptionPane.showConfirmDialog(
                window,
                "Are you sure you want to exit?",
                "Exit application",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            window.dispose();
            System.exit(0);
        }
    }

    private void buyAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new ByingWindow(productService));
    }

    private void sortAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new SortWindow(productService));
    }

    private void addAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new AddProductWindow(productService));
    }

    private void searchAction() {
        List<Product> products = productService.getLoadedProducts();
        if (products.isEmpty()) {
            showErrorDialog("No products loaded. Please load products first.");
            return;
        }
        SwingUtilities.invokeLater(() -> new SearchWindow(new ArrayList<>(products)));
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(window, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    


    

}
