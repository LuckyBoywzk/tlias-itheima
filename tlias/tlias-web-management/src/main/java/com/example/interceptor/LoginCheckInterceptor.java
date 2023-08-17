package com.example.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override  // 目标资源方法运行前运行，返回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        // 获取请求url
        String url = req.getRequestURL().toString();
        log.info("获取登录请求的url:" + url);

        // 判断请求中是否有login，如果有，直接放行，如果没有，则进行拦截
        if (url.contains("login")) {
            log.info("登录操纵，放行...");
            return true;
        }

        // 获取请求头中的jwt令牌
        String jwt = req.getHeader("token");

        // 判断令牌是否存在，如果不存在，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            res.getWriter().write(noLogin);
            return false;
        }

        // 解析token，解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(error);
            res.getWriter().write(noLogin);
            return false;
        }

        // 登录合法，放行
        log.info("令牌合法，放行...");
        return true;
    }

    @Override  // 目标资源方法运行后放行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override  // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
