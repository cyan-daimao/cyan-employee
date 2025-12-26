package com.cyan.employee.infra.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 密码工具类
 * @author cy.Y
 * @since 1.0.0
 */
public class BCryptUtil {

    /**
     * 生成加密后的密码（自动加盐）
     * @param rawPassword 原始密码
     */
    public static String encode(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("密码不能为空");
        }
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12)); // 12 是强度因子（log_rounds）
    }

    /**
     * 验证原始密码是否匹配已加密的密码
     * @param rawPassword 原始密码
     * @param encodedPassword 已加密的密码
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}