package com.ttokttak.filter;

import com.ttokttak.util.JwtUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class JwtFilter implements Filter {

    private JwtUtil jwtUtil;

    // 인증 불필요 경로
    private static final List<String> WHITELIST = List.of(
            "/api/auth/signup",
            "/api/auth/login",
            "/oauth2/authorization/kakao",
            "/login/oauth2/code/kakao",
            "/swagger-ui.html",
            "/swagger-resources",
            "/v2/api-docs",
            "/webjars"
    );

    @Override
    public void init(FilterConfig filterConfig) {
        // Spring 빈을 서블릿 필터에서 가져오는 방법
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(filterConfig.getServletContext());
        this.jwtUtil = Objects.requireNonNull(ctx).getBean(JwtUtil.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest)  servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();

        // 화이트리스트 경로는 토큰 검사 없이 통과
        boolean isWhitelisted = WHITELIST.stream()
                .anyMatch(path::startsWith);

        if (isWhitelisted) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Authorization 헤더에서 토큰 추출
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("{\"message\": \"로그인이 필요해요.\"}");
            return;
        }

        String token = header.substring(7);

        if (!jwtUtil.isValid(token)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write("{\"message\": \"유효하지 않은 토큰이에요.\"}");
            return;
        }

        // 토큰에서 userId 꺼내서 요청에 저장
        String userId = jwtUtil.getUserId(token);
        req.setAttribute("uid", userId);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
