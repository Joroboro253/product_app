package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;
import com.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class AddProductWindow extends JFrame {

    private final ProductService productService;

    private JTextField codeField;
    private JTextField brandField;
    private JTextField diagonalField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField displayField;
    private JTextField priceField;
    private JTextField quantityField;
    private JLabel statusLabel;

    public AddProductWindow(ProductService productService) {
        this.productService = productService;
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Add product");
        setSize(500, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Add a new product", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel formPanel = buildFormPanel();

        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton saveButton = ButtonView.create("Save");
        saveButton.addActionListener(e -> handleSave());

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actionsPanel.add(saveButton);
        actionsPanel.add(closeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6, 6, 6, 6);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        codeField = new JTextField();
        brandField = new JTextField();
        diagonalField = new JTextField();
        widthField = new JTextField();
        heightField = new JTextField();
        displayField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        addField(panel, constraints, 0, "Code", codeField);
        addField(panel, constraints, 1, "Brand", brandField);
        addField(panel, constraints, 2, "Diagonal (inches)", diagonalField);
        addField(panel, constraints, 3, "Width (cm)", widthField);
        addField(panel, constraints, 4, "Height (cm)", heightField);
        addField(panel, constraints, 5, "Display technology", displayField);
        addField(panel, constraints, 6, "Price", priceField);
        addField(panel, constraints, 7, "Quantity", quantityField);

        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints constraints, int row, String labelText, JComponent component) {
        constraints.gridy = row;

        constraints.gridx = 0;
        constraints.weightx = 0;
        panel.add(new JLabel(labelText + ":"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        panel.add(component, constraints);
    }

    private void handleSave() {
        String code = codeField.getText().trim();
        String brand = brandField.getText().trim();
        String diagonalValue = diagonalField.getText().trim();
        String widthValue = widthField.getText().trim();
        String heightValue = heightField.getText().trim();
        String displayTechnology = displayField.getText().trim();
        String priceValue = priceField.getText().trim();
        String quantityValue = quantityField.getText().trim();

        if (code.isEmpty() || brand.isEmpty() || diagonalValue.isEmpty() || widthValue.isEmpty()
                || heightValue.isEmpty() || displayTechnology.isEmpty() || priceValue.isEmpty()
                || quantityValue.isEmpty()) {
            showStatus("All fields are required.", true);
            return;
        }

        if (productService.findProductByCode(code) != null) {
            showStatus("Product with code " + code + " already exists.", true);
            return;
        }

        try {
            int diagonal = parsePositiveInt(diagonalValue, "Diagonal size");
            double width = parsePositiveDouble(widthValue, "Width");
            double height = parsePositiveDouble(heightValue, "Height");
            BigDecimal price = parsePrice(priceValue);
            int quantity = parseNonNegativeInt(quantityValue, "Quantity");

            Product product = Product.builder(code, brand, price)
                    .withDiagonalSize(diagonal)
                    .withWidth(width)
                    .withHeight(height)
                    .withDisplayTechnology(displayTechnology)
                    .withQuantity(quantity)
                    .build();

            if (productService.addProduct(product)) {
                showStatus("Product was added successfully.", false);
                resetForm();
            } else {
                showStatus("Failed to add product. Please try again.", true);
            }
        } catch (IllegalArgumentException ex) {
            showStatus(ex.getMessage(), true);
        }
    }

    private void resetForm() {
        codeField.setText("");
        brandField.setText("");
        diagonalField.setText("");
        widthField.setText("");
        heightField.setText("");
        displayField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    private int parsePositiveInt(String value, String fieldName) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed <= 0) {
                throw new IllegalArgumentException(fieldName + " must be greater than 0.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    private int parseNonNegativeInt(String value, String fieldName) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed < 0) {
                throw new IllegalArgumentException(fieldName + " must be zero or positive.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    private double parsePositiveDouble(String value, String fieldName) {
        try {
            double parsed = Double.parseDouble(value);
            if (parsed <= 0) {
                throw new IllegalArgumentException(fieldName + " must be greater than 0.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

    private BigDecimal parsePrice(String value) {
        try {
            BigDecimal price = new BigDecimal(value);
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0.");
            }
            return price;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Price must be a valid decimal number.");
        }
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
import java.awt.*;
import java.math.BigDecimal;

public class AddProductWindow extends JFrame {

    private final ProductService productService;

    private JTextField codeField;
    private JTextField brandField;
    private JTextField diagonalField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField displayField;
    private JTextField priceField;
    private JTextField quantityField;
    private JLabel statusLabel;

    public AddProductWindow(ProductService productService) {
        this.productService = productService;
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Add product");
        setSize(500, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Add a new product", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel formPanel = buildFormPanel();

        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton saveButton = ButtonView.create("Save");
        saveButton.addActionListener(e -> handleSave());

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actionsPanel.add(saveButton);
        actionsPanel.add(closeButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6, 6, 6, 6);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        codeField = new JTextField();
        brandField = new JTextField();
        diagonalField = new JTextField();
        widthField = new JTextField();
        heightField = new JTextField();
        displayField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        addField(panel, constraints, 0, "Code", codeField);
        addField(panel, constraints, 1, "Brand", brandField);
        addField(panel, constraints, 2, "Diagonal (inches)", diagonalField);
        addField(panel, constraints, 3, "Width (cm)", widthField);
        addField(panel, constraints, 4, "Height (cm)", heightField);
        addField(panel, constraints, 5, "Display technology", displayField);
        addField(panel, constraints, 6, "Price", priceField);
        addField(panel, constraints, 7, "Quantity", quantityField);

        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints constraints, int row, String labelText, JComponent component) {
        constraints.gridy = row;

        constraints.gridx = 0;
        constraints.weightx = 0;
        panel.add(new JLabel(labelText + ":"), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        panel.add(component, constraints);
    }

    private void handleSave() {
        String code = codeField.getText().trim();
        String brand = brandField.getText().trim();
        String diagonalValue = diagonalField.getText().trim();
        String widthValue = widthField.getText().trim();
        String heightValue = heightField.getText().trim();
        String displayTechnology = displayField.getText().trim();
        String priceValue = priceField.getText().trim();
        String quantityValue = quantityField.getText().trim();

        if (code.isEmpty() || brand.isEmpty() || diagonalValue.isEmpty() || widthValue.isEmpty()
                || heightValue.isEmpty() || displayTechnology.isEmpty() || priceValue.isEmpty()
                || quantityValue.isEmpty()) {
            showStatus("All fields are required.", true);
            return;
        }

        if (productService.findProductByCode(code) != null) {
            showStatus("Product with code " + code + " already exists.", true);
            return;
        }

        try {
            int diagonal = parsePositiveInt(diagonalValue, "Diagonal size");
            double width = parsePositiveDouble(widthValue, "Width");
            double height = parsePositiveDouble(heightValue, "Height");
            BigDecimal price = parsePrice(priceValue);
            int quantity = parseNonNegativeInt(quantityValue, "Quantity");

            Product product = Product.builder(code, brand, price)
                    .withDiagonalSize(diagonal)
                    .withWidth(width)
                    .withHeight(height)
                    .withDisplayTechnology(displayTechnology)
                    .withQuantity(quantity)
                    .build();

            if (productService.addProduct(product)) {
                showStatus("Product was added successfully.", false);
                resetForm();
            } else {
                showStatus("Failed to add product. Please try again.", true);
            }
        } catch (IllegalArgumentException ex) {
            showStatus(ex.getMessage(), true);
        }
    }

    private void resetForm() {
        codeField.setText("");
        brandField.setText("");
        diagonalField.setText("");
        widthField.setText("");
        heightField.setText("");
        displayField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    private int parsePositiveInt(String value, String fieldName) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed <= 0) {
                throw new IllegalArgumentException(fieldName + " must be greater than 0.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    private int parseNonNegativeInt(String value, String fieldName) {
        try {
            int parsed = Integer.parseInt(value);
            if (parsed < 0) {
                throw new IllegalArgumentException(fieldName + " must be zero or positive.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    private double parsePositiveDouble(String value, String fieldName) {
        try {
            double parsed = Double.parseDouble(value);
            if (parsed <= 0) {
                throw new IllegalArgumentException(fieldName + " must be greater than 0.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

    private BigDecimal parsePrice(String value) {
        try {
            BigDecimal price = new BigDecimal(value);
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0.");
            }
            return price;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Price must be a valid decimal number.");
        }
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? new Color(178, 34, 34) : new Color(34, 139, 34));
    }
}
