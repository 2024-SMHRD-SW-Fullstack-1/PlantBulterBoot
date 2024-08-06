package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="post")
@Data
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_idx")
	private Integer idx;
	
	@Column(name = "mem_id", length = 50)
	private String id;
	
	@Column(name = "post_img", length = 1000)
	private String img;
	
	@Column(name = "post_content")
	private String content;
	
	@Column(name = "post_views")
	private Integer views;
	
	@Column(name = "post_title", length = 50)
	private String title;
	
	@Column(name = "post_date")
	private LocalDateTime date;
	

}
