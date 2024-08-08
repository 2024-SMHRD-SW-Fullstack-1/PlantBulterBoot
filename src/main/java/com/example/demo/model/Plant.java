package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plant")
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_idx")
    private Integer id;

    @Column(name = "plant_name")
    private String name;

    @Column(name = "plant_img")
    private String imageUrl;

    @Column(name = "plant_content")
    private String content;
}
