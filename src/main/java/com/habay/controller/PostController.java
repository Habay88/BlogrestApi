package com.habay.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habay.dto.PostDto;
import com.habay.dto.PostResponse;
import com.habay.service.PostService;
import com.habay.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	
	private PostService postservice;

	public PostController(PostService postservice) {
		this.postservice = postservice;
	}
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<>(postservice.createPost(postDto),HttpStatus.CREATED);
	}
//	without pagination
//	@GetMapping
//	public List<PostDto> getAllPosts(){
//		return postservice.getAllPosts();
//	}
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo", defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false)int pageNo,
			@RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE, required=false) int pageSize,
			 @RequestParam(value="sortBy", defaultValue=AppConstants.DEFAULT_SORT_BY,required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIRECTION, required = false)String sortDir 
			){
		   
		return postservice.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id")Long id){
		return ResponseEntity.ok(postservice.getPostById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id")Long id){
		PostDto postResponse = postservice.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postservice.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
