package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.OrderedTable;

public interface OrdersOfUsers extends JpaRepository<OrderedTable, Long>{
	
	@Query(value="SELECT* FROM ordered_table o WHERE o.user_id =:u ", nativeQuery = true)
	public List<OrderedTable> gettingOrderdByUserId(@Param("u") long userId);
}
