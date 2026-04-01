package com.cyan.employee.infra.persistence.employee.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 员工表
 *
 * @author cy.Y
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("cyan_employee")
public class EmployeeDO {
    /**
     * 主键
     */
    @TableId(value = "id", type=IdType.ASSIGN_ID)
    private Long id;

    /**
     * 工号
     */
    @TableField(value = "staff_number")
    private String staffNumber;

    /**
     * 通行证（英文名+工号）
     */
    @TableField(value = "passport")
    private String passport;

    /**
     * 姓名
     */
    @TableField(value = "cn_name")
    private String cnName;

    /**
     * 英文名
     */
    @TableField(value = "en_name")
    private String enName;

    /**
     * 手机
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 职位
     */
    @TableField(value = "job_title")
    private String jobTitle;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @TableField(value = "deleted_at")
    @TableLogic(value = "null", delval = "now()")
    private LocalDateTime deletedAt;
}
