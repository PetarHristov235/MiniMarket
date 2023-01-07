package com.example.demo.dao;

import com.example.demo.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContractsRepository extends JpaRepository<ContractEntity, Integer> {

    @Query(value = "select * from contracts as c join items as i on c.item_id = i.items_id join users as u on u.user_id=c.seller_id",
            nativeQuery = true)
    List<ContractEntity> findingAllContracts();

    @Query(value = "select * from contracts where status='true'",
            nativeQuery = true)
    List<ContractEntity> findAllActiveContracts();

    @Transactional
    @Modifying
    @Query(value = "insert into contracts(seller_id,item_id,price,status) values (:sellerId , :itemId , :price, 'true')",
            nativeQuery = true)
    void saveNewContract(@Param("sellerId") int sellerId, @Param("itemId") int itemId, @Param("price") BigDecimal price);

    @Query(value = "select * from contracts as c where c.seller_id= :sellerId",
            nativeQuery = true)
    List<ContractEntity> findAllContractsBySellerId(@Param("sellerId") int sellerId);

    @Query(value = "select * from contracts  as c where c.item_id= :itemId and c.status='true'",
            nativeQuery = true)
    ContractEntity getActiveContractByItemId(@Param("itemId") int id);

    @Transactional
    @Modifying
    @Query(value = "update contracts set price= :price where item_id= :itemId and status='true';",
            nativeQuery = true)
    void updatePriceById(@Param("itemId") int itemId, @Param("price") BigDecimal price);

    @Transactional
    @Modifying
    @Query(value = "update contracts set buyer_id= :buyerId , status='false' where item_id= :itemId ",
            nativeQuery = true)
    void closingContract(@Param("buyerId") int buyerId, @Param("itemId") int itemId);

    @Transactional
    @Modifying
    @Query(value = "select * from contracts where item_id= :itemId AND seller_id= :sellerId AND buyer_id= :buyerId AND status='false'",
            nativeQuery = true)
    List<ContractEntity> getAllClosed(@Param("itemId") int itemId, @Param("sellerId") int sellerId,
                                      @Param("buyerId") int buyerId);

    @Transactional
    @Modifying
    @Query(value ="delete from contracts where contracts_id= :id", nativeQuery = true)
    void deleteContractById(@Param("id")int id);
}

