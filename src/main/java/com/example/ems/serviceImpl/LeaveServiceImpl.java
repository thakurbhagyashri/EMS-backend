package com.example.ems.serviceImpl;

import com.example.ems.dto.LeaveRequestDTO;
import com.example.ems.dto.LeaveStatusUpdateDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.LeaveRequest;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.LeaveRequestRepository;
import com.example.ems.service.EmailService;
import com.example.ems.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeRepository employeeRepo;
    private final EmailServiceImpl emailServiceImpl;
    @Override
    public boolean applyForLeave(LeaveRequestDTO dto) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest leave = LeaveRequest.builder()
                .employee(employee)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status(LeaveRequest.LeaveStatus.PENDING)
                .build();
        try {
            leaveRepo.save(leave);
            // Format leave dates
            String startDate = dto.getStartDate().toString(); // format if needed
            String endDate = dto.getEndDate().toString();     // format if needed
            // Employee email
            String employeeEmail = employee.getUser().getEmail();
            String employeeName = employee.getFirstName();
            String employeeMessage = String.format(
                    "Hi %s,<br>Your leave request has been submitted successfully for the dates <b>%s</b> to <b>%s</b>.",
                    employeeName, startDate, endDate
            );
            emailServiceImpl.sendEmailWithHtml(employeeEmail, "Leave Request Submitted", employeeMessage);
            // Manager email
            String managerEmail = employee.getManager().getUser().getEmail();
            String managerName = employee.getManager().getFirstName();
            String managerMessage = String.format(
                    "Hi %s,<br>You have received a new leave request from <b>%s</b> for the dates <b>%s</b> to <b>%s</b>.",
                    managerName, employeeName, startDate, endDate
            );
            emailServiceImpl.sendEmailWithHtml(managerEmail, "New Leave Request from " + employeeName, managerMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Approve or Reject Leave
    @Override
    public void updateLeaveStatus(LeaveStatusUpdateDTO dto) {
        LeaveRequest leave = leaveRepo.findById(dto.getLeaveRequestId())
                .orElseThrow(() -> new RuntimeException("Leave Request not found"));

        leave.setStatus(dto.getStatus());
        leave.setApprovedBy(dto.getApprovedBy());
        leave.setAdminNote(dto.getAdminNote());
        leaveRepo.save(leave);
    }

    // Auto Update Employee Leave Status
    @Override
    public void updateEmployeeStatuses() {
        List<LeaveRequest> approvedLeaves = leaveRepo.findAllByStatus(LeaveRequest.LeaveStatus.APPROVED);

        for (LeaveRequest leave : approvedLeaves) {
            Employee employee = leave.getEmployee();
            LocalDate today = LocalDate.now();

            if (!today.isBefore(leave.getStartDate()) && !today.isAfter(leave.getEndDate())) {
                if (employee.getEmploymentStatus() != Employee.EmploymentStatus.ON_LEAVE) {
                    employee.setEmploymentStatus(Employee.EmploymentStatus.ON_LEAVE);
                    employeeRepo.save(employee);
                }
            } else if (today.isAfter(leave.getEndDate())) {
                if (employee.getEmploymentStatus() == Employee.EmploymentStatus.ON_LEAVE) {
                    employee.setEmploymentStatus(Employee.EmploymentStatus.ACTIVE);
                    employeeRepo.save(employee);
                }
            }
        }
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRepo.findAll();
    }
}
