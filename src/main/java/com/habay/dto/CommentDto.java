package com.habay.dto;

import com.habay.model.Post;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data

public class CommentDto {

	private long id;
	private String email;
	
	private String name;
	
	private String body;
}
