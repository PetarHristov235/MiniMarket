package com.example.demo;

import com.example.demo.currencyConvert.CurrencyConverter;
import com.example.demo.dao.ItemRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.ContractEntity;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ClosingContractServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

public class ContractServiceImplTest {

    @Test
    void testIfProcessCouldCompleteSuccessfully(){
        final CurrencyConverter currencyConverter =Mockito.mock(CurrencyConverter.class);
        final UserRepository userRepository=  Mockito.mock(UserRepository.class);
        final ItemRepository itemRepository = Mockito.mock(ItemRepository.class);
        final ClosingContractServiceImpl closingContractService=new ClosingContractServiceImpl(userRepository ,itemRepository,currencyConverter);
        Mockito.when(currencyConverter.getCurrencyValue("GBP","EUR")).thenReturn(new BigDecimal("2"));

        final UserEntity user=new UserEntity(1,"Goshko",new BigDecimal("200"),"GBP");
        final ItemEntity item=new ItemEntity(1,"TestItem",user);
        final ContractEntity contractEntity=new ContractEntity(2,user,null,item,new BigDecimal("100"),true);
        Mockito.when(userRepository.getUserById(2)).thenReturn(
                Optional.of(new UserEntity(2, "Pesho", new BigDecimal("250"), "EUR")));
        closingContractService.dealProcess(contractEntity,2);

        Assertions.assertTrue(closingContractService.dealProcess(contractEntity, 2));
    }


}
