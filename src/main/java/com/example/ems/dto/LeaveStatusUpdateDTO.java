package com.example.ems.dto;

import com.example.ems.entities.LeaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStatusUpdateDTO {
    private Long leaveRequestId;
    private String approvedBy;
    private String adminNote;
    private LeaveRequest.LeaveStatus status; // APPROVED or REJECTED
}

