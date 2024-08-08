package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Plant;
import com.example.demo.service.PlantCatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/plant_catalog")
public class PlantCatalogController {

    @Autowired
    private PlantCatalogService plantCatalogService;

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantCatalogService.getAllPlants();
    }

    @PostMapping
    public Plant createPlant(@RequestBody Plant plant) {
        return plantCatalogService.savePlant(plant);
    }
}
