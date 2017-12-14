package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> listAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User update(Long id, User user) {
		User u =  userRepository.findOne(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		u.setDescription(user.getDescription());

		
		return userRepository.save(u);
	}
}
