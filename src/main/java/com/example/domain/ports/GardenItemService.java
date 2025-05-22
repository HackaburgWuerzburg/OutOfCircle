package com.example.domain.ports;

import com.example.domain.models.GardenItem;

import java.util.List;

public interface GardenItemService {
    Optional<GardenItem> findGardenItemById(Long id);
    List<GardenItem> findGardenItemByGardenId(Long id);
}
