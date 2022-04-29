package com.habay.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.habay.dto.CommentDto;
import com.habay.exception.BlogApiException;
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
	private ModelMapper mapper;
	
	public CommentServiceImpl(CommentRepository crepo,PostRepository prepo,ModelMapper mapper) {
		
		this.crepo = crepo;
		this.prepo= prepo;
		this.mapper=mapper;
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
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		
//		CommentDto commentDto =  new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
	}

	@Override
	public List<CommentDto> getComment(long postId) {
		// retreiave comment by post id
		List<Comment> comments = crepo.findByPostId(postId);
		
		//convert list of comments entities to list of comment dto's
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		
		// retreive post entity by id
		Post post = prepo.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post","id", postId));
		
		// retreive comment by id
		Comment comment = crepo.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comment", "id",commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(Long postId,long commentId, CommentDto commentRequest) {
		// TODO Auto-generated method stub
		
		Post post = prepo.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post","id", postId));
		
		Comment comment = crepo.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comment", "id",commentId));
		
		
		if(!comment.getPost().getId().equals(post.getId())) {
	throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		comment.setName(commentRequest.getName());
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());
		
		Comment updatedComment = crepo.save(comment);
		return mapToDto(updatedComment);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		
		Post post = prepo.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post","id", postId));
		
		Comment comment = crepo.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comment", "id",commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
				}
		crepo.delete(comment);
		
	}
	
}
