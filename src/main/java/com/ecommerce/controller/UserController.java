package com.ecommerce.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * This function returns list of all users.
	 * 
	 * @return List<Resource<User>>
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Resource<User>> getAllUsers() {
		List<User> users = userService.listAllUsers();

		List<Resource<User>> resources = new ArrayList<Resource<User>>();

		for (User user : users) {
			resources.add(getResource(user));
		}

		return resources;
	}

	/**
	 * 
	 * @param user
	 * @return User
	 */
	@PostMapping("/users")
	public User saveUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}

	/**
	 * 
	 * @param userId
	 * @return User
	 */

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Resource<User> getSingleUser(@PathVariable(value = "id") Long userId) {
		User user = userService.findUserById(userId);

		return getResource(user);
	}

	private Resource<User> getResource(User user) {
		Resource<User> resource = new Resource<User>(user);
		resource.add(linkTo(methodOn(UserController.class).getSingleUser(user.getId())).withSelfRel());
		return resource;
	}

	/**
	 * 
	 * @param id
	 * @param user
	 * @return User
	 */
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
		return userService.update(id, user);
	}

}
