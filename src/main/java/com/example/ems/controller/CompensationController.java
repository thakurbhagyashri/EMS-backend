package com.example.ems.controller;

import com.example.ems.DTO.CompensationDTO;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.service.CompensationService;
import com.example.ems.service.CompensationServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees/{employee_id}")
@RequiredArgsConstructor
@CrossOrigin
public class CompensationController {
    @Autowired
    private CompensationServicesImpl compensationServicesImpl;

    @GetMapping(path = "/getAllCompensations")
    public ResponseEntity<?> getAllCompensationsByEmployeeId(@PathVariable Long employee_id){
        List<CompensationDTO> list =compensationServicesImpl.getAllCompensationsByEmployeeId(employee_id);
        return list.isEmpty()?new ResponseEntity<>(new ResourceNotFoundException("Not Data Found"),HttpStatus.NO_CONTENT):
                new ResponseEntity<>(list,HttpStatus.OK);
    }

    public CompensationDTO getCurrentCompensation(Long employeeId) {
        return null;
    }

    @PostMapping(path ="/createCompensation")
    public CompensationDTO createCompensation(@PathVariable Long employee_id, @RequestBody CompensationDTO dto){
        return compensationServicesImpl.createCompensation(employee_id,dto);
    }

    @PatchMapping(path = "/updateCompensation/{compensationId}")
    public CompensationDTO updateCompensation(@PathVariable Long employee_id,@PathVariable Long compensationId, @RequestBody CompensationDTO dto){
        return compensationServicesImpl.updateCompensation(employee_id,compensationId,dto);
    }
}
