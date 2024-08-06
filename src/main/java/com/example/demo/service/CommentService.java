package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	// 해당 post의 댓글 목록 전부 가져오기
	public ArrayList<Comment> getCommentAll(Integer postIdx) {
        return commentRepository.findByPostIdx(postIdx);
    }
	
}
