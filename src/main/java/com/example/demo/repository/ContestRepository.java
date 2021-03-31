package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Contest;

public interface ContestRepository  extends JpaRepository<Contest, Integer> {
	
	
}