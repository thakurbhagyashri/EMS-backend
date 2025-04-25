package com.example.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
