package com.example.ems.mapper;

import com.example.ems.dto.UserDTO;
import com.example.ems.entities.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
    }
}
