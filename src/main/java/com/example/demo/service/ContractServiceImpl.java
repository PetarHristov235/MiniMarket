package com.example.demo.service;

import com.example.demo.dao.ContractsRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.ContractDTO;
import com.example.demo.entity.ContractEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    ContractsRepository contractsRepository;

    UserRepository userRepository;

    ClosingContractService closingContractService;
    ItemRepository itemRepository;

    @Autowired
    public ContractServiceImpl(final ContractsRepository contractsRepository, final UserRepository userRepository,
                               final ClosingContractService closingContractService,
                               final ItemRepository itemRepository) {
        this.contractsRepository = contractsRepository;
        this.userRepository = userRepository;
        this.closingContractService = closingContractService;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ContractDTO> findingAllContracts() {
        final List<ContractEntity> contractEntitiyList = contractsRepository.findingAllContracts();
        return contractEntitiyList.stream()
                .map(this::convertEntityToDto).toList();
    }

    @Override
    public void saveNewContract(final int itemId, final BigDecimal price) {
        final int sellerId = itemRepository.getItemById(itemId).getUser().getId();

        contractsRepository.saveNewContract(sellerId, itemId, price);
    }



    @Override
    public void updatePriceById(final int itemId, final BigDecimal price) {
        contractsRepository.updatePriceById(itemId, price);
    }

    @Override
    public List<ContractDTO> findAllActiveContracts() {
        return contractsRepository.findAllActiveContracts().stream()
                .map(this::convertEntityToDto).toList();
    }


    @Override
    public List<ContractDTO> findAllContractsBySellerId(final int sellerId) {
        return contractsRepository.findAllContractsBySellerId(sellerId).stream().map(this::convertEntityToDto).toList();
    }

    @Override
    public List<ContractDTO> getAllClosed(final int itemId, final int sellerId, final int buyerId) {
        return contractsRepository.getAllClosed(itemId, sellerId, buyerId).stream().map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public void deleteContractById(final int id) {
        contractsRepository.deleteContractById(id);
    }

    @Override
    public void closingActiveContractById(final int buyerId, final int itemId) {
        final ContractEntity activeContractByItemId = contractsRepository.getActiveContractByItemId(itemId);
        final boolean isProcessDid = closingContractService.dealProcess(activeContractByItemId, buyerId);
        if (isProcessDid) {
            contractsRepository.closingContract(buyerId, itemId);
        }
    }

    private ContractDTO convertEntityToDto(final ContractEntity contractEntity) {
        String buyerUsername = null;

        if (null != contractEntity.getBuyerId()) {

            final Optional<UserEntity> buyerById = userRepository.getUserById(contractEntity.getBuyerId());
            if (buyerById.isPresent()) {
                buyerUsername = buyerById.get().getUsername();
            }
        }

        return new ContractDTO(contractEntity.getId(),
                contractEntity.getUserEntity().getId(),
                contractEntity.getUserEntity().getUsername(), contractEntity.getBuyerId(), buyerUsername,
                contractEntity.getItemEntity().getId(), contractEntity.getPrice(), contractEntity.getStatus());
    }


}
