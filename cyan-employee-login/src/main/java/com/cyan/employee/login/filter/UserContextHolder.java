package com.cyan.employee.login.filter;

import com.cyan.employee.client.dto.EmployeeDTO;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 私有化构造器，禁止实例化
     */
    private UserContextHolder() {
    }

    /**
     * 获取上下文（不存在则创建）
     */
    public static UserContext getContext() {
        UserContext context = CONTEXT_HOLDER.get();
        if (context == null) {
            context = new UserContext();
            CONTEXT_HOLDER.set(context);
        }
        return context;
    }

    /**
     * 清空上下文（核心：请求结束时调用）
     */
    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }

    public static EmployeeDTO getCurrentEmployee() {
        return getContext().getEmployee();
    }

    public static void setCurrentEmployee(EmployeeDTO employee) {
        getContext().setEmployee(employee);
    }
}