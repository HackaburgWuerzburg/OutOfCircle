package com.example.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "garden_items")
public class GardenItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    @JoinColumn(name = "gardenId")
    private Long gardenId;

    public GardenItemEntity() {
    }

    public GardenItemEntity(Long gardenId) {
        this.gardenId = gardenId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGardenId() {
        return gardenId;
    }

    public void setGardenId(Long gardenId) {
        this.gardenId = gardenId;
    }
}
