package com.mifel.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifel.demo.UserService;
import com.mifel.demo.model.User;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@GetMapping("{id}")
	public User searchUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 userService.deleteUser(id);
	}
}