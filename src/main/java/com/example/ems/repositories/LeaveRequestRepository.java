package com.example.ems.repositories;

import com.example.ems.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findAllByStatus(LeaveRequest.LeaveStatus status);
}
