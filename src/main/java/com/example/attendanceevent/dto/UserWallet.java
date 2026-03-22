package com.example.attendanceevent.dto;

import jdk.jfr.Description;

import java.time.LocalDate;

@Description("사용자별 현재 포인트 잔액")
public class UserWallet {

    private Long userId;
    private int balance;
    private LocalDate updatedAt;

    public UserWallet(Long userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
