package com.example.demo;

import com.example.demo.dao.ItemRepository;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ClosingContractService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class UserEntityTest {

    @Test
    public void testGetters() {
        UserEntity userEntity = new UserEntity(2, "Pesho", new BigDecimal("200.50"),"GBP");


        Assertions.assertEquals(2,userEntity.getId(),
                "Id getter is incorrect");

        Assertions.assertEquals("Pesho",userEntity.getUsername(),
                "Username getter is incorrect");

        Assertions.assertEquals(new BigDecimal("200.50"),userEntity.getAccount(),
                "Account getter is incorrect");

        Assertions.assertEquals("GBP",userEntity.getCurrency(),
                "Currency getter is incorrect");

    }
}
