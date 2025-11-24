package com;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class CSVParser {
	public static List<Product> parseCSV(String filePath) {
		List<Product> products = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			line = reader.readLine();
						
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty()) {
					continue;
				}
				
				String[] parts = line.split(",");
				
				if (parts.length != 8) {
					System.err.println("Error parsing product: invalid line: " + line);
					continue;
				}
				
				try {
					String code = parts[0].trim();
					String brand = parts[1].trim();
					int diagonal_size = Integer.parseInt(parts[2].trim());
					double width = Double.parseDouble(parts[3].trim());
					double height = Double.parseDouble(parts[4].trim());
					String display_technology = parts[5].trim();
					BigDecimal price = new BigDecimal(parts[6].trim());
					int quantity = Integer.parseInt(parts[7].trim());
					
					Product product = new Product(code, brand, diagonal_size, width, height, display_technology, price, quantity);
					products.add(product);
				} catch (NumberFormatException e) {
					System.err.println("Warning: skipping invalid number line: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}
}
