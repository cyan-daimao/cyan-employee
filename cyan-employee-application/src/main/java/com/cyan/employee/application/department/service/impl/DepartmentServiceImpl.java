package com.cyan.employee.application.department.service.impl;

import com.cyan.employee.application.department.bo.DepartmentBO;
import com.cyan.employee.application.department.cmd.DepartmentCmd;
import com.cyan.employee.application.department.convert.DepartmentAppConvert;
import com.cyan.employee.application.department.service.DepartmentService;
import com.cyan.employee.domain.department.Department;
import com.cyan.employee.domain.department.query.DepartmentQuery;
import com.cyan.employee.domain.department.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 部门服务
 *
 * @author cy.Y
 * @version 1.0.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * 创建部门
     *
     * @param cmd 创建部门命令
     * @return 部门id
     */
    @Override
    public DepartmentBO create(DepartmentCmd cmd) {
        Department department = DepartmentAppConvert.INSTANCE.toDepartment(cmd);
        department = department.save(departmentRepository);
        return DepartmentAppConvert.INSTANCE.toDepartmentBO(department);
    }

    /**
     * 查询部门
     *
     * @param query 查询条件
     * @return 部门
     */
    @Override
    public DepartmentBO queryOne(DepartmentQuery query) {
        Department one = departmentRepository.findOne(query);
        return DepartmentAppConvert.INSTANCE.toDepartmentBO(one);
    }

    /**
     * 查询部门列表
     *
     * @param query 列表查询条件
     * @return 部门列表
     */
    @Override
    public List<DepartmentBO> list(DepartmentQuery query) {
        List<Department> departments= departmentRepository.list(query);
        return Optional.ofNullable(departments).orElse(Collections.emptyList()).stream().map(DepartmentAppConvert.INSTANCE::toDepartmentBO).toList();
    }

    /**
     * 删除部门
     *
     * @param cmd 删除部门命令
     */
    @Override
    public void remove(DepartmentCmd cmd) {
        Department department = departmentRepository.findById(cmd.getId());
        if (department != null) {
            department.delete(departmentRepository);
        }
    }
}
