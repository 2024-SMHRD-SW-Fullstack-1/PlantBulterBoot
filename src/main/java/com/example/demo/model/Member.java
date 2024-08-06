package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="member")
@Data
public class Member {
   
   @Id
   @Column(name="mem_id",length=50)
   //@JsonProperty("mem_id")
   private String id;
   
   //@JsonProperty("mem_pw")
   @Column(name="mem_pw",length=50)
   private String pw;
   
   @Column(name="mem_nick",length=50)
   private String nick;
   
   @Column(name="mem_img",length=1000)
   private String img;

}