package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_idx")
    private Integer id;

    @Column(name = "myplant_idx")
    private Integer plantId;

    @Column(name = "record_date")
    private LocalDateTime date;

    @Column(name = "record_content")
    private String content;
}
