package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Carts;

public interface CartsDao extends JpaRepository<Carts, Long>{
	
	@Query(value="SELECT* FROM carts c WHERE c.user_id =:q AND c.product_id =:m",nativeQuery = true)
	public Carts checkingExistenceOfProduct(@Param("q") long q, @Param("m") long m);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE carts c SET c.quantity =:q WHERE c.user_id =:n AND c.product_id =:m", nativeQuery = true)
	public void updationOfQuantity(@Param("q") long q, @Param("n") long n, @Param("m") long m);
	
	@Query(value="SELECT* FROM carts c WHERE c.user_id =:q",nativeQuery = true)
	public List<Carts> gettingCartByUserId(@Param("q") long q);
	
	@Query(value="SELECT* FROM carts c WHERE c.user_id =:q AND c.cart_id =:p",nativeQuery = true)
	public Carts gettingProductByUserIdAndUserId(@Param("q") long q, @Param("p") long p);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM carts c WHERE c.user_id =:q AND c.product_id =:p", nativeQuery = true)
	public void deletingProductWithUser(@Param("q") long q, @Param("p") long p);
	

}
