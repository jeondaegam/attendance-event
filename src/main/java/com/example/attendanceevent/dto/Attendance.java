package com.example.attendanceevent.dto;

import jdk.jfr.Description;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//@Data
public class Attendance {

    private Long id;
    private Long userId; // 사용자 pk
    private LocalDate attendanceDate; // 출석날짜
}
