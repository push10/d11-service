package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Bet;
import com.example.demo.model.League;
import com.example.demo.model.Matches;


public interface BetRepository extends JpaRepository<Bet, Integer> {

	@Query("SELECT b FROM Bet b WHERE b.match=:match AND b.league=:league")
	public Bet loadByMatch(@Param("match") Matches match, @Param("league") League league);
	
}
