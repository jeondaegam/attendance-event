package com.example.attendanceevent.service;

import com.example.attendanceevent.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import com.example.attendanceevent.dto.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public User login(String loginId, String password) {

        // 1. 아이디로 유저 찾기
        User user = userMapper.findByLoginId(loginId);

        if (user != null && user.getPassword().equals(password)) {
            return user; // 로그인 성공

        }
        // 하나라도 틀리면 null 리턴 (혹은 에러 던지기)
        return null;
    }
}
