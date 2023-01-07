package com.example.demo.service;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserEntity getUserById(final int id) {
        final Optional<UserEntity> user= userRepository.getUserById(id);
       final UserEntity userEntity;
        if(user.isPresent()){
            userEntity=user.get();
        }else{
            throw new RuntimeException("UserWith id: "+id + "does not exist.");
        }
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUsersWithItems() {
        return userRepository.getAllUsersWithItems();
    }

}
