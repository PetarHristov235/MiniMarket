package com.example.demo.rest;

import com.example.demo.dto.ContractDTO;
import com.example.demo.service.ContractService;
import com.example.demo.service.ContractServiceImpl;
import com.example.demo.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractRestController {

    ContractService contractService;

    ItemsService itemsService;
    @Autowired
    public ContractRestController(final ContractServiceImpl contractService, final ItemsService itemsService) {
        this.contractService = contractService;
        this.itemsService=itemsService;
    }


    @GetMapping("/contracts")
    public List<ContractDTO> findingAllContracts() {
        return contractService.findingAllContracts();
    }

    @GetMapping("/allActiveContracts")
    public List<ContractDTO> findingAllActiveContracts() {
        return contractService.findAllActiveContracts();
    }

    @GetMapping("/allContractsBySellerId/{sellerId}")
    public List<ContractDTO> findAllContractsBySellerId(@PathVariable final int sellerId){
        return contractService.findAllContractsBySellerId(sellerId);
    }

    @GetMapping("/getAllClosed/{itemId}/{sellerId}/{buyerId}")
    public List<ContractDTO> getAllClosed(@PathVariable final int itemId, @PathVariable final int sellerId, @PathVariable final int buyerId){
        return contractService.getAllClosed(itemId,sellerId,buyerId);
    }
    @PostMapping("/addNewContract")
    public void addNewContract(@RequestBody final ContractDTO contractDTO) {
        contractService.saveNewContract(contractDTO.getItemId(),contractDTO.getPrice());
    }

    @PutMapping("/closeContract")
    public void closeContract(@RequestBody final ContractDTO contractDTO){
        contractService.closingActiveContractById(contractDTO.getBuyerId(),contractDTO.getItemId());
    }
    @PutMapping("/updatePriceById")
    public void updatePriceById(@RequestBody final ContractDTO contractDTO) {
        contractService.updatePriceById(contractDTO.getItemId(), contractDTO.getPrice());
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteContractById(@PathVariable final int id){
        contractService.deleteContractById(id);
    }

}
