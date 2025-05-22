package com.example.domain.models;

public class GardenItem {
    private Long id;
    private Long gardenId;

    public GardenItem() {
    }

    public GardenItem(Long id, Long gardenId) {
        this.id = id;
        this.gardenId = gardenId;
    }

    public Long getGardenId() {
        return gardenId;
    }

    public void setGardenId(Long gardenId) {
        this.gardenId = gardenId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
