package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.User;

public interface UserService {

	public User save(User user);

	public List<User> listAllUsers();

	public User findUserById(Long id);

	public User update(Long id, User user);
}
