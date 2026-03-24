package com.example.attendanceevent.service;

import com.example.attendanceevent.dto.MyPageResponse;
import com.example.attendanceevent.mapper.AttendanceMapper;
import com.example.attendanceevent.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final PointMapper pointMapper;
    private final AttendanceMapper attendanceMapper;


    public MyPageResponse getMyInfo(Long userId) {
        MyPageResponse response = new MyPageResponse();

        // 1. 현재 잔액 가져오기
        int balance = pointMapper.getBalance(userId);

        // 2. 현재 연속 출석 일수
        //  TODO 오늘 출석을 아직 안한 경우, 0으로 보여줄까, 어제까지의 연속출석일수를 보여줄 것인가?
        int continuousDays = attendanceMapper.countContinuousAttendance(userId);

        // 3. 마지막 출석일
        String lastAttendanceDate = attendanceMapper.getLastAttendanceDate(userId);

        response.setUserId(userId);
        response.setBalance(balance);
        response.setContinuousDays(continuousDays);
        response.setLastAttendanceDate(lastAttendanceDate);

        return response;
    }
}
