package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.LeagueUserDetails;

public interface LeagueUserDetailsRepository extends JpaRepository<LeagueUserDetails, Integer> {
	@Query("SELECT m FROM LeagueUserDetails m WHERE m.league.id=:leagueId and m.user.id=:userId")
	public List<LeagueUserDetails> loadByContest(@Param("userId") int userId, @Param("leagueId") int leagueId);

	@Query("SELECT m FROM LeagueUserDetails m WHERE m.league.id=:leagueId and m.paymentDone=0")
	public List<LeagueUserDetails> loadNonPaidUsersForLeague(@Param("leagueId") int leagueId);

	@Query(value = "SELECT m FROM LeagueUserDetails m WHERE m.league.id=:leagueId and m.paymentDone=1 ")
	public List<LeagueUserDetails> loadUserParticipatedGames(@Param("leagueId") int leagueId);

	@Modifying
	@Query(value = "update league_user_match_details\r\n" + "set winner_user=:userId \r\n"
			+ "where \"match\"=:matchId and league=:leagueId", nativeQuery = true)
	public void updateWinner(@Param("userId") int userId, @Param("leagueId") int leagueId,
			@Param("matchId") int matchId);

}
