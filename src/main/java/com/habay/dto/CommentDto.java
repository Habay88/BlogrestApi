package com.habay.dto;

import com.habay.model.Post;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class CommentDto {

	private Long id;
	private String email;
	
	private String name;
	
	private String body;
}
