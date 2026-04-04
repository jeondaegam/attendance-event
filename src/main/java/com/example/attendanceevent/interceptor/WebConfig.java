package com.example.attendanceevent.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // 이 클래스에서 필요한 객체를 new 키워드 없이 자동으로 생성해주는 녀석?
// Interceptor를 어떤 주소(URL)에 적용할지 설정한다.
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/member/login", "/users/join", "/api/public/**"); // 로그인 체크가 필요 없는 페이지를 추가
    }

    // 인터셉터를 2개이상 추가하고싶으면?
}
