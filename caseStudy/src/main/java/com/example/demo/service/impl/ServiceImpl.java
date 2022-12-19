package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.ResponseClassses.Address;
import com.example.demo.ResponseClassses.CartResponse;
import com.example.demo.ResponseClassses.OrdersResponse;
import com.example.demo.ResponseClassses.Product;
import com.example.demo.ResponseClassses.UsersResonse;
import com.example.demo.model.ActiveLogin;
import com.example.demo.model.Carts;
import com.example.demo.model.OrderedTable;
import com.example.demo.model.Products;
import com.example.demo.model.Users;
import com.example.demo.repository.ActiveLoginDao;
import com.example.demo.repository.CartsDao;
import com.example.demo.repository.OrdersOfUsers;
import com.example.demo.repository.ProductsDao;
import com.example.demo.repository.UsersDao;

@Service
public class ServiceImpl implements ServiceCase{
	@Autowired
	private ProductsDao productDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private OrdersOfUsers ordersOfusers;
	@Autowired
	private ActiveLoginDao activeLogindao;
	@Autowired
	private CartsDao cartsDao;
	@Override
	public void addUsers(Users user) {
		usersDao.save(user);
	}
	@Override
	public boolean loginCheck(String email, String password) {
		List<Users> list = usersDao.findAll();
		for(Users us: list) {
			if(us.getEmail().equals(email) && us.getPassword().equals(password)) {
				activeLogindao.save(new ActiveLogin(us.getUserId()));
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean checkLoginActive(long login) {
		List<ActiveLogin> loginPersons = activeLogindao.findAll();
		for(ActiveLogin ac : loginPersons) {
			if(ac.getUserId()==login){
				return true;
			}
		}
		return false;
	}
	@Override
	public void deleteActiveLogin(long UserId) {
		ActiveLogin entity = activeLogindao.getOne(UserId);
		activeLogindao.delete(entity);
	}
	@Override
	public UsersResonse getProfile(long UserId) {
		List<Users> list = usersDao.findAll();
		for(Users us:list) {
			if(us.getUserId()==UserId) {
				Address ad = new Address(us.getStreet(),us.getCity(),us.getState(),us.getPincode());
				UsersResonse usr = new UsersResonse(UserId,us.getName(),us.getEmail(),us.getPhone(),ad);
				return usr;
			}
		}
		return null;
	}
	@Override
	public boolean postUpdateProfile(UsersResonse usr) {
		List<Users> list = usersDao.findAll();
		for(Users us: list) {
			if(us.getUserId()==usr.getUserID()) {
				us.setPhone(usr.getPhone());
				us.setStreet(usr.getAddress().getStreet());
				us.setCity(usr.getAddress().getCity());
				us.setState(usr.getAddress().getState());
				us.setPincode(usr.getAddress().getPincode());
				usersDao.saveAll(list);
				return true;
			}
		}
		return false;
	}
	@Override
	public ResponseEntity<Products> addingProduct(Products pro) {
		if(productDao.getProductByName(pro.getName(),pro.getPrice(),pro.getDetails(),pro.getCategory(),pro.getSubcategory())==null) {
			productDao.save(pro);
			return new ResponseEntity<>(pro, HttpStatus.ACCEPTED);
		}else {
			Products prod = productDao.getProductByName(pro.getName(),pro.getPrice(),pro.getDetails(),pro.getCategory(),pro.getSubcategory());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	@Override
	public ResponseEntity<Products> updatingProduct(Products pro) {
		productDao.updationOfData(pro.getName(), pro.getPrice(),pro.getDetails(),pro.getCategory(),pro.getSubcategory(),pro.getProductId());
		Products prog = productDao.getById(pro.getProductId());
		return new ResponseEntity<>(prog, HttpStatus.ACCEPTED);
	}
	@Override
	public ResponseEntity<Products> getingProductById(long productId) {
		if(productDao.getByIdp(productId)==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(productDao.getReferenceById(productId),HttpStatus.OK);
		}
	}
	@Override
	public ResponseEntity<List<Products>> gettingProductsByCategory(String catgry) {
		List<Products> listOfProducts = productDao.getByCategory(catgry);
		if(listOfProducts.size()==0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(listOfProducts,HttpStatus.FOUND);
		}
	}
	@Override
	public ResponseEntity<List<Products>> gettingProductsBySearch(String searchString) {
		List<Products> searchedList = productDao.getBySearchString(searchString);
		if(searchedList.size()==0) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity(searchedList,HttpStatus.FOUND);
		}
	}
	@Override
	public ResponseEntity addingToCart(String userId, String productId) {
		if(cartsDao.checkingExistenceOfProduct(Long.parseLong(userId),Long.parseLong(productId))==null) {
			cartsDao.save(new Carts(1,Long.parseLong(userId),Long.parseLong(productId),1));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			Carts cart1 = cartsDao.checkingExistenceOfProduct(Long.parseLong(userId),Long.parseLong(productId));
			cartsDao.updationOfQuantity(cart1.getQuantity()+1, Long.parseLong(userId), Long.parseLong(productId));
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	}
	@Override
	public ResponseEntity<List<CartResponse>> getCartByUserId(long UserId) {
		List<Carts> list = cartsDao.gettingCartByUserId(UserId);
		if(list.size() !=0) {
			List<CartResponse> listC = new ArrayList<>();
			for(int i=0;i<list.size();i++) {
				CartResponse car = new CartResponse();
				car.setCartItemId(list.get(i).getCartId());
				Products prod = productDao.getByIdp(list.get(i).getProductId());
				Product newPro = new Product(prod.getProductId(),prod.getName(),prod.getPrice(),prod.getDetails(),prod.getCategory(),prod.getSubcategory());
				car.setProduct(newPro);
				car.setQuantity(list.get(i).getQuantity());
				listC.add(car);
			}
			
			return new ResponseEntity(listC,HttpStatus.FOUND);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	@Override
	public ResponseEntity<CartResponse> getCartbyUserIdAndCartItemId(long userId, long cartItemId) {
		Carts cart = cartsDao.gettingProductByUserIdAndUserId(userId, cartItemId);
		if(cart != null) {
			CartResponse cart1 = new CartResponse();
			cart1.setCartItemId(cart.getCartId());
			Products prod = productDao.getByIdp(cart.getProductId());
			Product newPro = new Product(prod.getProductId(),prod.getName(),prod.getPrice(),prod.getDetails(),prod.getCategory(),prod.getSubcategory());
			cart1.setProduct(newPro);
			cart1.setQuantity(cart.getQuantity());
			return new ResponseEntity<>(cart1,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@Override
	public String removeProductByUserIDFromCart(long userId,long productId) {
		Products prod = productDao.getByIdp(productId);
		String name = prod.getName();
		cartsDao.deletingProductWithUser(userId, productId);
		return name + " removed from cart";
	}
	@Override
	public ResponseEntity<CartResponse> updatingQuantityUsingUserIdAndProductId(long quantity, long userId,
			long productId) {
		cartsDao.updationOfQuantity(quantity, userId, productId);
		Carts cart = cartsDao.checkingExistenceOfProduct(userId, productId);
		if(cart != null) {
			CartResponse cart1 = new CartResponse();
			cart1.setCartItemId(cart.getCartId());
			Products prod = productDao.getByIdp(cart.getProductId());
			Product newPro = new Product(prod.getProductId(),prod.getName(),prod.getPrice(),prod.getDetails(),prod.getCategory(),prod.getSubcategory());
			cart1.setProduct(newPro);
			cart1.setQuantity(quantity);
			return new ResponseEntity<>(cart1,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	@Override
	public ResponseEntity<OrderedTable> additionOfDataToOrderedTable(long userId) {
		List<Carts> list = cartsDao.gettingCartByUserId(userId);
		List<OrderedTable> otab = ordersOfusers.gettingOrderdByUserId(userId);
		if(list.size() !=0 && otab.size()==0) {
			for(int i=0;i<list.size();i++) {
				Carts cart = list.get(i);
				OrderedTable orderTable = new OrderedTable(1,cart.getUserId(),cart.getProductId(),cart.getQuantity(),"Arrived", new Date());
				ordersOfusers.save(orderTable);
			}
			return new ResponseEntity(ordersOfusers.gettingOrderdByUserId(userId),HttpStatus.FOUND);
			
		}else if(otab.size() !=0){
			return new ResponseEntity(ordersOfusers.gettingOrderdByUserId(userId),HttpStatus.FOUND);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	@Override
	public ResponseEntity<OrderedTable> gettingOrderDetails(long userId) {
		List<OrderedTable> otab = ordersOfusers.gettingOrderdByUserId(userId);
		if(otab.size() !=0) {
			return new ResponseEntity(ordersOfusers.gettingOrderdByUserId(userId),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
