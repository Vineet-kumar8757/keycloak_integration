package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Users;

public interface UsersDao extends JpaRepository<Users, Long>{
	@Query(value="SELECT* FROM users u WHERE u.email =:n",nativeQuery = true)
	public Users findByEmail(@Param("n") String n);
	
}
