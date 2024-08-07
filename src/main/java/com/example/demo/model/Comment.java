package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
    @ManyToOne
    @JoinColumn(name = "mem_id", referencedColumnName = "mem_id", insertable = false, updatable = false)
    private Member member;
    
    @Column(name = "mem_id", length = 50)
    private String id;
	
	@Column(name = "comment_content")
	private String content;
}
