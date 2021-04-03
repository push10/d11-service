package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.League;

public interface LeagueRepository extends JpaRepository<League, Integer> {

	@Query("SELECT l FROM League l WHERE l.joiningCode=:code")
	public League findByCode(@Param("code") String code);

	@Modifying
	@Query(value = "insert into league_users (league, user) values ( :league, :user)", nativeQuery = true)
	public void joinContestByCode(@Param("league") int league, @Param("user") int user);
	
	@Query( value = "SELECT l.* FROM league l, d11_db.users u, d11_db.league_users lu \r\n" + 
			"where l.id = lu.league\r\n" + 
			"and u.id  = lu.user\r\n" + 
			"and lu.user =:userId ",
			nativeQuery = true)
	public List<League> findByUserId(@Param("userId") int userId);
	
}