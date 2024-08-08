package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Plant;

public interface PlantCatalogRepository extends JpaRepository<Plant, Integer> {
}
