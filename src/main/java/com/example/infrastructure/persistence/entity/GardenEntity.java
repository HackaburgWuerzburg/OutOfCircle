package com.example.infrastructure.persistence.entity;

import com.example.domain.models.GardenItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gardens")
public class GardenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany
    @JoinColumn(name = "gardenId", referencedColumnName = "id")
    private List<GardenItemEntity> items;

    public GardenEntity() {
    }

    public GardenEntity(Long userId, List<GardenItemEntity> items) {
        this.userId = userId;
        this.items = new ArrayList<>(items);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<GardenItemEntity> getItems() {
        return items;
    }

    public void setItems(List<GardenItemEntity> items) {
        this.items = items;
    }
}