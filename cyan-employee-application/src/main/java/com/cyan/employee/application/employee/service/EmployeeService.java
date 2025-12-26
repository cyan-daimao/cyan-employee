package com.cyan.employee.application.employee.service;

import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.employee.cmd.EmployeeCmd;
import com.cyan.employee.domain.employee.query.EmployeeQuery;

import java.util.List;

/**
 * 员工服务
 *
 * @author cy.Y
 */
public interface EmployeeService {
    /**
     * 获取员工列表
     *
     * @return 员工列表
     */
    List<EmployeeBO> list();

    /**
     * 保存员工
     *
     * @param cmd 保存命令
     * @return 员工
     */
    EmployeeBO save(EmployeeCmd cmd);

    /**
     * 根据id查询员工
     *
     * @param id 员工id
     * @return 员工
     */
    EmployeeBO queryById(String id);

    /**
     * 根据查询条件查询员工
     *
     * @param query 查询条件
     * @return 员工
     */
    EmployeeBO queryOne(EmployeeQuery query);
}
