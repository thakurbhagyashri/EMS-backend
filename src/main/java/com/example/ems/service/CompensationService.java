package com.example.ems.service;

import com.example.ems.DTO.CompensationDTO;
import org.springframework.stereotype.Service;


import java.util.List;

public interface CompensationService {
    //public List<CompensationDTO> getAllCompensation();

    public List<CompensationDTO> getAllCompensationByEmployeeId(long employee_id);
    public CompensationDTO getCurrentCompensationByEmployeeId(long employee_id);
    public CompensationDTO createCompensation(CompensationDTO compensationDTO);
    public  CompensationDTO updateCompensation(CompensationDTO compensationDTO);

    //public boolean deleteByEmpoyeeId(long employee_id);
}
