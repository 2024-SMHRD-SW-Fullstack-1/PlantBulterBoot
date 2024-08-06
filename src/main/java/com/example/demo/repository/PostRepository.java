package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	// 모든 게시글 가져오기
	ArrayList<Post> findAllByOrderByIdxDesc();
	
	// 내 게시글
	ArrayList<Post> findAllByMemberId(String memberId);
	
}
