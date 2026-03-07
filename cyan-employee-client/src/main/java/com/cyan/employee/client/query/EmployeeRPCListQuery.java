package com.cyan.employee.client.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 员工列表查询参数,且条件
 *
 * @author cy.Y
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class EmployeeRPCListQuery {
    /**
     * 员工ID列表
     */
    private List<String> ids;

    /**
     * 员工通行证
     */
    private List<String> passports;
}
