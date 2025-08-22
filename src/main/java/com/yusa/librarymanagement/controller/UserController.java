package com.yusa.librarymanagement.controller;

import com.yusa.librarymanagement.model.User;
import com.yusa.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String userName){
        if(userName != null){
            return userService.getUsersByName(userName);
        }else{
            return userService.getUsers();
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam Long userId){
        userService.deleteUserById(userId);
    }
}
