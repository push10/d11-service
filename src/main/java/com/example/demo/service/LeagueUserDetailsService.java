package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LeagueUserDetails;
import com.example.demo.repository.LeagueUserDetailsRepository;

@Service
@Transactional
public class LeagueUserDetailsService {
	@Autowired
	private LeagueUserDetailsRepository leagueUserDetailsRepository;

	

	public List<LeagueUserDetails> loadLeagueUserDetails(int userId, int leagueId) {
		return leagueUserDetailsRepository.loadByContest(userId, leagueId);
	}

}
