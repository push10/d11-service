package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.League;
import com.example.demo.model.Matches;
import com.example.demo.service.BetService;
import com.example.demo.service.LeagueService;
import com.example.demo.service.MatchService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/matches")
public class MatchController {
	@Autowired
	MatchService matchService;

	@Autowired
	BetService betService;

	@Autowired
	LeagueService leagueService;

	@GetMapping("")
	public List<Matches> list() {
		return matchService.listAllMatches();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Matches> get(@PathVariable Integer id) {
		try {
			Matches match = matchService.getMatch(id);
			return new ResponseEntity<Matches>(match, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Matches>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/loadTodaysMatch")
	public ResponseEntity<List<Matches>> loadTodaysMatch() {
		try {
			LocalDate dateTime = LocalDate.now(ZoneId.of("Asia/Kolkata"));
			List<Matches> matches = matchService.loadByDate(dateTime.atStartOfDay(), dateTime.atTime(LocalTime.MAX));
			League league = leagueService.getLeague(1);
			matches.stream().forEach((match) -> {
				match.setBet(betService.loadByMatch(match, league));
			});
			return new ResponseEntity<List<Matches>>(matches, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Matches>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/loadMatchesByDate/{date}")
	public ResponseEntity<List<Matches>> loadMatchesByDate(@PathVariable String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateTime = LocalDate.parse(date, formatter);
			List<Matches> match = matchService.loadByDate(dateTime.atStartOfDay(), dateTime.atTime(LocalTime.MAX));
			return new ResponseEntity<List<Matches>>(match, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Matches>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/contest/{id}")
	public List<Matches> list(@PathVariable Integer id) {
		return matchService.loadByContest(id).subList(1, 10);
	}

	@PostMapping("/saveMatch")
	public ResponseEntity<Map<String, String>> add(@RequestBody Matches match) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			matchService.saveMatch(match);
			return ResponseEntity.accepted().body(response);
		} catch (NoSuchElementException e) {
			response.put("error", "Match creation fail.");
			return ResponseEntity.badRequest().body(response);
		}

	}
}
