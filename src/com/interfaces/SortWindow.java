package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;
import com.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SortWindow extends JFrame {

    private final ProductService productService;

    private JRadioButton brandRadio;
    private JRadioButton diagonalRadio;
    private JRadioButton priceRadio;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    public SortWindow(ProductService productService) {
        this.productService = productService;
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Sort products");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Sort products", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(buildOptionsPanel(), BorderLayout.CENTER);

        JTable table = buildResultsTable();
        JScrollPane scrollPane = new JScrollPane(table);

        statusLabel = new JLabel("Select a sorting option and click Sort.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton sortButton = ButtonView.create("Sort");
        sortButton.addActionListener(e -> handleSort());

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        actionsPanel.add(sortButton);
        actionsPanel.add(closeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Sorting options"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        brandRadio = new JRadioButton("Brand");
        diagonalRadio = new JRadioButton("Diagonal size");
        priceRadio = new JRadioButton("Price");

        ButtonGroup group = new ButtonGroup();
        group.add(brandRadio);
        group.add(diagonalRadio);
        group.add(priceRadio);

        panel.add(brandRadio);
        panel.add(diagonalRadio);
        panel.add(priceRadio);

        return panel;
    }

    private JTable buildResultsTable() {
        String[] columnNames = {
                "Code", "Brand", "Diagonal (in)", "Width (cm)", "Height (cm)",
                "Display", "Price", "Quantity"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
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

    private void handleSort() {
        ProductService.SortOption option = getSelectedOption();
        if (option == null) {
            showStatus("Please select a sorting option.", true);
            return;
        }

        List<Product> sorted = productService.sortProducts(option);
        if (sorted.isEmpty()) {
            showStatus("No products available for sorting.", true);
            clearResults();
            return;
        }

        updateTable(sorted);
        showStatus("Products sorted by " + option.name().toLowerCase() + ".", false);
    }

    private ProductService.SortOption getSelectedOption() {
        if (brandRadio.isSelected()) {
            return ProductService.SortOption.BRAND;
        }
        if (diagonalRadio.isSelected()) {
            return ProductService.SortOption.DIAGONAL;
        }
        if (priceRadio.isSelected()) {
            return ProductService.SortOption.PRICE;
        }
        return null;
    }

    private void updateTable(List<Product> products) {
        clearResults();
        for (Product product : products) {
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

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? new Color(178, 34, 34) : new Color(34, 139, 34));
    }
}
package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;
import com.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SortWindow extends JFrame {

    private final ProductService productService;

    private JRadioButton brandRadio;
    private JRadioButton diagonalRadio;
    private JRadioButton priceRadio;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    public SortWindow(ProductService productService) {
        this.productService = productService;
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Sort products");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Sort products", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(buildOptionsPanel(), BorderLayout.CENTER);

        JTable table = buildResultsTable();
        JScrollPane scrollPane = new JScrollPane(table);

        statusLabel = new JLabel("Select a sorting option and click Sort.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton sortButton = ButtonView.create("Sort");
        sortButton.addActionListener(e -> handleSort());

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        actionsPanel.add(sortButton);
        actionsPanel.add(closeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Sorting options"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        brandRadio = new JRadioButton("Brand");
        diagonalRadio = new JRadioButton("Diagonal size");
        priceRadio = new JRadioButton("Price");

        ButtonGroup group = new ButtonGroup();
        group.add(brandRadio);
        group.add(diagonalRadio);
        group.add(priceRadio);

        panel.add(brandRadio);
        panel.add(diagonalRadio);
        panel.add(priceRadio);

        return panel;
    }

    private JTable buildResultsTable() {
        String[] columnNames = {
                "Code", "Brand", "Diagonal (in)", "Width (cm)", "Height (cm)",
                "Display", "Price", "Quantity"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
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

    private void handleSort() {
        ProductService.SortOption option = getSelectedOption();
        if (option == null) {
            showStatus("Please select a sorting option.", true);
            return;
        }

        List<Product> sorted = productService.sortProducts(option);
        if (sorted.isEmpty()) {
            showStatus("No products available for sorting.", true);
            clearResults();
            return;
        }

        updateTable(sorted);
        showStatus("Products sorted by " + option.name().toLowerCase() + ".", false);
    }

    private ProductService.SortOption getSelectedOption() {
        if (brandRadio.isSelected()) {
            return ProductService.SortOption.BRAND;
        }
        if (diagonalRadio.isSelected()) {
            return ProductService.SortOption.DIAGONAL;
        }
        if (priceRadio.isSelected()) {
            return ProductService.SortOption.PRICE;
        }
        return null;
    }

    private void updateTable(List<Product> products) {
        clearResults();
        for (Product product : products) {
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

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? new Color(178, 34, 34) : new Color(34, 139, 34));
    }
}
