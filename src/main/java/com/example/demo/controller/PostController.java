package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PostController {
	
	@PostMapping("/postlist")
	public String postlist(HttpServletRequest request) {
		
		
		return "ok";
	}
}
