package com.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.model.Product;
import com.repository.ProductRepository;

public class ProductService {
	private final ProductRepository repository;
	private List<Product> products = new ArrayList<>();
	
	public ProductService(String csvFilePath) {
		this(new ProductRepository(csvFilePath));
	}
	
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public boolean loadProductsFromCSV() {
		List<Product> loadedProducts = repository.loadProducts();
		
		if (loadedProducts.isEmpty()) {
			return false;
		}
		
		this.products = loadedProducts;
		return true;
	}
	
    public List<Product> getLoadedProducts() {
        return new ArrayList<>(products);
    }

	public boolean addProduct(Product product) {
		if (product == null) {
			return false;
		}
		products.add(product);
		return true;
	}
	
	public Product findProductByCode(String code) {
		String trimmedCode = code.trim();
		
		for (Product product : products) {
			if (product.getCode().equalsIgnoreCase(trimmedCode)) {
				return product;
			}
		}
		
		return null;
	}

	public boolean decreaseProductQuantity(Product product) {
		if (product == null || product.getQuantity() <= 0) {
			return false;
		}
		product.setQuantity(product.getQuantity() - 1);
		return true;
	}
	
	public List<Product> sortProducts(SortOption option) {
		if (option == null) {
			throw new IllegalArgumentException("Sort option must be provided.");
		}
		List<Product> sorted = new ArrayList<>(products);
		sorted.sort(resolveComparator(option));
		this.products = sorted;
		return new ArrayList<>(sorted);
	}
	
	private Comparator<Product> resolveComparator(SortOption option) {
		switch (option) {
			case BRAND:
				return Comparator.comparing(product -> {
					String brand = product.getBrand();
					return brand == null ? "" : brand.toLowerCase();
				});
			case DIAGONAL:
				return Comparator.comparingInt(Product::getDiagonal_size);
			case PRICE:
				return Comparator.comparing(Product::getPrice);
			default:
				throw new IllegalArgumentException("Unknown sorting option.");
		}
	}
	
	public enum SortOption {
		BRAND,
		DIAGONAL,
		PRICE
	}
}
package com.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.model.Product;
import com.repository.ProductRepository;

public class ProductService {
	private final ProductRepository repository;
	private List<Product> products = new ArrayList<>();
	
	public ProductService(String csvFilePath) {
		this(new ProductRepository(csvFilePath));
	}
	
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public boolean loadProductsFromCSV() {
		List<Product> loadedProducts = repository.loadProducts();
		
		if (loadedProducts.isEmpty()) {
			return false;
		}
		
		this.products = loadedProducts;
		return true;
	}
	
    public List<Product> getLoadedProducts() {
        return new ArrayList<>(products);
    }

	public boolean addProduct(Product product) {
		if (product == null) {
			return false;
		}
		products.add(product);
		return true;
	}
	
	public Product findProductByCode(String code) {
		String trimmedCode = code.trim();
		
		for (Product product : products) {
			if (product.getCode().equalsIgnoreCase(trimmedCode)) {
				return product;
			}
		}
		
		return null;
	}

	public boolean decreaseProductQuantity(Product product) {
		if (product == null || product.getQuantity() <= 0) {
			return false;
		}
		product.setQuantity(product.getQuantity() - 1);
		return true;
	}
	
	public List<Product> sortProducts(SortOption option) {
		if (option == null) {
			throw new IllegalArgumentException("Sort option must be provided.");
		}
		List<Product> sorted = new ArrayList<>(products);
		sorted.sort(resolveComparator(option));
		this.products = sorted;
		return new ArrayList<>(sorted);
	}
	
	private Comparator<Product> resolveComparator(SortOption option) {
		switch (option) {
			case BRAND:
				return Comparator.comparing(product -> {
					String brand = product.getBrand();
					return brand == null ? "" : brand.toLowerCase();
				});
			case DIAGONAL:
				return Comparator.comparingInt(Product::getDiagonal_size);
			case PRICE:
				return Comparator.comparing(Product::getPrice);
			default:
				throw new IllegalArgumentException("Unknown sorting option.");
		}
	}
	
	public enum SortOption {
		BRAND,
		DIAGONAL,
		PRICE
	}
}

