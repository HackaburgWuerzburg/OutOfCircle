package com.example.application.controllers;

import com.example.application.dto.GardenInput;
import com.example.domain.models.Garden;
import com.example.infrastructure.mapper.GardenMapper;
import com.example.infrastructure.persistence.service.GardenServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gardens")
public class GardenController {

    private final GardenServiceImpl gardenService;
    private final GardenMapper gardenMapper;

    public GardenController(GardenServiceImpl gardenService, GardenMapper gardenMapper) {
        this.gardenService = gardenService;
        this.gardenMapper = gardenMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garden> getGardenById(@PathVariable Long id) {
        return gardenService.findGardenById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Garden> getGardenByUserId(@PathVariable Long userId) {
        return gardenService.findGardenByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Garden>> getAllGardens() {
        return ResponseEntity.ok(gardenService.findAllGardens());
    }

    @PostMapping
    public ResponseEntity<Garden> createGarden(@RequestBody GardenInput gardenInput) {
        Garden createdGarden = gardenService.createGarden(gardenMapper.inputToDomain(gardenInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGarden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garden> updateGarden(@PathVariable Long id, @RequestBody GardenInput gardenInput) {
        Garden updatedGarden = gardenService.updateGarden(id, gardenMapper.inputToDomain(gardenInput));
        return ResponseEntity.ok(updatedGarden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarden(@PathVariable Long id) {
        boolean deleted = gardenService.deleteGarden(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
