package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Post;
import com.ecommerce.model.User;
import com.ecommerce.repository.PostRepository;
import com.ecommerce.service.PostService;
import com.ecommerce.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<Post> getAllPosts() {
		return (List<Post>) postRepository.findAll();
	}

	@Override
	public Post save(Post post) {
		User author = userService.findUserById(post.getAuthor().getId());
		post.setAuthor(author);
		return postRepository.save(post);
	}

	@Override
	public Post findPostById(Long id) {
		return postRepository.findOne(id);
	}

}
