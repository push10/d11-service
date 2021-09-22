package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Matches;

public interface MatchRepository  extends JpaRepository<Matches, Integer> {
	@Query("SELECT m FROM Matches m WHERE m.contest.id=:contestId order by m.matchNo")
	public List<Matches> loadByContest(@Param("contestId") int contestId);
	
	@Query("SELECT m FROM Matches m WHERE m.schedule  BETWEEN :startDate AND :endDate")
	public List<Matches> loadByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate  );
	
	@Modifying
	@Query(value = "insert into league_user_match_details (`league`, `user`, `match`) values ( :league, :user, :match)", nativeQuery = true)
	public void insertLeagueUserMatch(@Param("league") int league, @Param("user") int user,@Param("match") int match);
}