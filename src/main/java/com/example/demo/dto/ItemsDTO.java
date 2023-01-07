package com.example.demo.dto;

import lombok.Data;

@Data
public class ItemsDTO {

    private int id;
    private String name;
    private int ownerId;
    private String ownerUsername;

    public ItemsDTO(final int id, final String name, final int ownerId, final String ownerUsername) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
    }

    public ItemsDTO() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }
}
