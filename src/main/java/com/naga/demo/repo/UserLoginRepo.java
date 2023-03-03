package com.naga.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naga.demo.model.LoginRecords;


public interface UserLoginRepo extends JpaRepository<LoginRecords,String>{
	public LoginRecords findByUsername(String username);
}
