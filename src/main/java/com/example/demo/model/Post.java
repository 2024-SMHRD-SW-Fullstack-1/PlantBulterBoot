package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
	
    @ManyToOne
    @JoinColumn(name = "mem_id", referencedColumnName = "mem_id", insertable = false, updatable = false)
    private Member member;
	
	@Column(name = "post_img", length = 1000)
	private String img;
	
	@Column(name = "post_content")
	private String content;
	
    @Column(name = "post_views", columnDefinition = "int default 0")
    private Integer views;
    
    @Column(name = "post_title", length = 50)
    private String title;
    
    @Column(name = "post_date")
    private LocalDateTime date;

    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
        if (this.views == null) {
            this.views = 0;
        }
    }

}
