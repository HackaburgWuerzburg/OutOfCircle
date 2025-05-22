package com.example.domain.ports;

import com.example.domain.models.Garden;

import java.util.List;
import java.util.Optional;

public interface GardenService {
    Optional<Garden> findGardenById(Long id);
    Optional<Garden> findGardenByUserId(Long userId);
    List<Garden> findAllGardens();

    //mutations
    Garden createGarden(Garden garden);
    Garden updateGarden(Long id, Garden garden);
    boolean deleteGarden(Long id);
}
