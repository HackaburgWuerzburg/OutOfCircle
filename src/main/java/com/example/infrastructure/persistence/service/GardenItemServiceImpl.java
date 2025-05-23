package com.example.infrastructure.persistence.service;

import com.example.domain.models.GardenItem;
import com.example.domain.ports.GardenItemService;
import com.example.infrastructure.mapper.GardenItemMapper;
import com.example.infrastructure.persistence.entity.GardenItemEntity;
import com.example.infrastructure.persistence.repository.GardenItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GardenItemServiceImpl implements GardenItemService {
    private final GardenItemRepository gardenItemRepository;
    private final GardenItemMapper gardenItemMapper;

    public GardenItemServiceImpl(GardenItemRepository gardenItemRepository, GardenItemMapper gardenItemMapper) {
        this.gardenItemRepository = gardenItemRepository;
        this.gardenItemMapper = gardenItemMapper;
    }

    @Override
    public Optional<GardenItem> findGardenItemById(Long id) {
        return gardenItemRepository.findById(id)
                .map(gardenItemMapper::entityToDomain);
    }

    @Override
    public List<GardenItem> findGardenItemByGardenId(Long id) {
        return gardenItemRepository.findByGardenId(id)
                .stream()
                .map(gardenItemMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<GardenItem> findAllGardenItems() {
        return gardenItemRepository.findAll()
                .stream()
                .map(gardenItemMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public GardenItem createGardenItem(GardenItem gardenItem) {
        GardenItemEntity savedGardenItemEntity = gardenItemRepository.save(gardenItemMapper.domainToEntity(gardenItem));
        return gardenItemMapper.entityToDomain(savedGardenItemEntity);
    }

    @Override
    public GardenItem updateGardenItem(Long id, GardenItem gardenItem) {
        GardenItemEntity updatedGardenItemEntity = gardenItemMapper.domainToEntity(gardenItem);
        updatedGardenItemEntity.setId(id);
        return gardenItemMapper.entityToDomain(gardenItemRepository.save(updatedGardenItemEntity));
    }

    @Override
    public boolean deleteGardenItem(Long id) {
        if(gardenItemRepository.existsById(id)) {
            gardenItemRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
