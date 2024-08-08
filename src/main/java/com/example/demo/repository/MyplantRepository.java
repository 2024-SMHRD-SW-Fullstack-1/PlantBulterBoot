package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Myplant;
import java.util.List;

public interface MyplantRepository extends JpaRepository<Myplant, Integer> {
    List<Myplant> findByMemberId(String memberId);
    
	// 해당 id의 식물 수
	long countByMemberId(String id);
    
}
