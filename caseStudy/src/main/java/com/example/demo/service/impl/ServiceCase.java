package com.example.demo.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.ResponseClassses.CartResponse;
import com.example.demo.ResponseClassses.UsersResonse;
import com.example.demo.model.ActiveLogin;
import com.example.demo.model.Carts;
import com.example.demo.model.OrderedTable;
import com.example.demo.model.Products;
import com.example.demo.model.Users;

public interface ServiceCase {
	public void addUsers(Users user);
	public boolean loginCheck(String email, String password);
	public boolean checkLoginActive(long login);
	public void deleteActiveLogin(long UserId);
	public UsersResonse getProfile(long UserId);
	public boolean postUpdateProfile(UsersResonse usr);
	public ResponseEntity<Products> addingProduct(Products pro);
	public ResponseEntity<Products> updatingProduct(Products pro);
	public ResponseEntity<Products> getingProductById(long productId);
	public ResponseEntity<List<Products>> gettingProductsByCategory(String catgry);
	public ResponseEntity<List<Products>> gettingProductsBySearch(String searchString);
	public ResponseEntity addingToCart(String userId, String productId);
	public ResponseEntity<List<CartResponse>> getCartByUserId(long UserId);
	public ResponseEntity<CartResponse> getCartbyUserIdAndCartItemId(long userId, long cartItemId);
	public String removeProductByUserIDFromCart(long userId, long productId);
	public ResponseEntity<CartResponse> updatingQuantityUsingUserIdAndProductId(long quantity, long userId, long productId);
	public ResponseEntity<OrderedTable> additionOfDataToOrderedTable(long userId);
	public ResponseEntity<OrderedTable> gettingOrderDetails(long userId);
}
