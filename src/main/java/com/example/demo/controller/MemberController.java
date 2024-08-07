package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.codec.Base64Codec;
import com.example.demo.model.Member;
import com.example.demo.model.Post;
import com.example.demo.service.MemberService;
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
   
// 회원정보 수정
   // 프로필 사진 서버로 가져오기
   @PostMapping("/mypage/image")
   public String storeImager(HttpServletRequest request) throws IOException {
      
      String image = request.getParameter("image");
      System.out.println("변경된 프로필 사진: "+ image);
      Base64Codec.makeFileWithString(image, UUID.randomUUID());
      
      return "save profileImg";
   }
   
}
