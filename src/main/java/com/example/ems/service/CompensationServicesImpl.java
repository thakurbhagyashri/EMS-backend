package com.example.ems.service;

import com.example.ems.DTO.CompensationDTO;
import com.example.ems.entities.Compensation;
import com.example.ems.entities.Employee;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.CompensationMapper;
import com.example.ems.repositories.CompensationRepository;
import com.example.ems.repositories.EmployeeRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CompensationServicesImpl implements CompensationService{
    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CompensationDTO> getAllCompensationsByEmployeeId(Long employeeId) {
        return compensationRepository.findByEmployeeEmployeeId(employeeId).stream()
                .map(CompensationMapper::toDTO)
                .collect(Collectors.toList());
        /*
         * return compensationRepository.findByEmployeeEmployeeId(employeeId).stream()
         * .map(e->
         * modelMapper.map(e,CompensationDTO.class)).collect(Collectors.toList());
         */
    }

    @Override
    public CompensationDTO getCurrentCompensation(Long employeeId) {
        Employee exist = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("No Employee Exist by this ID"));
        ArrayList<Compensation> c = (ArrayList<Compensation>) compensationRepository.findByEmployeeEmployeeId(employeeId);
        return CompensationMapper.toDTO(c.get((c.size()-1)));
    }

    @Override
    public CompensationDTO createCompensation(Long employeeId, CompensationDTO dto) {
        Employee exist =employeeRepository.findById(employeeId).orElseThrow
                (()-> new ResourceNotFoundException("No Employee Exist by this ID"));
        return CompensationMapper.toDTO(compensationRepository.save(CompensationMapper.toEntity(dto,exist)));
    }

    @Override
    public CompensationDTO updateCompensation(Long employeeId, Long compensationId, CompensationDTO dto) {
        Compensation existing = compensationRepository.findById(compensationId)
                .orElseThrow(() -> new RuntimeException("No compensation not found"));

        if (!existing.getEmployee().getEmployeeId().equals(employeeId)) {
            throw new RuntimeException("Compensation doesn't belong to this employee");
        }
        existing.setSalaryAmount(dto.getSalaryAmount());
        existing.setSalaryType(dto.getSalaryType());
        existing.setBonusAmount(dto.getBonusAmount());
        existing.setEffectiveFrom(dto.getEffectiveFrom());
        existing.setEffectiveTo(dto.getEffectiveTo());
        existing.setRevisionReason(dto.getRevisionReason());
        return CompensationMapper.toDTO(compensationRepository.save(existing));
    }
}
