package com.cyan.employee.login.filter;

import jakarta.servlet.http.HttpServletRequest;

// 第一步：封装真实IP获取工具类
public class IpUtils {
    // 常见代理转发的IP头字段（根据你的网关/Nginx配置调整）
    private static final String[] IP_HEADERS = {
        "X-Real-IP",
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP"
    };

    /**
     * 获取用户真实IP（兼容代理/网关场景）
     */
    public static String getRealIp(HttpServletRequest request) {
        String ip;
        // 1. 从代理头中获取真实IP
        for (String header : IP_HEADERS) {
            ip = request.getHeader(header);
            if (isValidIp(ip)) {
                // X-Forwarded-For可能包含多个IP（客户端, 代理1, 代理2），取第一个
                if (ip.contains(",")) {
                    ip = ip.split(",")[0].trim();
                }
                return ip;
            }
        }
        // 2. 无代理时取原生IP
        ip = request.getRemoteAddr();
        // 3. 本地测试时的IP处理（如127.0.0.1/0:0:0:0:0:0:0:1）
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    // 校验IP是否有效（非空、非unknown）
    private static boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
    }
}