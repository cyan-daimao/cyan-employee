package com.cyan.employee.login.filter;

import com.cyan.arch.common.api.ErrorCode;
import com.cyan.arch.common.api.LoginException;
import com.cyan.arch.common.api.Response;
import com.cyan.employee.client.LoginClient;
import com.cyan.employee.client.dto.EmployeeDTO;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 登陆拦截器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    @Lazy
    private LoginClient loginClient;

    /**
     * 请求处理前执行（核心方法，返回 true 放行，false 拦截）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) throws Exception {
        // 示例：记录请求信息
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("拦截到请求：" + method + " " + requestUri);
        String token = request.getHeader("Authorization");
        Response<EmployeeDTO> resp = loginClient.verify(token);
        if (ErrorCode.SUCCESS.getCode() != resp.getCode()) {
            throw new LoginException(resp.getMessage());
        }
        RequestContextHolder.getContext().setEmployee(resp.getData()).setToken(token);
        return true;
    }

    /**
     * 请求处理后、视图渲染前执行（若 preHandle 返回 false，此方法不执行）
     */
    @Override
    public void postHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 请求完成后执行（无论是否抛出异常，若 preHandle 返回 false，此方法不执行）
     * 常用于资源清理
     */
    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler,
                                @Nullable Exception ex) throws Exception {
        RequestContextHolder.clearContext();
    }
}
