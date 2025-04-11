package com.example.ems.service;

import com.example.ems.DTO.CompensationDTO;
import com.example.ems.entities.Compensation;
import com.example.ems.entities.Employee;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.CompensationMapper;
import com.example.ems.repositories.CompensationRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CompensationServicesImpl implements CompensationService{
    @Autowired
    CompensationRepository compensationRepository;

    @Override
    public List<CompensationDTO> getAllCompensationsByEmployeeId(Long employeeId) {
        return compensationRepository.findByEmployeeEmployeeId(employeeId).stream()
                .map(CompensationMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CompensationDTO getCurrentCompensation(Long employeeId) {
        return null;
    }

    @Override
    public CompensationDTO createCompensation(Long employeeId, CompensationDTO dto) {
        Employee e = new Employee();
        e.setEmployeeId(employeeId);
        return CompensationMapper.toDTO(compensationRepository.save(CompensationMapper.toEntity(dto,e)));
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
