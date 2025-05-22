package com.example.domain.models;

import java.util.List;

public class Garden {
    private Long id;
    private Long userId;
    private List<GardenItem> items;

    public Garden() {
    }

    public Garden(Long id, Long userId, List<GardenItem> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
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

    public List<GardenItem> getItems() {
        return items;
    }

    public void setItems(List<GardenItem> items) {
        this.items = items;
    }
}
