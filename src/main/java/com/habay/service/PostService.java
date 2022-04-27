package com.habay.service;

import java.util.List;

import com.habay.dto.PostDto;
import com.habay.dto.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto postdto);

	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

	PostDto getPostById(Long id);

	PostDto updatePost(PostDto postDto, Long id);

	void deletePostById(long id);

	
}
