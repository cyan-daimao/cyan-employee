package com.cyan.employee.domain.employee.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class EmployeeQuery {
    /**
     * 姓名
     */
    private String name;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 通行证
     */
    private String passport;
}
