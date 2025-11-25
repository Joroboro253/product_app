package com.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.Menu;
import com.Product;
import com.repository.ProductRepository;
import com.util.ProductFormatter;
import com.util.UserInputReader;


public class ProductService {
	private final Menu menu;
	private final ProductRepository repository;
	private final UserInputReader inputReader;
	private final ProductFormatter formatter;
	private List<Product> products = new ArrayList<>();
	
	public ProductService(Menu menu, Scanner scanner, String csvFilePath) {
		this(menu, new ProductRepository(csvFilePath), new UserInputReader(menu, scanner), new ProductFormatter());
	}
	
	public ProductService(Menu menu, ProductRepository repository, UserInputReader inputReader, ProductFormatter formatter) {
		this.menu = menu;
		this.repository = repository;
		this.inputReader = inputReader;
		this.formatter = formatter;
	}
	
	public void loadProductsFromCSV() {
		menu.showMessage("\nLoading products from " + repository.getSourceName() + "...");
		List<Product> loadedProducts = repository.loadProducts();
		
		if (loadedProducts.isEmpty()) {
			menu.showError("No products were loaded. Please check the CSV file.");
			return;
		}
		
		this.products = loadedProducts;
		menu.showMessage("Loaded " + products.size() + " products.");
	}
	
	public void printAllProducts() {
		if (!ensureProductsLoaded()) {
			return;
		}
		
		menu.showMessage("=== Products ===");
		for (Product product : products) {
			menu.showMessage(formatter.format(product));
		}
	}
	
	public void handleSearchMenu() {
		if (!ensureProductsLoaded()) {
			return;
		}
		
		menu.showMessage("Search by:");
		menu.showMessage("1 - Code");
		menu.showMessage("2 - Brand");
		
		String option = inputReader.readLine("Choose option: ");
		
		switch (option) {
			case "1":
				String productCode = inputReader.readRequiredString("Enter product code:");
				searchByCode(productCode);
				break;
			case "2":
				String productBrand = inputReader.readRequiredString("Enter product brand:");
				searchByBrand(productBrand);
				break;
			default:
				menu.showError("Invalid search option.");
		}
	}
	
	public void addNewProduct() {
		menu.showMessage("=== Add new product ===");
		
		String code = inputReader.readRequiredString("Enter code: ");
		String brand = inputReader.readRequiredString("Enter brand: ");
		int diagonalSize = inputReader.readInt("Enter diagonal size (inches): ");
		double width = inputReader.readDouble("Enter width (cm): ");
		double height = inputReader.readDouble("Enter height (cm): ");
		String displayTechnology = inputReader.readRequiredString("Enter display technology: ");
		BigDecimal price = inputReader.readBigDecimal("Enter price: ");
		int quantity = inputReader.readInt("Enter quantity: ");
		
		Product newProduct = new Product(code, brand, diagonalSize, width, height, displayTechnology, price, quantity);
		products.add(newProduct);
		menu.showMessage("Product added to the in-memory list.");
	}
	
	public void applySorting() {
		if (!ensureProductsLoaded()) {
			return;
		}
		
		menu.showMessage("Sort products by:");
		menu.showMessage("1 - Brand");
		menu.showMessage("2 - Diagonal size");
		menu.showMessage("3 - Price");
		
		String option = inputReader.readLine("Choose option: ");
		Comparator<Product> comparator;
		
		switch (option) {
			case "1":
				comparator = Comparator.comparing(product -> product.getBrand().toLowerCase());
				break;
			case "2":
				comparator = Comparator.comparingInt(Product::getDiagonal_size);
				break;
			case "3":
				comparator = Comparator.comparing(Product::getPrice);
				break;
			default:
				menu.showError("Unknown sorting option.");
				return;
		}
		
		Collections.sort(products, comparator);
		menu.showMessage("Products sorted.");
		printAllProducts();
	}
	
	public void buyTV() {
		if (!ensureProductsLoaded()) {
			return;
		}
		
		String code = inputReader.readRequiredString("Enter the code of the TV you want to buy:");
		Product product = findProductByCode(code);
		if (product == null) {
			menu.showError("Product not found.");
			return;
		}
		
		if (product.getQuantity() <= 0) {
			menu.showError("Product out of stock.");
			return;
		}
		
		menu.showMessage("You selected: " + formatter.format(product));
		menu.showMessage("1 - Buy");
		menu.showMessage("2 - Cancel");
		
		String decision = inputReader.readLine("Choose option: ");
		if ("1".equals(decision)) {
			product.setQuantity(product.getQuantity() - 1);
			menu.showMessage("Purchase completed. Remaining stock: " + product.getQuantity());
		} else {
			menu.showMessage("Purchase cancelled.");
		}
	}
	
	private void searchByCode(String code) {
		Product product = findProductByCode(code);
		
		if (product == null) {
			menu.showError("No product found for code " + code + ".");
		} else {
			menu.showMessage(formatter.format(product));
		}
	}
	
	private void searchByBrand(String brand) {
		List<Product> matches = products.stream()
				.filter(product -> product.getBrand().equalsIgnoreCase(brand.trim()))
				.collect(Collectors.toList());
		
		if (matches.isEmpty()) {
			menu.showError("No products found for brand " + brand + ".");
			return;
		}
		
		menu.showMessage("Found " + matches.size() + " product(s):");
		for (Product product : matches) {
			menu.showMessage(formatter.format(product));
		}
	}
	
	private Product findProductByCode(String code) {
		String trimmedCode = code.trim();
		
		for (Product product : products) {
			if (product.getCode().equalsIgnoreCase(trimmedCode)) {
				return product;
			}
		}
		
		return null;
	}
	
	private boolean ensureProductsLoaded() {
		if (products.isEmpty()) {
			menu.showError("No products loaded. Please select option 1 first.");
			return false;
		}
		
		return true;
	}
}

