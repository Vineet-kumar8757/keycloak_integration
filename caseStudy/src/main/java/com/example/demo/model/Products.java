package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String price;
	@Column(nullable=false)
	private String details;
	@Column(nullable=false)
	private String category;
	@Column(nullable=false)
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
	public Products(long productId, String name, String price, String details, String category, String subcategory) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.details = details;
		this.category = category;
		this.subcategory = subcategory;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
