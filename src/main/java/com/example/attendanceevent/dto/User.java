package com.example.attendanceevent.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    private Long id; // db의 PK
    private String loginId;
    private String password;
    private String userName;
    private LocalDate createdAt; // 계정 생성일

}
