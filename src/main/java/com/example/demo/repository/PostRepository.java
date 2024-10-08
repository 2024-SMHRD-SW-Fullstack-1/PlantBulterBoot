package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	// 모든 게시글 가져오기
	ArrayList<Post> findAllByOrderByIdxDesc();
	
	// 내 게시글
	ArrayList<Post> findAllByMemberId(String memberId);
	
	// 추가한 게시글
    Post findTopByIdOrderByIdxDesc(String id);
	
	// 게시글 삭제하기
	void deleteByIdx(Integer idx);
	
	// 해당 idx의 게시글
	Optional<Post> findByIdx(Integer id);
	
	// 검색한 게시글 리스트
	ArrayList<Post> findByTitleContainingOrContentContaining(String query1, String query2);
	
	// 해당 id의 게시글 수
	long countById(String id);
	
}
