package com.habay.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {

	private Long Id;
	// title should not be null or empty
	// title should have at least 2 characters
	@NotEmpty
	@Size(min=2, message = "Post title should have at least 2 characters")
	private String title;
	// post should not be null or empty
		// post should have at least 10 characters
	@NotEmpty
	@Size(min=10, message = "Post title should have at least 10 characters")
	private String description;
	@NotEmpty
	private String content;
	
	private Set<CommentDto> comments;
}
