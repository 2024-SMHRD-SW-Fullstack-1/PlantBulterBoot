package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Member;
import com.example.demo.model.Post;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
   
   @Autowired
   private MemberRepository repository;
   
   // 회원가입
   public void join(Member pm) {
      repository.save(pm);
   }
   // 로그인
   public Member login(Member pm) {
      
      return repository.findByIdAndPw(pm.getId(),pm.getPw());
   }

}