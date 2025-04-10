package com.example.ems.service;

import com.example.ems.DTO.CompensationDTO;
import com.example.ems.entities.Compensation;
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
    public List<CompensationDTO> getAllCompensationByEmployeeId(long employee_id) {
        /*return compensationRepository.findByEmployeeId(employee_id).stream()
                .map(CompensationMapper::toDto).collect(Collectors.toList());*/
        /*Optional<Compensation> c = compensationRepository.findById(employee_id);
        c.get().getEmployee().getEmployeeId();*/
        return compensationRepository.findById(employee_id).stream().map(CompensationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CompensationDTO getCurrentCompensationByEmployeeId(long employee_id) {
        return null;
    }

    @Override
    public CompensationDTO createCompensation(CompensationDTO compensationDTO) {
        return CompensationMapper.toDto(compensationRepository.save(CompensationMapper.toEntity(compensationDTO)));
    }

    @Override
    public CompensationDTO updateCompensation(CompensationDTO compensationDTO) {
        Optional<Compensation> com = Optional.ofNullable(compensationRepository.findById(compensationDTO.getCompensationId())
                .orElseThrow(() -> new ResourceNotFoundException("No Data Found")));
        if (com.isPresent()){
            Compensation old = com.get();
            old.setCompensationId(compensationDTO.getCompensationId());
            old.setEmployee(compensationDTO.getEmployee());
            old.setSalaryAmount(compensationDTO.getSalaryAmount());
            old.setSalaryType(compensationDTO.getSalaryType());
            old.setBonusAmount(compensationDTO.getBonusAmount());
            old.setEffectiveFrom(compensationDTO.getEffectiveFrom());
            old.setEffectiveTo(compensationDTO.getEffectiveTo());
            old.setRevisionReason(compensationDTO.getRevisionReason());
            try {
                return CompensationMapper.toDto(compensationRepository.save(old));
            } catch (Exception e) {
                return  null;
            }
        }
        return null;
    }
}
