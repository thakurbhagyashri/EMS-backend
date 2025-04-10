package com.example.ems.service;

import com.example.ems.DTO.CompensationDTO;
import org.springframework.stereotype.Service;


import java.util.List;

public interface CompensationService {
    List<CompensationDTO> getAllCompensationsByEmployeeId(Long employeeId);
    CompensationDTO getCurrentCompensation(Long employeeId);
    CompensationDTO createCompensation(Long employeeId, CompensationDTO dto);
    CompensationDTO updateCompensation(Long employeeId, Long compensationId, CompensationDTO dto);
}
