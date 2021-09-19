package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Matches;
import com.example.demo.service.BetService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/bet")
public class BetController {
	@Autowired
	BetService betService;
	
	

	@GetMapping("/loadMatchesByDate/{date}")
	public ResponseEntity<List<Matches>> loadMatchesByDate(@PathVariable String date) {
//		try {
////			betService
//			return new ResponseEntity<List<Matches>>(match, HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			return new ResponseEntity<List<Matches> >(HttpStatus.NOT_FOUND);
//		}
		return null;
	}

	@PostMapping("/saveMatch")
	public ResponseEntity<Map<String, String>> add(@RequestBody Matches match) {
//		Map<String, String> response = new HashMap<String, String>();
//		try {
//			matchService.saveMatch(match); 
//			return ResponseEntity.accepted().body(response);
//		} catch (NoSuchElementException e) {
//			response.put("error", "Match creation fail.");
//			return ResponseEntity.badRequest().body(response);
//		}
		return null;

	}
}
