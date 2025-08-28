package com.yusa.librarymanagement.service;

import com.yusa.librarymanagement.dto.LoginRequsetDto;
import com.yusa.librarymanagement.dto.UserRequestDto;
import com.yusa.librarymanagement.enums.Role;
import com.yusa.librarymanagement.model.User;
import com.yusa.librarymanagement.repository.UserRepository;

import com.yusa.librarymanagement.security.JwtUtil;
import com.yusa.librarymanagement.service.impl.UserServiceInterface;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Cacheable(value = "users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Cacheable(value = "users")
    public List<User> getUsersByName(String name){
        return userRepository.findByUserName(name);
    }

    @CacheEvict(value = "users", allEntries = true)
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

    @CacheEvict(value = "users",  allEntries = true)
    public User createUser(User user){
        return userRepository.save(user);
    }

    @CacheEvict(value = "users",  allEntries = true)
    public void deleteUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    public String loginUser(LoginRequsetDto request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user.getPassword().equals(request.getPassword())) {
            return jwtUtil.generateToken(request.getEmail());
        }
        throw new IllegalStateException("Wrong email or password");
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
