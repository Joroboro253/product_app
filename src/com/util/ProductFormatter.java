package com.util;

import com.Product;

public class ProductFormatter {
	public String format(Product product) {
		return "(" + product.getCode() + ") "
				+ product.getBrand() + " (" + product.getDisplay_technology() + ") "
				+ product.getDiagonal_size() + "\" - " + product.getPrice() + "$ (w:h: "
				+ product.getWidth() + "cm, " + product.getHeight() + "cm) -> "
				+ product.getQuantity() + " in stock";
	}
}

