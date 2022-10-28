package com.media.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserManager {
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer managerId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User", insertable = false, updatable = false)
	private User user;
	
	@OneToMany
	@JsonIgnore
	private List<Post> post;
	
}
