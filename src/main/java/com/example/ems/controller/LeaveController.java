package com.example.ems.controller;

import com.example.ems.dto.LeaveRequestDTO;
import com.example.ems.dto.LeaveStatusUpdateDTO;
import com.example.ems.entities.LeaveRequest;
import com.example.ems.serviceImpl.EmployeeServiceImpl;
import com.example.ems.serviceImpl.LeaveServiceImpl;
import com.example.ems.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveServiceImpl leaveServiceImpl;
    private final EmployeeServiceImpl employeeServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @PostMapping(path = "/apply")
    public ResponseEntity<?> applyLeave(@RequestBody LeaveRequestDTO dto) {
        boolean check = leaveServiceImpl.applyForLeave(dto);
        return check?new ResponseEntity<>(dto, HttpStatus.OK):new ResponseEntity<>(new RuntimeException("Leave Request Not Submitted"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/update-status")
    public ResponseEntity<String> updateStatus(@RequestBody LeaveStatusUpdateDTO dto) {
        leaveServiceImpl.updateLeaveStatus(dto);
        return ResponseEntity.ok("Leave status updated.");
    }

    @GetMapping(path = "/update-employee-status")
    public ResponseEntity<String> updateEmployeeStatusScheduler() {
        leaveServiceImpl.updateEmployeeStatuses();
        return ResponseEntity.ok("Employee leave statuses refreshed.");
    }

    @GetMapping(path = "/getAllLeaveRequests")
    public ResponseEntity<?> getAllLeaveRequests(){
        List <LeaveRequest> check =leaveServiceImpl.getAllLeaveRequests();
        return  check.isEmpty()? new ResponseEntity<>(check,HttpStatus.OK):new ResponseEntity<>(new RuntimeException("No data Found"),HttpStatus.NO_CONTENT);
    }
}

