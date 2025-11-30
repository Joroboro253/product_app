package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchWindow extends JFrame {

    private static final String[] COLUMN_NAMES = {
            "Code", "Brand", "Diagonal (in)", "Width (cm)", "Height (cm)",
            "Display", "Price", "Quantity"
    };

    private final List<Product> products;

    private JTextField codeField;
    private JTextField brandField;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    public SearchWindow(List<Product> products) {
        this.products = products != null ? new ArrayList<>(products) : Collections.emptyList();
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Search products");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Search products", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(12));
        headerPanel.add(buildSearchPanel());

        JTable resultsTable = buildResultsTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        statusLabel = new JLabel("Enter search criteria to begin.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton resetButton = ButtonView.create("Reset");
        resetButton.addActionListener(e -> resetForm());

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        actionsPanel.add(resetButton);
        actionsPanel.add(closeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildSearchPanel() {
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search options"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6, 6, 6, 6);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        codeField = new JTextField(20);
        brandField = new JTextField(20);

        JButton searchByCodeButton = ButtonView.create("Search by Code");
        searchByCodeButton.addActionListener(e -> searchByCode());

        JButton searchByBrandButton = ButtonView.create("Search by Brand");
        searchByBrandButton.addActionListener(e -> searchByBrand());

        int row = 0;

        constraints.gridy = row;
        constraints.gridx = 0;
        constraints.weightx = 0;
        searchPanel.add(new JLabel("Code:"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        searchPanel.add(codeField, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0;
        searchPanel.add(searchByCodeButton, constraints);

        row++;
        constraints.gridy = row;
        constraints.gridx = 0;
        constraints.weightx = 0;
        searchPanel.add(new JLabel("Brand:"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        searchPanel.add(brandField, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0;
        searchPanel.add(searchByBrandButton, constraints);

        return searchPanel;
    }

    private JTable buildResultsTable() {
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        return table;
    }

    private void searchByCode() {
        String code = codeField.getText().trim();
        if (code.isEmpty()) {
            showStatus("Please enter a product code.", true);
            return;
        }

        Product match = products.stream()
                .filter(product -> product.getCode() != null && product.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (match == null) {
            clearResults();
            showStatus("No products found with code " + code + ".", true);
            return;
        }

        updateTable(Collections.singletonList(match));
        showStatus("Found 1 product for code " + code + ".", false);
    }

    private void searchByBrand() {
        String brand = brandField.getText().trim();
        if (brand.isEmpty()) {
            showStatus("Please enter a brand.", true);
            return;
        }

        List<Product> matches = products.stream()
                .filter(product -> product.getBrand() != null && product.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            clearResults();
            showStatus("No products found for brand " + brand + ".", true);
            return;
        }

        updateTable(matches);
        showStatus("Found " + matches.size() + " product(s) for brand " + brand + ".", false);
    }

    private void updateTable(List<Product> results) {
        clearResults();
        for (Product product : results) {
            tableModel.addRow(new Object[]{
                    product.getCode(),
                    product.getBrand(),
                    product.getDiagonal_size(),
                    product.getWidth(),
                    product.getHeight(),
                    product.getDisplay_technology(),
                    product.getPrice(),
                    product.getQuantity()
            });
        }
    }

    private void clearResults() {
        tableModel.setRowCount(0);
    }

    private void resetForm() {
        codeField.setText("");
        brandField.setText("");
        clearResults();
        showStatus("Enter search criteria to begin.", false);
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? new Color(178, 34, 34) : new Color(34, 139, 34));
    }
}
package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchWindow extends JFrame {

    private static final String[] COLUMN_NAMES = {
            "Code", "Brand", "Diagonal (in)", "Width (cm)", "Height (cm)",
            "Display", "Price", "Quantity"
    };

    private final List<Product> products;

    private JTextField codeField;
    private JTextField brandField;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    public SearchWindow(List<Product> products) {
        this.products = products != null ? new ArrayList<>(products) : Collections.emptyList();
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Search products");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Search products", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(12));
        headerPanel.add(buildSearchPanel());

        JTable resultsTable = buildResultsTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        statusLabel = new JLabel("Enter search criteria to begin.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton resetButton = ButtonView.create("Reset");
        resetButton.addActionListener(e -> resetForm());

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        actionsPanel.add(resetButton);
        actionsPanel.add(closeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildSearchPanel() {
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search options"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6, 6, 6, 6);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        codeField = new JTextField(20);
        brandField = new JTextField(20);

        JButton searchByCodeButton = ButtonView.create("Search by Code");
        searchByCodeButton.addActionListener(e -> searchByCode());

        JButton searchByBrandButton = ButtonView.create("Search by Brand");
        searchByBrandButton.addActionListener(e -> searchByBrand());

        int row = 0;

        constraints.gridy = row;
        constraints.gridx = 0;
        constraints.weightx = 0;
        searchPanel.add(new JLabel("Code:"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        searchPanel.add(codeField, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0;
        searchPanel.add(searchByCodeButton, constraints);

        row++;
        constraints.gridy = row;
        constraints.gridx = 0;
        constraints.weightx = 0;
        searchPanel.add(new JLabel("Brand:"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        searchPanel.add(brandField, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0;
        searchPanel.add(searchByBrandButton, constraints);

        return searchPanel;
    }

    private JTable buildResultsTable() {
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        return table;
    }

    private void searchByCode() {
        String code = codeField.getText().trim();
        if (code.isEmpty()) {
            showStatus("Please enter a product code.", true);
            return;
        }

        Product match = products.stream()
                .filter(product -> product.getCode() != null && product.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (match == null) {
            clearResults();
            showStatus("No products found with code " + code + ".", true);
            return;
        }

        updateTable(Collections.singletonList(match));
        showStatus("Found 1 product for code " + code + ".", false);
    }

    private void searchByBrand() {
        String brand = brandField.getText().trim();
        if (brand.isEmpty()) {
            showStatus("Please enter a brand.", true);
            return;
        }

        List<Product> matches = products.stream()
                .filter(product -> product.getBrand() != null && product.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            clearResults();
            showStatus("No products found for brand " + brand + ".", true);
            return;
        }

        updateTable(matches);
        showStatus("Found " + matches.size() + " product(s) for brand " + brand + ".", false);
    }

    private void updateTable(List<Product> results) {
        clearResults();
        for (Product product : results) {
            tableModel.addRow(new Object[]{
                    product.getCode(),
                    product.getBrand(),
                    product.getDiagonal_size(),
                    product.getWidth(),
                    product.getHeight(),
                    product.getDisplay_technology(),
                    product.getPrice(),
                    product.getQuantity()
            });
        }
    }

    private void clearResults() {
        tableModel.setRowCount(0);
    }

    private void resetForm() {
        codeField.setText("");
        brandField.setText("");
        clearResults();
        showStatus("Enter search criteria to begin.", false);
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? new Color(178, 34, 34) : new Color(34, 139, 34));
    }
}
