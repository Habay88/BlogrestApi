package com.habay.service;

import java.util.List;

import com.habay.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postId, CommentDto commentDto);
	
	List<CommentDto> getComment(long postId);

	//List<CommentDto> getCommentsByPostId(Long postId);
	CommentDto getCommentById(Long postId,Long commentId);
	
	//CommentDto updateComment(Long postId, CommentDto commentRequest);

	CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

   void deleteComment(Long postId, Long commentId);
}
