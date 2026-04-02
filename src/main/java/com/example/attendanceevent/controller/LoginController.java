package com.example.attendanceevent.controller;

import com.example.attendanceevent.dto.LoginRequest;
import com.example.attendanceevent.service.UserService;
import com.example.attendanceevent.dto.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
        User user = userService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        if (user != null) {
            session.setAttribute("USER_PK", user.getId()); // 서버의 세션에 USER_PK 라는 이름으로 id값 저장
            return ResponseEntity.ok("❅ *˚̩͙*‧₊̊‧*˚̩͙̩͙* 환영합니다｡ " + user.getUserName() + "님｡｡! *‧‧*˚̩͙*‧₊̊‧* ❅");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 틀렸습니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response) { // 로그인 했으면 USER_PK 값이 세션에 있을테니 세션만 받는다

        session.invalidate();

        // 이미 세션 내의 값들을 모두 삭제해서 안해도 괜찮지만, 쿠키도 함께 지워보자
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);

        response.addCookie(cookie); // 응답 헤더에 쿠키를 실어서 보냄

        return ResponseEntity.ok("로그아웃 되었습니다.사물함이 성공적으로 비워졌어! \uD83E\uDEE1");
    }

}
