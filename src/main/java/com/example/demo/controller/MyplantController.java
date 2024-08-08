package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Myplant;
import com.example.demo.service.MyplantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class MyplantController {

    private static final Logger logger = LoggerFactory.getLogger(MyplantController.class);

    @Autowired
    private MyplantService myplantService;

    // 모든 식물 가져오기
    @GetMapping
    public List<Myplant> getAllPlants() {
        return myplantService.getAllPlants();
    }

    // 특정 멤버의 모든 식물 가져오기
    @GetMapping("/{memberId}")
    public List<Myplant> getPlantsByMemberId(@PathVariable String memberId) {
        return myplantService.getPlantsByMemberId(memberId);
    }

    // 식물 추가
    @PostMapping
    public Myplant createPlant(@RequestBody Myplant myplant) {
        logger.info("Received plant data: {}", myplant);
        return myplantService.savePlant(myplant);
    }
}
