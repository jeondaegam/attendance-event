package com.example.attendanceevent.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 요청에서 세션을 가져온다(없으면 null 반환)
        HttpSession session = request.getSession(false);

        // 세션에 USER_PK 가 없으면?
        if (session == null || session.getAttribute("USER_PK") == null) {
            // 401 에러를 던진다 (혹은 로그인 페이지로 리다이렉트)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "이 페이지를 보려면 로그인이 필요해!");
            return false; // 이 요청을 컨트롤러로 전달하지 않고 리턴함
        }
        return true; // 로그인 확인됨! 이 요청을 컨트롤러로 전달함
    }
}
