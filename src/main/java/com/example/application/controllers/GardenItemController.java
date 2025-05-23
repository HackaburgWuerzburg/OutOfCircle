package com.example.application.controllers;

import com.example.application.dto.GardenItemInput;
import com.example.domain.models.GardenItem;
import com.example.infrastructure.mapper.GardenItemMapper;
import com.example.infrastructure.persistence.service.GardenItemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garden-items")
public class GardenItemController {

    private final GardenItemServiceImpl gardenItemService;
    private final GardenItemMapper gardenItemMapper;

    public GardenItemController(GardenItemServiceImpl gardenItemService, GardenItemMapper gardenItemMapper) {
        this.gardenItemService = gardenItemService;
        this.gardenItemMapper = gardenItemMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GardenItem> getGardenItemById(@PathVariable Long id) {
        return gardenItemService.findGardenItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/garden/{gardenId}")
    public ResponseEntity<List<GardenItem>> getGardenItemsByGardenId(@PathVariable Long gardenId) {
        return ResponseEntity.ok(gardenItemService.findGardenItemByGardenId(gardenId));
    }

    @GetMapping
    public ResponseEntity<List<GardenItem>> getAllGardenItems() {
        return ResponseEntity.ok(gardenItemService.findAllGardenItems());
    }

    @PostMapping
    public ResponseEntity<GardenItem> createGardenItem(@RequestBody GardenItemInput gardenItemInput) {
        GardenItem created = gardenItemService.createGardenItem(
                gardenItemMapper.inputToDomain(gardenItemInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GardenItem> updateGardenItem(@PathVariable Long id,
                                                       @RequestBody GardenItemInput gardenItemInput) {
        GardenItem updated = gardenItemService.updateGardenItem(id,
                gardenItemMapper.inputToDomain(gardenItemInput));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGardenItem(@PathVariable Long id) {
        boolean deleted = gardenItemService.deleteGardenItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
