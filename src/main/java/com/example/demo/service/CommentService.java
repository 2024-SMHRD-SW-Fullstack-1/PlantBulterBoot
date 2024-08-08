package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	// 해당 post의 댓글 목록 전부 가져오기
	public ArrayList<Comment> getCommentAll(Integer postIdx) {
        return commentRepository.findByPostIdx(postIdx);
    }
	
	// 해당 댓글 삭제하기
	@Transactional
	public void deleteCommentByIdx(Integer commentIdx) {
        commentRepository.deleteByCommentIdx(commentIdx);
    }

	public void commentAdd(Comment comment) {
		commentRepository.save(comment);		
	}
	
	// 해당 id의 댓글 수
    public long getCommentCount(String id) {
        return commentRepository.countById(id);
    }
	
}
