package com.example.attendanceevent.controller;

import com.example.attendanceevent.dto.MyPageResponse;
import com.example.attendanceevent.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/api/mypage")
    public ResponseEntity<?> getMyPage(HttpSession session) {
        // getMyPage(@SessionAttribute("USER_PK") Long userId)

        // 세션에서 "USER_PK" 값 꺼내기
        // 인터셉터가 로그인 안한 유저는 이미 걸러줬기 때문에, 세션이 있다고 믿고 바로 꺼내면 OK
        Long userId = (Long) session.getAttribute("USER_PK");

        // 2. PK로 마이페이지 정보 조회
        return ResponseEntity.ok(myPageService.getMyInfo(userId));

    }

    // 이렇게 하면 userId=1처럼 url에 남의 아이디를 넣어서 다른 사람 정보를 훔쳐볼 수 없다
    // 오직 자기 세션에 담긴 pk로만 조회할 수 있으니까
    
}
