package com.example.ems.scheduler;

import com.example.ems.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeaveStatusScheduler {
    private final LeaveService leaveService;

    @Scheduled(cron = "0 0 0 * * ?") // Every midnight
    public void autoUpdateLeaveStatus() {
        leaveService.updateEmployeeStatuses();
    }
}
