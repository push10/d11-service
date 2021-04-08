package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LeagueUserDetails;
import com.example.demo.service.LeagueUserDetailsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/leagueUserDetails")
public class LeagueUsersDetailsController {

	@Autowired
	LeagueUserDetailsService leagueUserDetailsService;

	@GetMapping("/{userId}/{leagueId}")
	public List<LeagueUserDetails> list(@PathVariable int userId, @PathVariable int leagueId) {
		return leagueUserDetailsService.loadLeagueUserDetails(userId, leagueId);
	}
	
	public List<String> listNonPaidUsers( @PathVariable int leagueId) {
		return leagueUserDetailsService.loadNonPaidUser(leagueId);
	}

}
