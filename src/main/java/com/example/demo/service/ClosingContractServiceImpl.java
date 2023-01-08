package com.example.demo.service;

import com.example.demo.currencyConvert.CurrencyConverter;
import com.example.demo.dao.ItemRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.ContractEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ClosingContractServiceImpl implements ClosingContractService {

    UserRepository userRepository;
    ItemRepository itemRepository;
    CurrencyConverter currencyConverter;

    @Autowired
    public ClosingContractServiceImpl(final UserRepository userRepository, final ItemRepository itemRepository,
                                      final CurrencyConverter currencyConverter) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public boolean dealProcess(final ContractEntity contractEntity, final int buyerId) {
        boolean isCompleted=false;
        final UserEntity sellerUser = contractEntity.getUserEntity();
        if (sellerUser.getId() != buyerId) {
            final UserEntity buyerUser = userExistingChecker(buyerId);
            final BigDecimal itemPrice = contractEntity.getPrice();
            final BigDecimal currencyValue = currencyConverter.getCurrencyValue(sellerUser.getCurrency(),
                    buyerUser.getCurrency());
            final BigDecimal itemPriceInBuyerCurrency = currencyValue.multiply(itemPrice);
            if( buyerUser.getAccount().compareTo(itemPriceInBuyerCurrency)>=0){
                final BigDecimal  buyerAccountAfterDeal = buyerUser.getAccount().subtract(itemPriceInBuyerCurrency);
                final BigDecimal sellerAccountAfterDeal = sellerUser.getAccount().add(itemPrice);
                userRepository.updateUserAccountPrice(buyerAccountAfterDeal, buyerUser.getId());
                userRepository.updateUserAccountPrice(sellerAccountAfterDeal,sellerUser.getId());
                itemRepository.updateItemOwnership(buyerId,contractEntity.getItemEntity().getId());
                isCompleted=true;
            }
        } else if(sellerUser.getId() == buyerId){
            throw new RuntimeException("Seller with id: " + sellerUser.getId() + " cannot buy his own item.");

        }
  return isCompleted;  }

    private UserEntity userExistingChecker(final int id) {
        final Optional<UserEntity> optionalUser = userRepository.getUserById(id);
        final UserEntity user;
        user = optionalUser.orElseGet(UserEntity::new);
        return user;
    }
}
