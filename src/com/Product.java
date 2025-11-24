package com;

import java.math.BigDecimal;

public class Product {
	private String code;
	private String brand;
	private int diagonal_size;
	private double width;
	private double height;
	private String display_technology;
	private BigDecimal price;
	private int quantity;
	
	public Product(String code, String brand, int diagonal_size, double width, double height, String display_technology,
			BigDecimal price, int quantity) {
		this.code = code;
		this.brand = brand;
		this.diagonal_size = diagonal_size;
		this.width = width;
		this.height = height;
		this.display_technology = display_technology;
		this.price = price;
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getDiagonal_size() {
		return diagonal_size;
	}

	public void setDiagonal_size(int diagonal_size) {
		this.diagonal_size = diagonal_size;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getDisplay_technology() {
		return display_technology;
	}

	public void setDisplay_technology(String display_technology) {
		this.display_technology = display_technology;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
