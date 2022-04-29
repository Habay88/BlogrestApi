package com.habay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habay.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

	List<Comment> findByPostId(long postId);

}
