package com.example.infrastructure.persistence.service;

import com.example.domain.models.Garden;
import com.example.domain.ports.GardenService;
import com.example.infrastructure.mapper.GardenMapper;
import com.example.infrastructure.persistence.entity.GardenEntity;
import com.example.infrastructure.persistence.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GardenServiceImpl implements GardenService {
    private final GardenRepository gardenRepository;
    private final GardenMapper gardenMapper;

    public GardenServiceImpl(GardenRepository gardenRepository, GardenMapper gardenMapper) {
        this.gardenRepository = gardenRepository;
        this.gardenMapper = gardenMapper;
    }

    @Override
    public Optional<Garden> findGardenById(Long id) {
        return gardenRepository.findById(id)
                .map(gardenMapper::entityToDomain);
    }

    @Override
    public Optional<Garden> findGardenByUserId(Long userId) {
        return gardenRepository.findByUserId(userId)
                .map(gardenMapper::entityToDomain);
    }

    @Override
    public List<Garden> findAllGardens() {
        return gardenRepository.findAll()
                .stream()
                .map(gardenMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Garden createGarden(Garden garden) {
        GardenEntity savedGardenEntity = gardenRepository.save(gardenMapper.domainToEntity(garden));
        return gardenMapper.entityToDomain(savedGardenEntity);
    }

    @Override
    public Garden updateGarden(Long id, Garden garden) {
        GardenEntity updatedGardenEntity = gardenRepository.save(gardenMapper.domainToEntity(garden));
        updatedGardenEntity.setId(id);
        return gardenMapper.entityToDomain(gardenRepository.save(updatedGardenEntity));
    }

    @Override
    public boolean deleteGarden(Long id) {
        if(gardenRepository.existsById(id)) {
            gardenRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
