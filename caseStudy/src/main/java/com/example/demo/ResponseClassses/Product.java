package com.example.demo.ResponseClassses;

public class Product {
	private long productId;
	private String name;
	private String price;
	private String details;
	private String category;
	private String subcategory;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public Product(long productId, String name, String price, String details, String category, String subcategory) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.details = details;
		this.category = category;
		this.subcategory = subcategory;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
