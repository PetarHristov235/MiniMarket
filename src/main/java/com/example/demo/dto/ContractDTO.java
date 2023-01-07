package com.example.demo.dto;

import java.math.BigDecimal;

public class ContractDTO {

    private int id;
    private Integer sellerId;

    private String sellerUsername;

    private Integer buyerId;
    private String buyerUsername;
    private Integer itemId;
    private BigDecimal price;
    private boolean status;

    public ContractDTO(final int id, final Integer sellerId, final String sellerUsername, final Integer buyerId, final String buyerUsername,
                       final Integer itemId, final BigDecimal price, final boolean status) {
        this.id = id;
        this.sellerId = sellerId;
        this.sellerUsername = sellerUsername;
        this.buyerId = buyerId;
        this.buyerUsername = buyerUsername;
        this.itemId = itemId;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(final Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(final String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(final Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(final String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(final boolean status) {
        this.status = status;
    }
}

