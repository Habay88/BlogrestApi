package com.habay.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.habay.dto.PostDto;
import com.habay.dto.PostResponse;
import com.habay.exception.ResourceNotFoundException;
import com.habay.model.Post;
import com.habay.repository.PostRepository;
import com.habay.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	
	private PostRepository prepo;
	
	
	public PostServiceImpl(PostRepository prepo) {
		this.prepo = prepo;
	}


//	@Override
//	public PostDto createPost(PostDto postDto) {
//		
//		Post post = mapToEntity(postDto);
//		Post newPost = prepo.save(post);
//		
//		//convert entity to dto
//	//	PostDto postResponse = new mapToDto(newPost);
//		
//		return postResponse;
//	}
	 @Override
	    public PostDto createPost(PostDto postDto) {

	        // convert DTO to entity
	        Post post = mapToEntity(postDto);
	        Post newPost = prepo.save(post);

	        // convert entity to DTO
	        PostDto postResponse = mapToDto(newPost);
	        return postResponse;
	    }


	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {
	
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
			: Sort.by(sortBy).descending();	
		
		// CREATE PAGEABLE INSTANCE
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts = prepo.findAll(pageable);
		
		//get content from page object
		List<Post> listOfPosts = posts.getContent();
	
	//	List<Post> posts = prepo.findAll();
	    List<PostDto> content =	listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;
	}
	// convert entity to dto
   private PostDto mapToDto(Post post) {
   PostDto postDto = new PostDto();
   postDto.setId(post.getId());
   postDto.setTitle(post.getTitle());
   postDto.setDescription(post.getDescription());
   postDto.setContent(post.getContent());
   return postDto;
}
   
   // convert dto to entity
   private Post mapToEntity(PostDto postDto) {
	   Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
		
   }


@Override
public PostDto getPostById(Long id) {
	Post post = prepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
	return mapToDto(post);
}


@Override
public PostDto updatePost(PostDto postDto, Long id) {
	// get post by id from db a
	Post post = prepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
	
	post.setTitle(postDto.getTitle());
	post.setDescription(postDto.getDescription());
	post.setContent(postDto.getContent());
	
	Post updatedPost = prepo.save(post);
	return mapToDto(updatedPost);
}

@Override
public void deletePostById(long id) {
    // get post by id from the database
    Post post = prepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    prepo.delete(post);
}


}
