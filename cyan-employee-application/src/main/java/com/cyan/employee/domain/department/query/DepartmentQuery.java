package com.cyan.employee.domain.department.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 部门查询
 *
 * @author cy.Y
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DepartmentQuery {

    /**
     * 部门id
     */
    private String id;
    /**
     * 部门名称
     */
    private String name;
}
