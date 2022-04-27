package com.habay.impl;

import org.springframework.stereotype.Service;

import com.habay.dto.CommentDto;
import com.habay.exception.ResourceNotFoundException;
import com.habay.model.Comment;
import com.habay.model.Post;
import com.habay.repository.CommentRepository;
import com.habay.repository.PostRepository;
import com.habay.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository crepo;
	private PostRepository prepo;
	
	public CommentServiceImpl(CommentRepository crepo,PostRepository prepo) {
		
		this.crepo = crepo;
		this.prepo= prepo;
	}

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		
		Comment comment = mapToEntity(commentDto);
		
		// retreive post entity by id
		Post post = prepo.findById(postId).orElseThrow(
		() -> new ResourceNotFoundException("Post","id", postId));
		
		// set post to comment entity
		comment.setPost(post);
		
		// comment entity to db
		Comment newComment = crepo.save(comment);
		return mapToDto(newComment);
	}

	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto =  new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return comment;
	}
	
}
