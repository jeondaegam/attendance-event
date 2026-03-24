package com.example.attendanceevent.controller;

import com.example.attendanceevent.dto.MyPageResponse;
import com.example.attendanceevent.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{userId}")
    public ResponseEntity<MyPageResponse> getMyPage(@PathVariable Long userId) {
        MyPageResponse data = myPageService.getMyInfo(userId);
        return ResponseEntity.ok(data);
    }

}
