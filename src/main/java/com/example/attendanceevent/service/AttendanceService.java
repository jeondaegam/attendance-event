package com.example.attendanceevent.service;

import com.example.attendanceevent.dto.Attendance;
import com.example.attendanceevent.dto.PointHistory;
import com.example.attendanceevent.dto.UserWallet;
import com.example.attendanceevent.mapper.AttendanceMapper;
import com.example.attendanceevent.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 모지..? 생성자가 필요하다?
public class AttendanceService {
    private final AttendanceMapper attendanceMapper;
    private final PointMapper pointMapper;

    /**
     * 출석 체크
     */
    @Transactional
    public String markAttendance(Long userId) {

        // 1. 검증: 오늘 이미 출석 했는지? (출석 했다면 내보냄)
        int count = attendanceMapper.countTodayAttendance(userId);
        if (count > 0) {
            throw new RuntimeException("이미 오늘은 출석 도장을 찍으셨습니다!"); // Runtime익셉션은뭐지?
        }

        // 2. 실행: 출석 안했다면? 데이터 넣기
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendanceMapper.insertAttendance(attendance);
        // insert 후에는 attendance.userId에 db가 생성한 번호가 자동으로 담겨 있다.
        System.out.println("생성된 로그 ID: " + attendance.getId());

        // 3. 후속작업: 연속 일수 계산 및 보상 메시지
        int continuousDays = attendanceMapper.countContinuousAttendance(userId);
        String message = "출석 완료! 현재 " + continuousDays + "일 연속 출석 중입니다.\n";

        if (continuousDays == 7) {
            message += "\uD83D\uDE4C7일 연속 달성! 1,000 포인트 지급!\uD83D\uDE4C";
            // 여기에 포인트 지급 service.addPoint(userId, 1000) 같은 걸 넣으면 ok

            int amount = +1000;
            String reason = "7일 연속 출석";
            rewardPoint(userId, amount, reason, attendance.getId());

        }

        return message;
    }


    @Transactional
    public void rewardPoint(Long userId, int amount, String reason, Long attendanceId) {

        // 1. 포인트 이력 쌓기
        PointHistory pointHistory = new PointHistory(userId, amount, reason, attendanceId);
        pointMapper.insertPointHistory(pointHistory);

        // 2. 지갑 잔액 갱신
        // 지갑이 없으면 생성, 있으면 기존 잔액에 더하기
        pointMapper.upsertUserWallet(userId, amount);
//        UserWallet userWallet = new UserWallet(userId, amount);

    }



}
