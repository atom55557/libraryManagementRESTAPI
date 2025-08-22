package com.yusa.librarymanagement.service.impl;

import com.yusa.librarymanagement.dto.LoginRequsetDto;
import com.yusa.librarymanagement.dto.UserRequestDto;
import com.yusa.librarymanagement.model.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getUsers();
    List<User> getUsersByName(String name);
    User registerUser( UserRequestDto request);
    User getUserById(Long id);
    User createUser(User user);
    void deleteUserById(Long id);
    String loginUser(LoginRequsetDto request);
}
