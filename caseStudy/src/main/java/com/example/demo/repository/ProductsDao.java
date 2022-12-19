package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Products;

@Repository
public interface ProductsDao extends JpaRepository<Products, Long>{
	
	@Query(value ="SELECT * FROM products p WHERE p.name =:n and p.price =:pr and p.details =:m AND p.category =:q AND p.subcategory =:r", nativeQuery = true)
	public Products getProductByName(@Param("n") String n,@Param("pr") String pr, @Param("m") String m, @Param("q") String q, @Param("r") String r);
	
	@Query(value="UPDATE products p SET p.name =:n, p.price =:pr, p.details =:m, p.category =:q, p.subcategory =:r WHERE p.product_id =:id", nativeQuery = true)
	public void updationOfData(@Param("n") String n,@Param("pr") String pr, @Param("m") String m, @Param("q") String q, @Param("r") String r, @Param("id") long id);
	
	@Query(value="SELECT* FROM products p WHERE p.product_id =:id", nativeQuery = true)
	public Products getByIdp(@Param("id") long id);
	
	@Query(value="SELECT* FROM products p WHERE p.category =:q",nativeQuery = true)
	public List<Products> getByCategory(@Param("q") String q);
	
	@Query(value="SELECT* FROM products p WHERE p.name =:q OR p.details =:q OR p.category =:q OR p.subcategory =:q", nativeQuery = true)
	public List<Products> getBySearchString(@Param("q") String q);
}
