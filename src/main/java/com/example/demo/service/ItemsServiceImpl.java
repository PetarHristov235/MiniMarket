package com.example.demo.service;

import com.example.demo.dao.ItemRepository;
import com.example.demo.dto.ItemsDTO;
import com.example.demo.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    ItemRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(final ItemRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public List<ItemsDTO> getAllItems() {
        final List<ItemEntity> allItems = itemsRepository.getAllItems();
        final List<ItemsDTO> allItemsDTO = new ArrayList<>();
        for (final ItemEntity itemEntity : allItems) {
            allItemsDTO.add(convertEntityToDto(itemEntity));
        }

        return allItemsDTO;
    }



    @Override
    public List<ItemsDTO> getAllItemsWithOwnerId(final int id) {
        final List<ItemEntity> allItemsWithOwnerIdList = itemsRepository.getAllItemsWithOwnerId(id);
        return allItemsWithOwnerIdList.stream().map(ItemsServiceImpl::convertEntityToDto).toList();
    }

    @Override
    public void saveNewItem(final int id, final String name, final int ownerId) {
        itemsRepository.saveNewItem(id, name, ownerId);
    }

    private static ItemsDTO convertEntityToDto(final ItemEntity itemEntity) {
        return new ItemsDTO(itemEntity.getId(), itemEntity.getName(), itemEntity.getUser().getId(),
                itemEntity.getUser().getUsername());
    }

}
