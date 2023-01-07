package com.example.demo.service;

import com.example.demo.entity.ContractEntity;

public interface ClosingContractService {

    boolean dealProcess(ContractEntity contractEntity, int buyerId);
}
