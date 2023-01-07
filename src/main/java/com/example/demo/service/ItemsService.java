package com.example.demo.service;

import com.example.demo.dto.ItemsDTO;

import java.util.List;

public interface ItemsService {
    List<ItemsDTO> getAllItems();
    List<ItemsDTO> getAllItemsWithOwnerId(int ownerId);

    void saveNewItem(int id, String name , int ownerId);
}
