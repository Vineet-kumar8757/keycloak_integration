package com.example.demo.ResponseClassses;

public class CartResponse {
	private long cartItemId;
	private Product product;
	private long quantity;
	public long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public CartResponse(long cartItemId, Product product, long quantity) {
		super();
		this.cartItemId = cartItemId;
		this.product = product;
		this.quantity = quantity;
	}
	public CartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
