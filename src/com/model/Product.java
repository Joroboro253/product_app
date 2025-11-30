package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
	private final String code;
	private final String brand;
	private final int diagonal_size;
	private final double width;
	private final double height;
	private final String display_technology;
	private final BigDecimal price;
	private int quantity;
	
	private Product(String code, String brand, int diagonal_size, double width, double height,
			String display_technology, BigDecimal price, int quantity) {
		this.code = code;
		this.brand = brand;
		this.diagonal_size = diagonal_size;
		this.width = width;
		this.height = height;
		this.display_technology = display_technology;
		this.price = price;
		this.quantity = quantity;
	}

	public static Builder builder(String code, String brand, BigDecimal price) {
		return new Builder(code, brand, price);
	}

	public String getCode() {
		return code;
	}

	public String getBrand() {
		return brand;
	}

	public int getDiagonal_size() {
		return diagonal_size;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public String getDisplay_technology() {
		return display_technology;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public static final class Builder {
		private String code;
		private String brand;
		private int diagonal_size;
		private double width;
		private double height;
		private String display_technology;
		private BigDecimal price;
		private int quantity;
		
		private Builder(String code, String brand, BigDecimal price) {
			withCode(code);
			withBrand(brand);
			withPrice(price);
		}
		
		public Builder withCode(String code) {
			this.code = code;
			return this;
		}
		
		public Builder withBrand(String brand) {
			this.brand = brand;
			return this;
		}
		
		public Builder withDiagonalSize(int diagonal_size) {
			this.diagonal_size = diagonal_size;
			return this;
		}
		
		public Builder withWidth(double width) {
			this.width = width;
			return this;
		}
		
		public Builder withHeight(double height) {
			this.height = height;
			return this;
		}
		
		public Builder withDisplayTechnology(String display_technology) {
			this.display_technology = display_technology;
			return this;
		}
		
		public Builder withPrice(BigDecimal price) {
			this.price = price;
			return this;
		}
		
		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public Product build() {
			validate();
			return new Product(code, brand, diagonal_size, width, height, display_technology, price, quantity);
		}

		private void validate() {
			ensureNotBlank(code, "Code");
			ensureNotBlank(brand, "Brand");
			ensurePositive(diagonal_size, "Diagonal size");
			ensurePositive(width, "Width");
			ensurePositive(height, "Height");
			ensureNotBlank(display_technology, "Display technology");
			ensurePositive(Objects.requireNonNull(price, "Price must not be null"), "Price");
			ensureNonNegative(quantity, "Quantity");
		}

		private static void ensureNotBlank(String value, String fieldName) {
			if (value == null || value.trim().isEmpty()) {
				throw new IllegalArgumentException(fieldName + " must not be empty.");
			}
		}

		private static void ensurePositive(int value, String fieldName) {
			if (value <= 0) {
				throw new IllegalArgumentException(fieldName + " must be greater than 0.");
			}
		}

		private static void ensurePositive(double value, String fieldName) {
			if (Double.compare(value, 0.0) <= 0) {
				throw new IllegalArgumentException(fieldName + " must be greater than 0.");
			}
		}

		private static void ensurePositive(BigDecimal value, String fieldName) {
			if (value.compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException(fieldName + " must be greater than 0.");
			}
		}

		private static void ensureNonNegative(int value, String fieldName) {
			if (value < 0) {
				throw new IllegalArgumentException(fieldName + " must be zero or positive.");
			}
		}
	}
}
package com.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
	private final String code;
	private final String brand;
	private final int diagonal_size;
	private final double width;
	private final double height;
	private final String display_technology;
	private final BigDecimal price;
	private int quantity;
	
	private Product(String code, String brand, int diagonal_size, double width, double height,
			String display_technology, BigDecimal price, int quantity) {
		this.code = code;
		this.brand = brand;
		this.diagonal_size = diagonal_size;
		this.width = width;
		this.height = height;
		this.display_technology = display_technology;
		this.price = price;
		this.quantity = quantity;
	}


	public static Builder builder(String code, String brand, BigDecimal price) {
		return new Builder(code, brand, price);
	}

	public String getCode() {
		return code;
	}

	public String getBrand() {
		return brand;
	}

	public int getDiagonal_size() {
		return diagonal_size;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public String getDisplay_technology() {
		return display_technology;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public static final class Builder {
		private String code;
		private String brand;
		private int diagonal_size;
		private double width;
		private double height;
		private String display_technology;
		private BigDecimal price;
		private int quantity;
		
		private Builder(String code, String brand, BigDecimal price) {
			withCode(code);
			withBrand(brand);
			withPrice(price);
		}
		
		public Builder withCode(String code) {
			this.code = code;
			return this;
		}
		
		public Builder withBrand(String brand) {
			this.brand = brand;
			return this;
		}
		
		public Builder withDiagonalSize(int diagonal_size) {
			this.diagonal_size = diagonal_size;
			return this;
		}
		
		public Builder withWidth(double width) {
			this.width = width;
			return this;
		}
		
		public Builder withHeight(double height) {
			this.height = height;
			return this;
		}
		
		public Builder withDisplayTechnology(String display_technology) {
			this.display_technology = display_technology;
			return this;
		}
		
		public Builder withPrice(BigDecimal price) {
			this.price = price;
			return this;
		}
		
		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public Product build() {
			validate();
			return new Product(code, brand, diagonal_size, width, height, display_technology, price, quantity);
		}

		private void validate() {
			ensureNotBlank(code, "Code");
			ensureNotBlank(brand, "Brand");
			ensurePositive(diagonal_size, "Diagonal size");
			ensurePositive(width, "Width");
			ensurePositive(height, "Height");
			ensureNotBlank(display_technology, "Display technology");
			ensurePositive(Objects.requireNonNull(price, "Price must not be null"), "Price");
			ensureNonNegative(quantity, "Quantity");
		}

		private static void ensureNotBlank(String value, String fieldName) {
			if (value == null || value.trim().isEmpty()) {
				throw new IllegalArgumentException(fieldName + " must not be empty.");
			}
		}

		private static void ensurePositive(int value, String fieldName) {
			if (value <= 0) {
				throw new IllegalArgumentException(fieldName + " must be greater than 0.");
			}
		}

		private static void ensurePositive(double value, String fieldName) {
			if (Double.compare(value, 0.0) <= 0) {
				throw new IllegalArgumentException(fieldName + " must be greater than 0.");
			}
		}

		private static void ensurePositive(BigDecimal value, String fieldName) {
			if (value.compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException(fieldName + " must be greater than 0.");
			}
		}

		private static void ensureNonNegative(int value, String fieldName) {
			if (value < 0) {
				throw new IllegalArgumentException(fieldName + " must be zero or positive.");
			}
		}
	}
}
