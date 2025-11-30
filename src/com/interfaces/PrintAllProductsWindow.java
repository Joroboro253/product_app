package com.interfaces;

import com.model.Product;
import com.interfaces.utils.ButtonView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrintAllProductsWindow extends JFrame {

    public PrintAllProductsWindow(List<Product> products) {
        setTitle("All products");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titleLabel = new JLabel("All products", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTable table = createTable(products);
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel countLabel = new JLabel("Total products: " + products.size());
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton closeButton = ButtonView.create("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(countLabel, BorderLayout.WEST);
        bottomPanel.add(closeButton, BorderLayout.EAST);

        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
        setVisible(true);
    }

    private JTable createTable(List<Product> products) {
        String[] columnNames = {
                "Code", "Brand", "Diagonal (in)", "Width (cm)", "Height (cm)",
                "Display", "Price", "Quantity"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Product product : products) {
            model.addRow(new Object[]{
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

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        return table;
    }
}

