package com.habay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habay.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
