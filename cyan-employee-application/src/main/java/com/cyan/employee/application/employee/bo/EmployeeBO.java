package com.cyan.employee.application.employee.bo;

import com.cyan.employee.application.department.bo.DepartmentBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工业务对象
 *
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class EmployeeBO {

    /**
     * 主键
     */
    private String id;

    /**
     * 工号
     */
    private String staffNumber;

    /**
     * 通行证
     */
    private String passport;

    /**
     * 员工所属部门
     */
    private List<DepartmentBO> departmentId;

    /**
     * 姓名
     */
    private String cnName;

    /**
     * 姓名
     */
    private String enName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 职位
     */
    private String jobTitle;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;
}
