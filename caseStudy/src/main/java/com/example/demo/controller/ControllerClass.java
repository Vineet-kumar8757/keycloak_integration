package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseClassses.CartResponse;
import com.example.demo.ResponseClassses.UsersResonse;
import com.example.demo.model.Carts;
import com.example.demo.model.OrderedTable;
import com.example.demo.model.Products;
import com.example.demo.model.Result;
import com.example.demo.model.UserSignUpRes;
import com.example.demo.model.Users;
import com.example.demo.service.impl.ServiceCase;

@RestController
public class ControllerClass {
	@Autowired
	private ServiceCase serviceCase;
	@PostMapping("/login")
	public ResponseEntity getLogin(@RequestBody Users user){
		if(serviceCase.loginCheck(user.getEmail(), user.getPassword())) {
			return new ResponseEntity(new Result("Success"),HttpStatus.OK);
		}else {
			return new ResponseEntity(new Result("failure"),HttpStatus.UNAUTHORIZED);
		}
	}
	@PostMapping("/signup")
	public ResponseEntity<UserSignUpRes> getSignUp(@RequestBody Users user ){
		System.out.println(user.getPassword());
		serviceCase.addUsers(user);
		return new ResponseEntity(new UserSignUpRes(user.getUserId()),HttpStatus.OK);	
	}
	@PostMapping("/logout")
	public ResponseEntity getLogout(@RequestBody Users user) {
		if(serviceCase.checkLoginActive(user.getUserId())) {
			serviceCase.deleteActiveLogin(user.getUserId());
			return new ResponseEntity(new Result("Success"),HttpStatus.OK);
		}else {
			return new ResponseEntity(new Result("failure"),HttpStatus.UNAUTHORIZED);
		}
	}
	@GetMapping("/getprofile/{userId}")
	@RolesAllowed("user")
	public ResponseEntity<?> getProfile(@PathVariable String userId) {
		if(serviceCase.getProfile(Long.parseLong(userId)) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(serviceCase.getProfile(Long.parseLong(userId)),HttpStatus.OK);
	}
	@PostMapping("/updateProfile")
	public ResponseEntity updateProfile(@RequestBody UsersResonse usr) {
		return null;
	}
	
	@PostMapping("/products/addProduct")
	@RolesAllowed("admin")
	public ResponseEntity<Products> addProducts(@RequestBody Products product){
		return serviceCase.addingProduct(product);
	}
	
	@PostMapping("/products/update")
	public ResponseEntity<Products> updateProducts(@RequestBody Products product){
		return serviceCase.updatingProduct(product);
	}
	
	@GetMapping("/products/getById/{productId}")
	public ResponseEntity<Products> getProductsByItsId(@PathVariable String productId){
		return serviceCase.getingProductById(Long.parseLong(productId));
	}
	
	@GetMapping("/products/{category}")
	public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String category){
		return serviceCase.gettingProductsByCategory(category);
	}
	
	@GetMapping("/products/search/{searchString}")
	public ResponseEntity<List<Products>> getProductsBySearch(@PathVariable String searchString){
		return serviceCase.gettingProductsBySearch(searchString);
	}
	
	@GetMapping("/cart/{userId}/add/{productId}")
	public ResponseEntity addProductToCart(@PathVariable String userId, @PathVariable String productId) {
		return serviceCase.addingToCart(userId, productId);
	}
	@GetMapping("/cart/{userId}/getCart")  
	public ResponseEntity getCartItemBYUserId(@PathVariable String userId) {
		return serviceCase.getCartByUserId(Long.parseLong(userId));
	}
	
	@GetMapping("/cart/{userId}/getCartItem/{cartitemId}")
	public ResponseEntity<CartResponse> getCartItemByUserIdAndCartItemId(@PathVariable String userId, @PathVariable String cartitemId) {
		return serviceCase.getCartbyUserIdAndCartItemId(Long.parseLong(userId),Long.parseLong(cartitemId));
	}
	
	@GetMapping("/cart/{userId}/remove/{productId}")
	public String removeProductOfUserId(@PathVariable String userId, @PathVariable String productId) {
		return serviceCase.removeProductByUserIDFromCart(Long.parseLong(userId), Long.parseLong(productId));
	}
	
	@PostMapping("/cart/{userId}/changeQuantity/{productId}")
	public ResponseEntity<CartResponse> changeQuantity(@RequestBody Carts cart, @PathVariable String userId, @PathVariable String productId){
		return serviceCase.updatingQuantityUsingUserIdAndProductId(cart.getQuantity(), Long.parseLong(userId), Long.parseLong(productId));
	}
	
	@GetMapping("/order/{userId}/createOrder")
	public ResponseEntity<OrderedTable> createOrder(@PathVariable String userId){
		return serviceCase.additionOfDataToOrderedTable(Long.parseLong(userId));
	}
	
	@GetMapping("/order/{userId}/getOrders")
	public ResponseEntity<OrderedTable> getOrders(@PathVariable String userId){
		return serviceCase.gettingOrderDetails(Long.parseLong(userId));
	}
	@GetMapping("/products/1")
	public ResponseEntity<String> getString(){
		return new ResponseEntity<String>("Welcome Vineet Kumar",HttpStatus.OK);
	}
	@GetMapping("/products/2")
	public ResponseEntity<String> getrString(){
		return new ResponseEntity<String>("Welcome astha Kumar",HttpStatus.OK);
	}
}
