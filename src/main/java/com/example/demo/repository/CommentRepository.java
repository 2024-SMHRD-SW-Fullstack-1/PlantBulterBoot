package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	ArrayList<Comment> findByPostIdx(Integer postIdx);
	
	void deleteByCommentIdx(Integer commentIdx);
}
