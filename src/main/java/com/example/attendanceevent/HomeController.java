package com.example.attendanceevent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, friend! 서버가 아주 잘 돌아가고 있어! \uD83D\uDE80";
    }
}
