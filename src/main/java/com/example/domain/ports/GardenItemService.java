package com.example.domain.ports;

import com.example.domain.models.GardenItem;

import java.util.List;
import java.util.Optional;

public interface GardenItemService {
    Optional<GardenItem> findGardenItemById(Long id);
    List<GardenItem> findGardenItemByGardenId(Long id);
    List<GardenItem> findAllGardenItems();

    //mutation
    GardenItem createGardenItem(GardenItem gardenItem);
    GardenItem updateGardenItem(Long id, GardenItem gardenItem);
    boolean deleteGardenItem(Long id);
}
