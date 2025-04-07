package com.example.ems.service;

import com.example.ems.DTO.UserDTO;
import com.example.ems.entities.User;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.UserMapper;
import com.example.ems.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toDto(user);
    }

    public UserDTO createUser(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserMapper.toDto(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (existingUser.getRole() != User.Role.EMPLOYEE) {
            throw new AccessDeniedException("Only EMPLOYEE users can be updated.");
        }

        existingUser.setEmail(dto.getEmail());
        existingUser.setRole(dto.getRole());
        existingUser.setStatus(dto.getStatus());
        existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        return UserMapper.toDto(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
