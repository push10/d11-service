package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Matches;

public interface MatchRepository  extends JpaRepository<Matches, Integer> {
	@Query("SELECT m FROM Matches m WHERE m.contest.id=:contestId")
	public List<Matches> loadByContest(@Param("contestId") int contestId);
}