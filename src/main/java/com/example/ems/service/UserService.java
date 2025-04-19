package com.example.ems.service;

import com.example.ems.dto.UserDTO;
import com.example.ems.entities.User;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.UserMapper;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    public List<UserDTO> getAllUsers() ;
    public UserDTO getUserById(Long id) ;
    public UserDTO createUser(UserDTO dto) ;
    public UserDTO updateUser(Long id, UserDTO dto) ;
    public void deleteUser(Long id);
}
