package com.example.attendanceevent.policy;

import lombok.Getter;

@Getter
public enum AttendanceReward {
    WEEKLY(7, 1000, "7일 연속 출석 보너스"),
    MONTHLY(30, 5000, "30일 연속 출석 보너스"),
    NONE(0, 0, ""); // 해당사항 없음

    private final int targetDays;
    private final int amount;
    private final String reason;

    AttendanceReward(int targetDays, int amount, String reason) {
        this.targetDays = targetDays;
        this.amount = amount;
        this.reason = reason;
    }

    // 연속 일수를 넣으면 맞는 보상을 찾아준다.
    public static AttendanceReward findByDays(int days) {
        for (AttendanceReward reward : values()) {
            if (reward.targetDays == days) return reward;
        }
        return NONE;
    }
}
