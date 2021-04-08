package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT r FROM User r WHERE r.username=:username and r.password=:password")
	public List<User> findByUserName(@Param("username") String username, @Param("password") String password);
}
