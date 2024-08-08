package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class MemberController {
   
   @Autowired
   private MemberService service;
   
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
      
      return result;
   }
   
   

}
