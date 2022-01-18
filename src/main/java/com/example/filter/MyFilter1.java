package com.example.filter;


import javax.servlet.*;
import java.io.IOException;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter 1");
        chain.doFilter(request, response); // 다음 필터로 넘어가라는 의미

        // 해당 필터에서 처리하고 다시 다음 필터로 넘겨주는 doFilter를 호출한다.

    }
}
