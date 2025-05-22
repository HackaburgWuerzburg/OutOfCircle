package com.example.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "garden_items")
public class GardenItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "garden")
    private Long gardenId;
}
