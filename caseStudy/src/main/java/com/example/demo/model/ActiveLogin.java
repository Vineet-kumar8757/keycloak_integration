package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ActiveLogin")
public class ActiveLogin {
	@Id
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public ActiveLogin(long userId) {
		super();
		this.userId = userId;
	}

	public ActiveLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
