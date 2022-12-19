package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Carts")
public class Carts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;
	@Column(name="UserId")
	private long UserId;
	@Column(name="ProductId")
	private long ProductId;
	@Column(name="quantity")
	private long quantity;
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		this.UserId = userId;
	}
	public long getProductId() {
		return ProductId;
	}
	public void setProductId(long productId) {
		this.ProductId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Carts(long cartId, long userId, long productId, long quantity) {
		super();
		this.cartId = cartId;
		this.UserId = userId;
		this.ProductId = productId;
		this.quantity = quantity;
	}
	public Carts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
