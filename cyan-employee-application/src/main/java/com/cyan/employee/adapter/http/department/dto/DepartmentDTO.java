package com.cyan.employee.adapter.http.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 部门DTO
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class DepartmentDTO {

    /**
     * 主键
     */
    private String id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父id
     */
    private String parentId;

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

}
