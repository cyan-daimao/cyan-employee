package com.cyan.employee.login.config;

import com.cyan.employee.login.filter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EmployeeLoginWebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public EmployeeLoginWebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/health",
                        "/login",
                        "/rpc/**",
                        "/public/**"
                );
    }
}
