package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

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

}
