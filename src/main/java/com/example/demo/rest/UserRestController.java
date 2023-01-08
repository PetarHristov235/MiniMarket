package com.example.demo.rest;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    UserService userService;

    @Autowired
    public UserRestController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/allUsersWithItems")
    public List<UserEntity> getAllUsersWithItems(){
        return userService.getAllUsersWithItems();
    }
    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable final String userId) {
        final int id = Integer.parseInt(userId);
        return userService.getUserById(id);
    }
}
