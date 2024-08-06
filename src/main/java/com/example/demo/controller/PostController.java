package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostController {
	
	@Autowired
	private PostService service;
	
	@PostMapping("/postlist")
	public ArrayList<Post> postlist(HttpServletRequest request) {
		
		ArrayList<Post> postList = service.getAllPost();
		
		System.out.println(postList);
		
		return postList;
	}
}
