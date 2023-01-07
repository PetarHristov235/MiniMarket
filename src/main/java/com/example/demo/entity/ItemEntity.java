package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "items_id",
            nullable = false,unique = true)
    private Integer id;

    @Column
    private String name;

    //    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)



    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity user;

    //    private UserEntity user;
    //
    //    @Transient
    //    String ownerUserName=user.getUsername();


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity user) {
        this.user = user;
    }

    public ItemEntity() {
    }

    public ItemEntity(final Integer id, final String name, final UserEntity user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
