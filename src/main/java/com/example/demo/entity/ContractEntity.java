package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="contracts")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "contracts_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity userEntity;
    @Column(name = "buyer_id")
    private Integer buyerId;
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @Column(name = "price")
    BigDecimal price;

    @Column(name="status")
    Boolean status;

    public ContractEntity(){}

    public ContractEntity(final Integer id, final UserEntity userEntity, final Integer buyerId, final ItemEntity itemEntity, final BigDecimal price,
                          final Boolean status) {
        this.id = id;
        this.userEntity = userEntity;
        this.buyerId = buyerId;
        this.itemEntity = itemEntity;
        this.price = price;
        this.status = status;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(final Integer buyerId) {
        this.buyerId = buyerId;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(final ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(final Boolean status) {
        this.status = status;
    }
}
