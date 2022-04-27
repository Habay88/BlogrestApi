package com.habay.service;

import com.habay.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postId, CommentDto commentDto);
}
