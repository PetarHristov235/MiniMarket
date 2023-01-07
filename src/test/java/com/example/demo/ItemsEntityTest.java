package com.example.demo;

import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ItemsEntityTest {

    @Test
    public void  itemsEntityGettersTest() {
        UserEntity userEntity = new UserEntity(2, "Pesho", new BigDecimal("200.50"),"GBP");
        ItemEntity itemEntity = new ItemEntity(2, "Item1",
              userEntity);

        Assertions.assertEquals(2, itemEntity.getId(),
                "Id getter is incorrect");

        Assertions.assertEquals("Item1", itemEntity.getName(),
                "Name getter is incorrect");

        Assertions.assertEquals(userEntity, itemEntity.getUser(),
                "User getter is incorrect");



    }

}
