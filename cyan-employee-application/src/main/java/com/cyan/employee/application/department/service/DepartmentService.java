package com.cyan.employee.application.department.service;

import com.cyan.employee.application.department.bo.DepartmentBO;
import com.cyan.employee.application.department.cmd.DepartmentCmd;
import com.cyan.employee.domain.department.query.DepartmentQuery;

import java.util.List;

/**
 * 部门服务
 *
 * @author cy.Y
 * @version 1.0.0
 */
public interface DepartmentService {

    /**
     * 创建部门
     *
     * @param cmd 创建部门命令
     * @return 部门id
     */
    DepartmentBO create(DepartmentCmd cmd);


    /**
     * 查询部门
     *
     * @param query 查询条件
     * @return 部门
     */
    DepartmentBO queryOne(DepartmentQuery query);

    /**
     * 查询部门列表
     *
     * @param query 列表查询条件
     * @return 部门列表
     */
    List<DepartmentBO> list(DepartmentQuery query);

    /**
     * 删除部门
     *
     * @param cmd 删除部门命令
     */
    void remove(DepartmentCmd cmd);
}
