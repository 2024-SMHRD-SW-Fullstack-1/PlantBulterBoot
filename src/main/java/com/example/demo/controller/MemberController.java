package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.codec.Base64Codec;
import com.example.demo.model.Member;
import com.example.demo.model.Mypage;
import com.example.demo.model.Post;
import com.example.demo.service.CommentService;
import com.example.demo.service.MemberService;
import com.example.demo.service.MyplantService;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class MemberController {
   
   @Autowired
   private MemberService service;
   
   @Autowired
   private PostService postService;
   
   @Autowired
   private CommentService commentService;
   
   @Autowired
   private MyplantService plantService;
   
   // 회원가입
   @PostMapping("/join")
   public String join(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
      String param = request.getParameter("Member");
      
      ObjectMapper om = new ObjectMapper();
      Member pm = om.readValue(param, Member.class);
      
      service.join(pm);
      
      return "OK";
   }
   
   // 로그인
   @PostMapping("/login")
   public Member login(HttpServletRequest request) throws IOException {
      String param = request.getParameter("Member");
      
      ObjectMapper om = new ObjectMapper();
      Member pm = om.readValue(param, Member.class);
      
      Member result = service.login(pm);
      result.setImg(Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + result.getImg() + ".jpg"));
      System.out.println("로그인한 멤버 : " + result);
      
      return result;
   }
   
   // record 가져오기
   @PostMapping("/mypage")
   public Mypage myPage(HttpServletRequest request) throws IOException {
     String id = request.getParameter("Member").replace("\"", "");
      
     
      ArrayList<Post> postList = postService.getMyPost(id);
		for(Post p : postList) {
			if (p.getImg() != null) {
				String postImg = p.getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + postImg + ".jpg");
				p.setImg(img);
			}else if (p.getImg() == null) {
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/noPostImg.png");
				p.setImg(img);
			}
			
			if (p.getMember().getImg() != null && p.getMember().getImg().length() < 60) {
				String memImg = p.getMember().getImg();
				String img = Base64Codec.makeStringWithFile("src/main/resources/static/upload/" + memImg + ".jpg");
				p.getMember().setImg(img);
			}
		}
      System.out.println("mypage 리스트 : "+postList);
      
       long cntPlant = plantService.getPlantCount(id);
	   long cntPost = postService.getPostCount(id);
	   long cntComment = commentService.getCommentCount(id);
	   
	   Mypage mypage = new Mypage(postList, cntPlant, cntPost, cntComment);
      
     return mypage;
   }
   
   // 회원정보 수정
   // 프로필 사진 변경
   @PostMapping("/mypage/profilechange")
   public String storeImager(HttpServletRequest request) throws IOException {
      
      String param = request.getParameter("Member");
      ObjectMapper om = new ObjectMapper();
      String img = null;
      if(om.readTree(param).get("img") != null) {
         img = Base64Codec.makeFileWithString(om.readTree(param).get("img").asText(), UUID.randomUUID());
      }
      Member pm = om.readValue(param, Member.class);
      pm.setImg(img);
      
      service.editImg(pm);
      
      return "save profileImg";
   }
   // 닉네임 변경
   @PostMapping("/mypage/nick")
   public void changeNick(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
      String param = request.getParameter("Member");
      
      ObjectMapper om = new ObjectMapper();
      
      Member pm = om.readValue(param, Member.class);
      service.changeNick(pm);      
   }
   // 비밀번호 변경
   @PostMapping("/mypage/pw")
   public void changePw(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
      String param = request.getParameter("Member");
      
      ObjectMapper om = new ObjectMapper();
      
      Member pm = om.readValue(param, Member.class);
      service.changePw(pm);
      
   }
   // 회원탈퇴
   @PostMapping("/mypage/delete")
   public void deleteMem(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
      String param = request.getParameter("Member");
      
      ObjectMapper om = new ObjectMapper();
      
      Member pm = om.readValue(param, Member.class);
      service.deleteMem(pm);
      
   }
   
}
