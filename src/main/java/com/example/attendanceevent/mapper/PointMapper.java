package com.example.attendanceevent.mapper;

import com.example.attendanceevent.dto.PointHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PointMapper {

    // 포인트 적립
    void insertPointHistory(PointHistory pointHistory);

    // 잔액 업데이트
    void upsertUserWallet(@Param("userId") Long userId, @Param("amount") int amount);

    // 현재 잔액 조회
    int getBalance(Long userId);
}
