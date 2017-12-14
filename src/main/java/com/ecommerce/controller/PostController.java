package com.ecommerce.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Post;
import com.ecommerce.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Resource<Post>> getAllPosts() {
		List<Post> posts = postService.getAllPosts();

		List<Resource<Post>> resources = new ArrayList<>();

		for (Post post : posts) {
			resources.add(getResource(post));
		}

		return resources;
	}

	@PostMapping("/posts")
	public Post save(@Valid @RequestBody Post post) {
		return postService.save(post);
	}

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Resource<Post> getPostById(@PathVariable("id") Long postId) {
		Post post = postService.findPostById(postId);

		return getResource(post);
	}

	private Resource<Post> getResource(Post post) {
		Resource<Post> resource = new Resource<Post>(post);

		resource.add(linkTo(methodOn(PostController.class).getPostById(post.getId())).withSelfRel());
		resource.add(linkTo(methodOn(UserController.class).getSingleUser(post.getAuthor().getId())).withRel("author"));
		
		return resource;
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> update(@PathVariable("id") Long id, @Valid @RequestBody Post updatedPost) {
		Post post = postService.findPostById(id);

		if (post == null) {
			return ResponseEntity.notFound().build();
		}

		updatedPost.setId(id);
		Post newPost = postService.save(updatedPost);
		return ResponseEntity.ok(newPost);
	}

}
