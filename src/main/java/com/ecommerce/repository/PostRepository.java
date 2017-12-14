package com.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
