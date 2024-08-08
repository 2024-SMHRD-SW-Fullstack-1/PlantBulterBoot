package com.example.demo.service;

import com.example.demo.model.Plant;
import com.example.demo.repository.PlantCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantCatalogService {

    @Autowired
    private PlantCatalogRepository plantCatalogRepository;

    public List<Plant> getAllPlants() {
        return plantCatalogRepository.findAll();
    }

    public Plant savePlant(Plant plant) {
        return plantCatalogRepository.save(plant);
    }
}
