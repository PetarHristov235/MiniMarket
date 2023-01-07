package com.example.demo.dao;

import com.example.demo.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query(value = "select * from items as i join users as u on i.owner_id =u.user_id ",
            nativeQuery = true)
    List<ItemEntity> getAllItems();

    //From the bonus exercise
    @Query(value = "select * from items as i where i.owner_id = :itemId ",
            nativeQuery = true)
    List<ItemEntity> getAllItemsWithOwnerId(@Param("itemId") int id);

    @Transactional
    @Modifying
    @Query(value = "insert into items (items_id, name,owner_Id)values(:id, :name, :owner_id)",
            nativeQuery = true)
    void saveNewItem(@Param("id") int id, @Param("name") String name, @Param("owner_id") int ownerId);

    @Transactional
    @Modifying
    @Query(value = "update items set owner_id= :ownerId where items_id= :id",
            nativeQuery = true)
    void updateItemOwnership(@Param("ownerId") int ownerId, @Param("id") int id);

    @Query(value = "select * from items where items_id= :id",nativeQuery = true)
    ItemEntity getItemById(@Param("id") int id);

}
