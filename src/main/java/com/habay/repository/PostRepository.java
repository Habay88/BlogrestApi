package com.habay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habay.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
