package com.habay.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PostDto {

	private Long Id;
	
	private String title;
	
	private String description;
	
	private String content;
	
	private Set<CommentDto> comments;
}
