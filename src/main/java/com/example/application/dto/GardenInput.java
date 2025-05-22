package com.example.application.dto;

import com.example.domain.models.GardenItem;

import java.util.List;

public class GardenInput {
    private Long userId;
    private List<GardenItem> items;

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
