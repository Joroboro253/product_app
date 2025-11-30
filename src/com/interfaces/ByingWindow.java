package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;
import com.service.ProductService;

import javax.swing.*;
import java.awt.*;

public class ByingWindow extends JFrame {

    private final ProductService productService;

    private JTextField codeField;
    private JLabel productInfoLabel;
    private JLabel quantityLabel;
    private JLabel statusLabel;
    private JButton buyButton;

    private Product selectedProduct;

    public ByingWindow(ProductService productService) {
        this.productService = productService;
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Buy product");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Buy a TV", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel searchPanel = buildSearchPanel();
        JPanel infoPanel = buildInfoPanel();
        JPanel bottomPanel = buildBottomPanel();

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(searchPanel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        codeField = new JTextField();
        codeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        JButton findButton = ButtonView.create("Find by Code");
        findButton.addActionListener(e -> handleSearch());

        panel.add(new JLabel("Product code:"));
        panel.add(Box.createVerticalStrut(6));
        panel.add(codeField);
        panel.add(Box.createVerticalStrut(12));
        panel.add(findButton);

        return panel;
    }

    private JPanel buildInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Product details"));

        productInfoLabel = new JLabel("No product selected");
        productInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        quantityLabel = new JLabel("");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));

        buyButton = ButtonView.create("Buy");
        buyButton.addActionListener(e -> handlePurchase());
        buyButton.setEnabled(false);

        JPanel header = new JPanel(new BorderLayout());
        header.add(productInfoLabel, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.add(quantityLabel, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(buyButton);

        panel.add(header, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(actions, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        statusLabel = new JLabel("Enter product code to begin.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        panel.add(statusLabel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.EAST);

        return panel;
    }

    private void handleSearch() {
        String code = codeField.getText().trim();
        if (code.isEmpty()) {
            showStatus("Please enter a product code.", true);
            clearSelection();
            return;
        }

        Product product = productService.findProductByCode(code);
        if (product == null) {
            showStatus("No product found with code " + code + ".", true);
            clearSelection();
            return;
        }

        selectedProduct = product;
        updateProductInfo(product);
        showStatus("Product ready for purchase.", false);
    }

    private void handlePurchase() {
        if (selectedProduct == null) {
            showStatus("Select a product first.", true);
            return;
        }

        if (selectedProduct.getQuantity() <= 0) {
            showStatus("This product is out of stock.", true);
            buyButton.setEnabled(false);
            return;
        }

        boolean success = productService.decreaseProductQuantity(selectedProduct);
        if (success) {
            showStatus("Purchase completed. Remaining stock: " + selectedProduct.getQuantity(), false);
            updateProductInfo(selectedProduct);
        } else {
            showStatus("Unable to complete purchase. Please try again.", true);
        }
    }

    private void updateProductInfo(Product product) {
        String details = "<html><b>" + product.getBrand() + "</b> (" + product.getCode() + ")<br/>" +
                "Display: " + product.getDisplay_technology() + "<br/>" +
                "Diagonal: " + product.getDiagonal_size() + "\"<br/>" +
                "Size: " + product.getWidth() + "cm x " + product.getHeight() + "cm<br/>" +
                "Price: " + product.getPrice() + "</html>";

        productInfoLabel.setText(details);
        quantityLabel.setText("In stock: " + product.getQuantity());
        buyButton.setEnabled(product.getQuantity() > 0);
    }

    private void clearSelection() {
        selectedProduct = null;
        productInfoLabel.setText("No product selected");
        quantityLabel.setText("");
        buyButton.setEnabled(false);
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

public class ByingWindow extends JFrame {

    private final ProductService productService;

    private JTextField codeField;
    private JLabel productInfoLabel;
    private JLabel quantityLabel;
    private JLabel statusLabel;
    private JButton buyButton;

    private Product selectedProduct;

    public ByingWindow(ProductService productService) {
        this.productService = productService;
        buildWindow();
        setVisible(true);
    }

    private void buildWindow() {
        setTitle("Buy product");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("Buy a TV", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel searchPanel = buildSearchPanel();
        JPanel infoPanel = buildInfoPanel();
        JPanel bottomPanel = buildBottomPanel();

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(searchPanel, BorderLayout.WEST);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    private JPanel buildSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        codeField = new JTextField();
        codeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        JButton findButton = ButtonView.create("Find by Code");
        findButton.addActionListener(e -> handleSearch());

        panel.add(new JLabel("Product code:"));
        panel.add(Box.createVerticalStrut(6));
        panel.add(codeField);
        panel.add(Box.createVerticalStrut(12));
        panel.add(findButton);

        return panel;
    }

    private JPanel buildInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Product details"));

        productInfoLabel = new JLabel("No product selected");
        productInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        quantityLabel = new JLabel("");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));

        buyButton = ButtonView.create("Buy");
        buyButton.addActionListener(e -> handlePurchase());
        buyButton.setEnabled(false);

        JPanel header = new JPanel(new BorderLayout());
        header.add(productInfoLabel, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.add(quantityLabel, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(buyButton);

        panel.add(header, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(actions, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        statusLabel = new JLabel("Enter product code to begin.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        panel.add(statusLabel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.EAST);

        return panel;
    }

    private void handleSearch() {
        String code = codeField.getText().trim();
        if (code.isEmpty()) {
            showStatus("Please enter a product code.", true);
            clearSelection();
            return;
        }

        Product product = productService.findProductByCode(code);
        if (product == null) {
            showStatus("No product found with code " + code + ".", true);
            clearSelection();
            return;
        }

        selectedProduct = product;
        updateProductInfo(product);
        showStatus("Product ready for purchase.", false);
    }

    private void handlePurchase() {
        if (selectedProduct == null) {
            showStatus("Select a product first.", true);
            return;
        }

        if (selectedProduct.getQuantity() <= 0) {
            showStatus("This product is out of stock.", true);
            buyButton.setEnabled(false);
            return;
        }

        boolean success = productService.decreaseProductQuantity(selectedProduct);
        if (success) {
            showStatus("Purchase completed. Remaining stock: " + selectedProduct.getQuantity(), false);
            updateProductInfo(selectedProduct);
        } else {
            showStatus("Unable to complete purchase. Please try again.", true);
        }
    }

    private void updateProductInfo(Product product) {
        String details = "<html><b>" + product.getBrand() + "</b> (" + product.getCode() + ")<br/>" +
                "Display: " + product.getDisplay_technology() + "<br/>" +
                "Diagonal: " + product.getDiagonal_size() + "\"<br/>" +
                "Size: " + product.getWidth() + "cm x " + product.getHeight() + "cm<br/>" +
                "Price: " + product.getPrice() + "</html>";

        productInfoLabel.setText(details);
        quantityLabel.setText("In stock: " + product.getQuantity());
        buyButton.setEnabled(product.getQuantity() > 0);
    }

    private void clearSelection() {
        selectedProduct = null;
        productInfoLabel.setText("No product selected");
        quantityLabel.setText("");
        buyButton.setEnabled(false);
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? new Color(178, 34, 34) : new Color(34, 139, 34));
    }
}
