package com.example.ems.controller;

import com.example.ems.DTO.CompensationDTO;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.service.CompensationService;
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
    private CompensationService compensationService;


    @GetMapping(path = "/getAllCompensation")
    public ResponseEntity<?> getAllCompensationByEmployeeId(@PathVariable long employee_id) {
        List<CompensationDTO> list = compensationService.getAllCompensationByEmployeeId(employee_id);
        return list.isEmpty() ? new ResponseEntity<>(new ResourceNotFoundException("No Data Found"), HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/getCurrentCompensationByEmployeeId")
    public ResponseEntity<?> getCurrentCompensationByEmployeeId(@PathVariable long employee_id) {
        return null;
    }

    @PostMapping(path = "/createCompensation")
    public ResponseEntity<?> createCompensation(@RequestBody CompensationDTO compensationDTO) {
        CompensationDTO created = compensationService.createCompensation(compensationDTO);
        return created == null ? new ResponseEntity<>(new ResourceNotFoundException("Compensation not Created"), HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(created, HttpStatus.OK);
    }

    @PutMapping(path = "/updateCompensation")
    public ResponseEntity<?> updateCompensation(@RequestBody CompensationDTO compensationDTO) {
        CompensationDTO updated = compensationService.updateCompensation(compensationDTO);
        return updated == null ? new ResponseEntity<>(new ResourceNotFoundException("Data Not Updated"), HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
