package com.cyan.employee.application.login;

import com.cyan.employee.application.employee.bo.EmployeeBO;

/**
 * 登陆服务
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface LoginService {

    /**
     * 登陆
     *
     * @param passport    通行证
     * @param password 密码
     * @return token
     */
    String login(String passport, String password);

    /**
     * 验证
     *
     * @param token token
     * @return 员工
     */
    EmployeeBO verify(String token);
}
