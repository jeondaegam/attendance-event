package com.example.attendanceevent.controller;

import com.example.attendanceevent.dto.LoginRequest;
import com.example.attendanceevent.service.UserService;
import com.example.attendanceevent.dto.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
//        User user = userService.authenticate(loginRequest.getLoginId(), loginRequest.getPassword());
        User user = userService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        if (user != null) {
            session.setAttribute("USER_PK", user.getId()); // 서버의 세션에 USER_PK 라는 이름으로 id값 저장
            return ResponseEntity.ok("❅ *˚̩͙*‧₊̊‧*˚̩͙̩͙* 환영합니다｡ " + user.getUserName() + "님｡｡! *‧‧*˚̩͙*‧₊̊‧* ❅");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 틀렸습니다.");
    }
}
