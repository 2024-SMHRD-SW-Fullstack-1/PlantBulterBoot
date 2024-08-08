package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Myplant;
import com.example.demo.repository.MyplantRepository;
import java.util.List;

@Service
public class MyplantService {

    @Autowired
    private MyplantRepository myplantRepository;

    // 모든 식물 가져오기
    public List<Myplant> getAllPlants() {
        return myplantRepository.findAll();
    }

    // 특정 멤버의 모든 식물 가져오기
    public List<Myplant> getPlantsByMemberId(String memberId) {
        return myplantRepository.findByMemberId(memberId);
    }

    // 식물 저장하기
    @Transactional
    public Myplant savePlant(Myplant myPlant) {
        // plantIdx가 0인 경우 그대로 저장하여 외래 키 제약 조건을 준수
        if (myPlant.getPlantIdx() == 0) {
            return myplantRepository.save(myPlant);
        }
        return myplantRepository.save(myPlant);
    }
    
	// 해당 id의 식물
    public long getPlantCount(String id) {
        return myplantRepository.countByMemberId(id);
    }
}
