package com.example.ems.service;

import com.example.ems.dto.LeaveRequestDTO;
import com.example.ems.dto.LeaveStatusUpdateDTO;
import com.example.ems.entities.LeaveRequest;

import java.util.List;

public interface LeaveService {
    public boolean applyForLeave(LeaveRequestDTO dto);
    public void updateLeaveStatus(LeaveStatusUpdateDTO dto);
    public void updateEmployeeStatuses();
    public List<LeaveRequest> getAllLeaveRequests();
}
