package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="comment")
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_idx")
	private Integer commentIdx;
	
	@Column(name = "post_idx")
	private Integer postIdx;
	
	@Column(name = "mem_id")
	private String id;
	
	@Column(name = "comment_content")
	private String content;
}
