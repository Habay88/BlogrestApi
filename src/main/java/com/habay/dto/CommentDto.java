package com.habay.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.habay.model.Post;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data

public class CommentDto {

	private long id;
	// name should not be null or empty
	@Email
	@NotEmpty(message="Email should not be null or empty")
	private String email;
	@NotEmpty(message="Name should not be null or empty")
	private String name;
	@NotEmpty
	@Size(min = 10, message = "Comment body must be minimun 10 characters")
	private String body;
}
