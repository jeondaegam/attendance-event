package com.example.attendanceevent.mapper;

import com.example.attendanceevent.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 아이디로 유저 찾기
    User findByLoginId(String loginId);

}
