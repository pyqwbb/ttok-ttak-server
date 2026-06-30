package com.ttokttak.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttokttak.util.JwtUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JwtFilter implements Filter {

    private JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // 인증 불필요 경로 (Ant 패턴 사용 가능: /** 등)
    private static final List<String> WHITELIST = List.of(
            "/api/auth/signup",
            "/api/auth/login",
            "/oauth2/authorization/kakao",
            "/login/oauth2/code/kakao",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/webjars/**"
    );

    @Override
    public void init(FilterConfig filterConfig) {
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(filterConfig.getServletContext());
        this.jwtUtil = Objects.requireNonNull(ctx, "WebApplicationContext가 초기화되지 않았어요.")
                .getBean(JwtUtil.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 1. CORS 프리플라이트(OPTIONS)는 무조건 통과 — 토큰이 없으므로 검사 대상이 아님
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String path = req.getRequestURI();

        // 2. 화이트리스트는 JWT 검사 없이 통과 (Ant 패턴 매칭)
        if (isWhitelisted(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 3. Authorization 헤더 체크
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            writeError(resp, HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요해요.");
            return;
        }

        String token = header.substring(7).trim();

        if (!jwtUtil.isValid(token)) {
            writeError(resp, HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰이에요.");
            return;
        }

        // 4. 유저 정보 저장 후 다음 필터로
        req.setAttribute("uid", jwtUtil.getUserId(token));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private void writeError(HttpServletResponse resp, int status, String message) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getWriter(), Map.of("message", message));
    }

    @Override
    public void destroy() {
        // 정리할 리소스 없음
    }
}