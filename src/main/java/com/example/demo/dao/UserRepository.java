package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from users as u left join items as i on u.user_id=i.owner_id ;",
            nativeQuery = true)
    List<UserEntity> getAllUsers();
    @Query(value = "select * from users as u join items as i on u.user_id=i.owner_id ;",
            nativeQuery = true)
    List<UserEntity> getAllUsersWithItems();
    @Query(value = "select * from users as u where u.user_id= :userId",
            nativeQuery = true)
    Optional<UserEntity> getUserById(@Param("userId") int id);

    @Transactional
    @Modifying
    @Query(value = "update users set account= :account where user_id= :id ",nativeQuery = true)
    void updateUserAccountPrice(@Param("account")BigDecimal account, @Param("id")int id);
}
