package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.codec.Base64Codec;
import com.example.demo.model.Comment;
import com.example.demo.model.Member;
import com.example.demo.model.Post;
import com.example.demo.service.CommentService;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/post")
	public ArrayList<Post> postList(HttpServletRequest request) throws IOException {
		
		ArrayList<Post> postList = postService.getAllPost();
		
		for(Post p : postList) {
			if (p.getImg() != null) {
				String postImg = p.getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + postImg + ".jpg");
				p.setImg(img);
			}
			
			if (p.getMember().getImg() != null && p.getMember().getImg().length() < 60) {
				String memImg = p.getMember().getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + memImg + ".jpg");
				p.getMember().setImg(img);
			}
		}
		
		System.out.println("post리스트들 : "+postList);
		
		return postList;
	}
	
	@GetMapping("/post/{idx}")
	public ArrayList<Comment> commentList(@PathVariable("idx") int idx) throws IOException {
		System.out.println("해당 post의 idx : " + idx);
		
		ArrayList<Comment> commentList = commentService.getCommentAll(idx);
		
		for(Comment c : commentList) {			
			if (c.getMember().getImg() != null && c.getMember().getImg().length() < 60) {
				String memImg = c.getMember().getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + memImg + ".jpg");
				c.getMember().setImg(img);
			}
		}
		
		
		return commentList;
	}
	
	   @PostMapping("/post/add")
	   public Post postAdd(HttpServletRequest request) throws IOException {
	      String param = request.getParameter("addPost");   
	      ObjectMapper om = new ObjectMapper();
	      
	      String img = null;
	      
	      if (om.readTree(param).get("img") != null) {
	         img = Base64Codec.makeFileWithString(om.readTree(param).get("img").asText(), UUID.randomUUID());
	      }
	        String id = om.readTree(param).get("id").asText();
	        String title = om.readTree(param).get("title").asText();
	        String content = om.readTree(param).get("content").asText();
	        
	        Post post = new Post();

	        post.setId(id);
	        post.setImg(img);
	        post.setContent(content);
	        post.setTitle(title);
	        
	        Member member = memberService.findById(id);
	        post.setMember(member);
	        
	        Post result = postService.postAdd(post);
	        
	        System.out.println("추가한 결과 : " + result);
	        
			if (result.getImg() != null) {
				String postImg = result.getImg();
				String img64 = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + postImg + ".jpg");
				result.setImg(img64);
			}
			
			if (result.getMember().getImg() != null && result.getMember().getImg().length() < 60) {
				String memImg = result.getMember().getImg();
				String img64 = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + memImg + ".jpg");
				result.getMember().setImg(img64);
			}
	      
	      return result;
	   }
	
	@PostMapping("/post/update")
	public Post postUpdate(HttpServletRequest request) throws IOException {
		String param = request.getParameter("updatePost");	
		ObjectMapper om = new ObjectMapper();
		
		String img = null;
		
		if (om.readTree(param).get("img") != null) {
			img = Base64Codec.makeFileWithString(om.readTree(param).get("img").asText(), UUID.randomUUID());
		}
		int idx = Integer.parseInt(om.readTree(param).get("idx").asText());
        String id = om.readTree(param).get("id").asText();
        String title = om.readTree(param).get("title").asText();
        String content = om.readTree(param).get("content").asText();
        
        Post post = new Post();
        
        post.setIdx(idx);
        post.setId(id);
        post.setImg(img);
        post.setContent(content);
        post.setTitle(title);
        post.setDate(LocalDateTime.now());
        
        Post result = postService.updatePost(post);
        
		if (result.getImg() != null) {
			String postImg = result.getImg();
			String img64 = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + postImg + ".jpg");
			result.setImg(img64);
		}
		
		if (result.getMember().getImg() != null && result.getMember().getImg().length() < 60) {
			String memImg = result.getMember().getImg();
			String img64 = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + memImg + ".jpg");
			result.getMember().setImg(img64);
		}
        
        System.out.println("수정한 결과 : " + result);
		
		return result;
	}
	
	@GetMapping("/post/delete/{idx}")
	public String postDel(@PathVariable("idx") int idx) {
		System.out.println("삭제하려는 게시글 idx : " + idx);
		
		postService.deleteByIdx(idx);
		
		return "게시글 삭제 완료";
	}
	
	// 게시글 조회수
	@GetMapping("/increViews/{idx}")
	public void increViews(@PathVariable("idx") int idx) {
		System.out.println("클릭한 게시글의 idx : " + idx);
		postService.incrementViews(idx);
	}
	
	// 검색 기능
	@GetMapping("/search/{query}")
	public ArrayList<Post> search(@PathVariable("query") String query) throws IOException {
		ArrayList<Post> postList = postService.searchPosts(query);
		
		for(Post p : postList) {
			if (p.getImg() != null) {
				String postImg = p.getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + postImg + ".jpg");
				p.setImg(img);
			}
			
			if (p.getMember().getImg() != null && p.getMember().getImg().length() < 60) {
				String memImg = p.getMember().getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + memImg + ".jpg");
				p.getMember().setImg(img);
			}
		}
		
		System.out.println("검색한 게시글들 : " + postList);

		return postList;
	}
	
}
