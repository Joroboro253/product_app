package com.interfaces;

import com.service.ProductService;

import javax.swing.SwingUtilities;

public class Launcher {

    private static final String CSV_FILE_PATH = "products.csv";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductService productService = new ProductService(CSV_FILE_PATH);
            MenuWindow main = new MenuWindow(productService);
            main.show();
        });
    }
}
package com.interfaces;

import com.service.ProductService;

import javax.swing.SwingUtilities;

public class Launcher {

    private static final String CSV_FILE_PATH = "products.csv";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductService productService = new ProductService(CSV_FILE_PATH);
            MenuWindow main = new MenuWindow(productService);
            main.show();
        });
    }
}
