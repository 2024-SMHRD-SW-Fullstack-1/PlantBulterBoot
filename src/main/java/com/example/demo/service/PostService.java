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

}
