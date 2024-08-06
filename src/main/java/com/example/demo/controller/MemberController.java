package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.Post;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
public class MemberController {
   
   @Autowired
   private MemberService service;
   
   @Autowired
   private PostService postService;
   
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
   public Member login(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
      String param = request.getParameter("Member");
      
      ObjectMapper om = new ObjectMapper();
      Member pm = om.readValue(param, Member.class);
      
      Member result = service.login(pm);
      
      System.out.println(result);
      
      return result;
   }
   
   // record 가져오기
   @PostMapping("/mypage")
   public ArrayList<Post> myPage(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
	  String id = request.getParameter("Member").replace("\"", "");
	   
	  
      ArrayList<Post> postList = postService.getMyPost(id);
      System.out.println("mypage 리스트 : "+postList);
      
	  return postList;
   }
   
}
