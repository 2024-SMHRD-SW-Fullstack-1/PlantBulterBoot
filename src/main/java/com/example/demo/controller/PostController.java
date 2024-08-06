package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.codec.Base64Codec;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post")
	public ArrayList<Post> postList(HttpServletRequest request) throws IOException {
		
		ArrayList<Post> postList = postService.getAllPost();
		
		for(Post p : postList) {
			if (p.getImg() != null) {
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + p.getImg() + ".jpg");
				p.setImg(img);
			}
		}
		
		System.out.println(postList);
		
		return postList;
	}
	
	@GetMapping("/post/{idx}")
	public ArrayList<Comment> commentList(@PathVariable("idx") int idx) {
		System.out.println("해당 post의 idx : " + idx);
		
		ArrayList<Comment> commentList = commentService.getCommentAll(idx);
		System.out.println(commentList);
		
		return commentList;
	}
	
	@PostMapping("/post/add")
	public String postAdd(HttpServletRequest request) throws IOException {
		String param = request.getParameter("addPost");	
		ObjectMapper om = new ObjectMapper();
		Post post = om.readValue(param, Post.class);
		
		if (post.getImg() != null) {
			String img = Base64Codec.makeFileWithString(post.getImg(), UUID.randomUUID());
			post.setImg(img);
		}
		
		postService.postAdd(post);
		
		return "게시글 추가 완료";
	}
}
