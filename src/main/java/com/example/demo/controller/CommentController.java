package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.model.Member;
import com.example.demo.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/delete")
	public String commentDel(HttpServletRequest request) {
		Integer commentIdx = Integer.parseInt(request.getParameter("delComment"));
		
		commentService.deleteCommentByIdx(commentIdx);
		
		return "댓글 삭제 완료";
	}
	
	@PostMapping("/comment/add")
	public String commentAdd(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String param = request.getParameter("addComment");	
		System.out.println("addComment : " + param);
		
	    ObjectMapper om = new ObjectMapper();
	    Comment comment = om.readValue(param, Comment.class);
		
		commentService.commentAdd(comment);
		
		//commentService.deleteCommentByIdx(commentIdx);
		
		return "댓글 등록 완료";
	}
}
