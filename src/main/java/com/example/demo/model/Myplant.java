package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "my_plant")
@Data
public class Myplant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myplant_idx")
    private Integer myplantIdx; // 이름을 myplantIdx로 변경

    @Column(name = "mem_id", length = 50)
    private String memberId;

    @Column(name = "plant_idx")
    private Integer plantIdx;

    @Column(name = "myplant_nick")
    private String nickname;

    @Column(name = "myplant_start")
    private String startDate;

    @Column(name = "myplant_goal")
    private String goal;
}
