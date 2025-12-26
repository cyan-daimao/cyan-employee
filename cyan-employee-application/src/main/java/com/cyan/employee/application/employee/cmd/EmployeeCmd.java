package com.cyan.employee.application.employee.cmd;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 员工命令
 *
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class EmployeeCmd {

    /**
     * 主键
     */
    private String id;

    /**
     * 员工所属部门
     */
    private List<String> departmentIds;
    /**
     * 姓名
     */
    @NotBlank(message = "工号不能为空")
    private String staffNumber;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String cnName;

    /**
     * 姓名
     */
    @NotBlank(message = "用户名不能为空")
    private String enName;

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空")
    private String jobTitle;

}
