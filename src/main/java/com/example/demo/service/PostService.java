package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	// 게시글 모두 가져오기
	public ArrayList<Post> getAllPost() {
        ArrayList<Post> postList = postRepository.findAllByOrderByIdxDesc();
        
        return  postList;
    }
	
	// 게시글 추가하기
	public void postAdd(Post post) {
		postRepository.save(post);
	}
	
	// 내 게시글 가져오기
	public ArrayList<Post> getMyPost(String id){
	      
		ArrayList<Post> postList = postRepository.findAllByMemberId(id);
	      
		return postList;
	}
	
	// 게시글 삭제하기
	@Transactional
	public void deleteByIdx(Integer idx) {
		postRepository.deleteByIdx(idx);
	}
	
	// 게시글 조회수 증가
	@Transactional
    public void incrementViews(int idx) {
        Optional<Post> optionalPost = postRepository.findById(idx);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        }
    }
	
	// 검색한 게시글 리스트
	public ArrayList<Post> searchPosts(String query) {
        return postRepository.findByTitleContainingOrContentContaining(query, query);
    }

}
