package com.yusa.librarymanagement.controller;

import com.yusa.librarymanagement.dto.LoginRequsetDto;
import com.yusa.librarymanagement.dto.UserRequestDto;
import com.yusa.librarymanagement.dto.UserResponseDto;
import com.yusa.librarymanagement.mapper.UserResponseDtoMapper;
import com.yusa.librarymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto request){
        return UserResponseDtoMapper.toDto(userService.registerUser(request));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequsetDto request){
       return userService.loginUser(request);
    }

}
