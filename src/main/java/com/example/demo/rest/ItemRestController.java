package com.example.demo.rest;

import com.example.demo.dto.ItemsDTO;
import com.example.demo.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemRestController {

    ItemsService itemsService;

    @Autowired
    public ItemRestController(final ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("/items")
    public List<ItemsDTO> findAll() {
        return itemsService.getAllItems();
    }

    @GetMapping("getItemByOwnerId/{itemId}")
    public List<ItemsDTO> findById(@PathVariable final int itemId) {
        return itemsService.getAllItemsWithOwnerId(itemId);
    }

    @PostMapping("/addNewItem")
    public void addNewItem(@RequestBody final ItemsDTO itemsDTO) {
        itemsService.saveNewItem(itemsDTO.getId(), itemsDTO.getName(), itemsDTO.getOwnerId());
    }


}
