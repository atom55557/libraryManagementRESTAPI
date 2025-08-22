package com.yusa.librarymanagement.mapper;

import com.yusa.librarymanagement.dto.UserResponseDto;
import com.yusa.librarymanagement.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDtoMapper {

    public static UserResponseDto toDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    public static List<UserResponseDto> toDtoList(List<User> users){
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
