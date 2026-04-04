package com.example.attendanceevent.controller;

import com.example.attendanceevent.service.AttendanceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/api/attendance")
    public ResponseEntity<String> checkIn(HttpSession session) {
        Long userId = (Long) session.getAttribute("USER_PK");

        try {
            String result = attendanceService.markAttendance(userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
//                    e.getMessage(); // service단에서 넘겨주는 에러 메시지를 화면에 보여줌
        }
    }
}
