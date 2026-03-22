package com.example.attendanceevent.controller;

import com.example.attendanceevent.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/check/{userId}")
    public String checkIn(@PathVariable Long userId) {
        try {
            return attendanceService.markAttendance(userId);
        } catch (Exception e) {
            return e.getMessage(); // service단에서 넘겨주는 에러 메시지를 화면에 보여줌
        }
    }
}
