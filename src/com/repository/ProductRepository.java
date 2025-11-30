package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.model.Product;
import com.util.CSVParser;

public class ProductRepository {
	private final String csvFilePath;
	
	public ProductRepository(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}
	
	public List<Product> loadProducts() {
		List<Product> parsedProducts = CSVParser.parseCSV(csvFilePath);
		if (parsedProducts == null) {
			return new ArrayList<>();
		}
		
		return new ArrayList<>(parsedProducts);
	}
	
	public String getSourceName() {
		return csvFilePath;
	}
}

