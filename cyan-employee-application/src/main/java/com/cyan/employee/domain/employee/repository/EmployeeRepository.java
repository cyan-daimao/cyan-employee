package com.cyan.employee.domain.employee.repository;

import com.cyan.employee.domain.employee.Employee;
import com.cyan.employee.domain.employee.query.EmployeeQuery;

import java.util.List;

/**
 * 员工存储服务
 *
 * @author cy.Y
 * @version 1.0.0
 */
public interface EmployeeRepository {
    /**
     * 保存员工
     *
     * @param employee 员工
     * @return 员工
     */
    Employee save(Employee employee);

    /**
     * 获取员工
     *
     * @param id 员工id
     * @return 员工
     */
    Employee findById(String id);

    /**
     * 获取1个员工
     *
     * @param query 查询条件
     * @return 员工
     */
    Employee findOne(EmployeeQuery query);

    /**
     * 获取员工列表
     */
    List<Employee> list();
}
