package com.example.attendanceevent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageResponse {

    private Long userId;
    private Integer balance; // 현재 잔액
    private Integer continuousDays; // 현재 연속 출석 일수
    private String lastAttendanceDate; // 마지막 출석일(참고용)
}
