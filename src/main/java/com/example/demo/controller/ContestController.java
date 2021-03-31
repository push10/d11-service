package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contest;
import com.example.demo.model.User;
import com.example.demo.service.ContestService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/contests")
public class ContestController {
	@Autowired
	ContestService contestService;

	@GetMapping("/listAllContest")
	public List<Contest> list() {
		return contestService.listAllContest();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contest> get(@PathVariable Integer id) {
		try {
			Contest contest = contestService.getContest(id);
			return new ResponseEntity<Contest>(contest, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Contest>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> add(@RequestBody Contest contest) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			contestService.saveContest(contest);
			response.put("success", "Contest " + contest.getName() + " register successfully.");
			return ResponseEntity.accepted().body(response);
		} catch (NoSuchElementException e) {
			response.put("error", "Contest registration fail.");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Contest contest, @PathVariable Integer id) {
		try {
			contestService.saveContest(contest);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		contestService.deleteContest(id);
	}


}
