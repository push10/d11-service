package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("")
	public List<User> list() {
		return userService.listAllUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id) {
		try {
			User user = userService.getUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> add(@RequestBody User user) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			userService.saveUser(user);
			response.put("success", "User " + user.getUsername() + " register successfully.");
			return ResponseEntity.accepted().body(response);
		} catch (NoSuchElementException e) {
			response.put("error", "User registration fail.");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
		try {
			User existUser = userService.getUser(id);
			user.setId(id);
			userService.saveUser(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		try {
			List<User> users = userService.loadByUserName(user.getUsername(), user.getPassword());
			if (CollectionUtils.isEmpty(users)) {
				return new ResponseEntity<User>(new User("User not found"), HttpStatus.NOT_FOUND);
			}
			System.out.println("===========> user loaded " + users.get(0).getFirstName());
			return new ResponseEntity<User>(users.get(0), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<User>(new User("User not found"), HttpStatus.NOT_FOUND);
		}

	}
}
