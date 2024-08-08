package com.example.demo.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mypage {
	private ArrayList<Post> postList;
	private long cntPlant;
	private long cntPost;
	private long cntComment;
}
