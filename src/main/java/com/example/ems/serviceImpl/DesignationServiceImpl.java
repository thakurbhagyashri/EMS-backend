package com.example.ems.serviceImpl;

import com.example.ems.dto.UserDTO;
import com.example.ems.entities.Designation;
import com.example.ems.entities.Employee;
import com.example.ems.entities.User;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.UserMapper;
import com.example.ems.repositories.DesignationRepository;
import com.example.ems.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DesignationServiceImpl {

    private final DesignationRepository designationRepository;

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    public Optional<Designation> getDesignationById(Long id) {
        return designationRepository.findById(id);
    }

    public List<Designation> getDesignationsByEmployee(Employee employee) {
        return designationRepository.findByEmployee(employee);
    }

    public List<Designation> getDesignationsByReportingTo(Employee reportingTo) {
        return designationRepository.findByReportingTo(reportingTo);
    }

    public Designation getCurrentDesignation(Employee employee) {
        return designationRepository.findTopByEmployeeAndEffectiveToIsNullOrderByEffectiveFromDesc(employee);
    }

    public List<Designation> getActiveDesignations(LocalDate currentDate) {
        return designationRepository.findByEffectiveToIsNullOrEffectiveToAfter(currentDate);
    }

    public Designation saveDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    public void deleteDesignation(Long id) {
        designationRepository.deleteById(id);
    }

    @Service
    @RequiredArgsConstructor
    public static class UserService {

        private final UserRepository userRepository;

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
            return UserMapper.toDto(userRepository.save(user));
        }


    }
}