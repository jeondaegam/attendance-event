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

        // 1. 세션에서 "USER_PK" 값 꺼내기
        Object userPkObj = session.getAttribute("USER_PK");

        // 2. 로그인 안 한 유저 체크
        if (userPkObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요한 서비스입니다. \uD83D\uDE22");
        }

        // 3. Object 타입을 Long으로 형변환
        Long userId = (Long) userPkObj;

        // 4. 안전하게 꺼낸 PK로 마이페이지 정보 조회
        MyPageResponse response = myPageService.getMyInfo(userId);
        return ResponseEntity.ok(response);

    }

    // 이렇게 하면 userId=1처럼 url에 남의 아이디를 넣어서 다른 사람 정보를 훔쳐볼 수 없다
    // 오직 자기 세션에 담긴 pk로만 조회할 수 있으니까
    
}
