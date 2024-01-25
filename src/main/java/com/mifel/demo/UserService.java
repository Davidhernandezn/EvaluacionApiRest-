package com.mifel.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mifel.demo.model.User;
import com.mifel.demo.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(Long id){
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.get();
	}

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
