package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.League;
import com.example.demo.service.LeagueService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/leagues")
public class LeagueController {
	@Autowired
	LeagueService leagueService;

	@GetMapping("")
	public List<League> list() {
		return leagueService.listAllLeague();
	}

	@GetMapping("/{id}")
	public ResponseEntity<League> get(@PathVariable Integer id) {
		try {
			League league = leagueService.getLeague(id);
			return new ResponseEntity<League>(league, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<League>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/saveLeague")
	public ResponseEntity<Map<String, String>> add(@RequestBody League league) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			leagueService.saveLeague(league);
			return ResponseEntity.accepted().body(response);
		} catch (NoSuchElementException e) {
			response.put("error", "League creation fail.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping("/joinContest/{userId}")
	public ResponseEntity<HttpStatus> joinLeague(@PathVariable Integer userId, @RequestBody League league) {
		try {
			leagueService.joinLeague(userId, league.getJoiningCode());
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
