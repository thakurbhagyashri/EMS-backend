package com.example.ems.service;

import com.example.ems.dto.CompensationDTO;


import java.util.List;

public interface CompensationService {
    List<CompensationDTO> getAllCompensationsByEmployeeId(Long employeeId);
    CompensationDTO getCurrentCompensation(Long employeeId);
    CompensationDTO createCompensation(Long employeeId, CompensationDTO dto);
    CompensationDTO updateCompensation(Long employeeId, Long compensationId, CompensationDTO dto);
}
