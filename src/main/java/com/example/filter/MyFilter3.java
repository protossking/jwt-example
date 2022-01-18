package com.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setCharacterEncoding("UTF-8");

        // 만약 토큰을 검증하여 Controller 에 접근 여부 결정

        if(req.getMethod().equals("POST")) {
            String auth_header = req.getHeader("Authorization");

            if(auth_header.equals("secret")) {
                chain.doFilter(req, res);
            }
            else {
                PrintWriter writer = res.getWriter();
                writer.println("인증 안됨");
            }
        }
        else {
            chain.doFilter(request, response);
        }
    }

    /*
     POST 로 요청이 왔다면 헤더에 Authorization 값을 꺼낸다
     해당값이 임시로 만들어놓은 키 값인 시크릿이 맞는지 확인
     맞다면 계속 필터타게하고
     틀리면 인증안됨이라는 메세지 응답
     */
}
