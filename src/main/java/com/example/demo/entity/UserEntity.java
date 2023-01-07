package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    private String username;

    @Column(name = "account")
    private BigDecimal account;

    @Column(name="currency")
    private String currency;
    public UserEntity(final int id, final String username, final BigDecimal account, String currency) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.currency=currency;
    }

    public UserEntity() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(final BigDecimal account) {
        this.account = account;
    }

    //    public List<ItemRepository> getItemsEntityList() {
    //        return itemsEntityList;
    //    }
}
