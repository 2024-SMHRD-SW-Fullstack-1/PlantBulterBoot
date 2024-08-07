package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Member;
import com.example.demo.model.Post;

public interface MemberRepository extends JpaRepository<Member, String>{
   
   // 로그인
   Member findByIdAndPw(String id,String pw);
   
}