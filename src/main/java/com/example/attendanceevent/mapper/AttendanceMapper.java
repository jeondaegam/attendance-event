package com.example.attendanceevent.mapper;

import com.example.attendanceevent.dto.Attendance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {

    /** 오늘 출석 여부 확인 (count가 0보다 크면 이미 출석한 것) */
    int countTodayAttendance(Long userId);

    /** 출석 정보 저장 */
    void insertAttendance(Attendance attendance);

    /** 연속 출석 일수 계산 */
    int countContinuousAttendance(Long userId);

    /** 마지막 출석일 조회 */
    String getLastAttendanceDate(Long userId);
}
