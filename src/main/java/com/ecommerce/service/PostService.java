package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Post;

public interface PostService {
	
	public List<Post> getAllPosts();

	public Post save(Post post);

	public Post findPostById(Long id);

}
