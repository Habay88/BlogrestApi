package com.habay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habay.dto.CommentDto;
import com.habay.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId")long postId,@RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
	}
	
}
