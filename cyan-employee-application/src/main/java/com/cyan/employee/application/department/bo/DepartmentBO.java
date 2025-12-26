package com.cyan.employee.application.department.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 部门业务对象
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class DepartmentBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 主管id
     */
    private String leaderId;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    private LocalDateTime updateAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;
}
