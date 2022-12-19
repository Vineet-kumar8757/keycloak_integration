package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ActiveLogin;

public interface ActiveLoginDao extends JpaRepository<ActiveLogin, Long>{

}
