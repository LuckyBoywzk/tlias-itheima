package com.example.filter;


import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        // 获取请求url
        String url = req.getRequestURL().toString();
        log.info("获取登录请求的url:" + url);

        // 判断请求中是否有login，如果有，直接放行，如果没有，则进行拦截
        if (url.contains("login")) {
            log.info("登录操纵，放行...");
            filterChain.doFilter(req, res);
            return;
        }

        // 获取请求头中的jwt令牌
        String jwt = req.getHeader("token");

        // 判断令牌是否存在，如果不存在，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            res.getWriter().write(noLogin);
            return;
        }

        // 解析token，解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            res.getWriter().write(noLogin);
            return;
        }

        // 登录合法，放行
        log.info("令牌合法，放行...");
        filterChain.doFilter(req, res);
    }
}
