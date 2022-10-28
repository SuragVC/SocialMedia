package com.media.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Post {
	@Id
	private Integer post_id;
	@NotNull
	@NotBlank
	@Size(min=5,max=100,message = "Content should be between 5 to 100 character")
	private String content;
	@NotBlank
	private String note;
	
	@NotNull
	private Integer user_id;
	
	
}
