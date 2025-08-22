package com.yusa.librarymanagement.service;

import com.yusa.librarymanagement.dto.LoginRequsetDto;
import com.yusa.librarymanagement.dto.UserRequestDto;
import com.yusa.librarymanagement.enums.Role;
import com.yusa.librarymanagement.model.User;
import com.yusa.librarymanagement.repository.UserRepository;

import com.yusa.librarymanagement.service.impl.UserServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByName(String name){
        return userRepository.findByUserName(name);
    }

    public User registerUser( UserRequestDto request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    public String loginUser(LoginRequsetDto request) {
        User user = userRepository.finUserByEmail(request.getEmail());
        if (user.getPassword().equals(request.getPassword())) {
            return "success";
        }
        return "fail";
    }

    public User findUserByEmail(String email) {
        return userRepository.finUserByEmail(email);
    }
}
