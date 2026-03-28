package com.example.attendanceevent.dto;

import jdk.jfr.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Description("포인트 적립 및 사용 이력")
@NoArgsConstructor
public class PointHistory {

    private Long id;
    private Long userId;
    private int amount;
    private String reason;
    private Long attendanceLogId;

    public PointHistory(Long userId, int amount, String reason, Long attendanceLogId) {
        this.userId = userId;
        this.amount = amount;
        this.reason = reason;
        this.attendanceLogId = attendanceLogId;
    }
}
