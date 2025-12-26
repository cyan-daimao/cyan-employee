package com.cyan.employee.domain.department.repository;

import com.cyan.employee.domain.department.Department;
import com.cyan.employee.domain.department.query.DepartmentQuery;

import java.util.List;

/**
 * 部门仓储服务
 *
 * @author cy.Y
 * @version 1.0.0
 */
public interface DepartmentRepository {

    /**
     * 根据id查询部门
     *
     * @param id 部门id
     * @return 部门
     */
    Department findById(String id);

    /**
     * 保存部门
     *
     * @param department 部门
     * @return 部门
     */
    Department save(Department department);


    /**
     * 更新部门
     *
     * @param department 部门
     * @return 部门
     */
    Department update(Department department);

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    void deleteById(String id);

    /**
     * 根据名称查询部门
     *
     * @param query 查询条件
     * @return 部门
     */
    Department findOne(DepartmentQuery query);

    /**
     * 查询部门
     *
     * @param query 查询条件
     * @return 部门
     */
    List<Department> list(DepartmentQuery query);
}
