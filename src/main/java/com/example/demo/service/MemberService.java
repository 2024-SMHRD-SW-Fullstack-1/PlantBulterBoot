package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Member;
import com.example.demo.model.Post;
import com.example.demo.repository.MemberRepository;

import jakarta.transaction.Transactional;

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
   // 닉네임 변경
   @Transactional
   public void changeNick(Member pm) {
      Optional<Member> m = repository.findById(pm.getId());
      
      if (m.isPresent()) {
         Member result = m.get();
         result.setNick(pm.getNick());
           repository.save(result);
       }           
   }
   // 비밀번호 변경
   @Transactional
   public void changePw(Member pm) {
      Optional<Member> m = repository.findById(pm.getId());
      
      if (m.isPresent()) {
         Member result = m.get();
         result.setPw(pm.getPw());
           repository.save(result);
       }           
   }
   // 회원 탈퇴
   @Transactional
   public void deleteMem(Member pm) {
      repository.deleteById(pm.getId());
   }
   
   // 해당 id의 멤버 값 가져오기
   public Member findById(String id) {
	   Optional<Member> opMem = repository.findById(id);
	   Member mem = null;
	      if (opMem.isPresent()) {
	          mem = opMem.get();
	        } 
   
	   return mem;
   }
   

}