package com.example.demo.service;

import com.example.demo.dto.ContractDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ContractService {

    List<ContractDTO> findingAllContracts();

    void saveNewContract(int itemId, BigDecimal price);

    void updatePriceById(int id, BigDecimal price);

    List<ContractDTO> findAllActiveContracts();

    void closingActiveContractById(int itemId, int buyerId);

    List<ContractDTO> findAllContractsBySellerId(int sellerId);

    List<ContractDTO> getAllClosed(int itemId, int sellerId, int buyerId);

    void deleteContractById(int id);
}
