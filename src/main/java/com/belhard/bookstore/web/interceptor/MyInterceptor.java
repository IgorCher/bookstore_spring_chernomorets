package com.belhard.bookstore.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Log4j2
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
            throws Exception {
        log.debug("Interceptor-PRE: {}, method: {}", request.getRequestURI(), request.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, @NonNull  HttpServletResponse response, @NonNull Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("Interceptor-POST: {}, method: {}", request.getRequestURI(), request.getMethod());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex)
            throws Exception {
        log.debug("Interceptor-AFTER: {}, method: {}", request.getRequestURI(), request.getMethod());
    }
}
