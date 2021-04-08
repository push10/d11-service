package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.model.LeagueUserDetails;
import com.example.demo.model.UserInvestmentDetails;
import com.example.demo.repository.LeagueUserDetailsRepository;

@Service
@Transactional
public class LeagueUserDetailsService {
	@Autowired
	private LeagueUserDetailsRepository leagueUserDetailsRepository;

	public List<LeagueUserDetails> loadLeagueUserDetails(int userId, int leagueId) {
		List<String> listOfNonPaidUsers = loadNonPaidUser(leagueId);
		List<LeagueUserDetails> leagueUserDetails = leagueUserDetailsRepository.loadByContest(userId, leagueId);
		if (!CollectionUtils.isEmpty(leagueUserDetails)) {
			leagueUserDetails.get(0).setNonPaidUsers(listOfNonPaidUsers);
			leagueUserDetails.get(0).setUserInvestementDetails(loadAllUserInvestmentDetails(leagueId));
		}
		return leagueUserDetails;
	}

	public List<String> loadNonPaidUser(int leagueId) {
		List<LeagueUserDetails> leagueUserDetails = leagueUserDetailsRepository.loadNonPaidUsersForLeague(leagueId);
		return leagueUserDetails.parallelStream()
				.map((leagueUserDetail) -> leagueUserDetail.getLeague().getId() + "-"
						+ leagueUserDetail.getMatch().getId() + "-" + leagueUserDetail.getUser().getId())
				.collect(Collectors.toList());
	}

	public Map<String, UserInvestmentDetails> loadAllUserInvestmentDetails(int leagueId) {
		List<LeagueUserDetails> leagueUserDetails = loadAllUserParticipatedGames(leagueId);
		Map<String, UserInvestmentDetails> userInvestementDetails = new HashMap<>();
		Map<String, Integer> winnerCount = new HashMap<String, Integer>();
		Map<String, Integer> investmentCount = new HashMap<String, Integer>();

		leagueUserDetails.forEach(leagueUserDetail -> {
			String key = leagueUserDetail.getLeague().getId() + "-" + leagueUserDetail.getUser().getId();
			String winnerKey = leagueUserDetail.getLeague().getId() + "-" + leagueUserDetail.getWinnerUser().getId();
			
			userInvestementDetails.putIfAbsent(key, new UserInvestmentDetails());
			
			Integer countInvestment = investmentCount.getOrDefault(key, 0);
			investmentCount.put(key, countInvestment + leagueUserDetail.getLeague().getEntryAmt());

			Integer countWinner = winnerCount.getOrDefault(winnerKey, leagueUserDetail.getLeague().getEntryAmt());
			winnerCount.put(winnerKey, countWinner + leagueUserDetail.getLeague().getEntryAmt());
			

		});

		userInvestementDetails.forEach((k,v)->{
			v.setTotalInvestmentAmt(investmentCount.get(k));
			v.setWinningAmt(winnerCount.get(k) != null ? winnerCount.get(k) : 0);
		});
		
		
		

		return userInvestementDetails;
	}

	public List<LeagueUserDetails> loadAllUserParticipatedGames(int leagueId) {
		return leagueUserDetailsRepository.loadUserParticipatedGames(leagueId);
	}

}
