package com.cyan.employee.application.employee.service.impl;

import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.employee.cmd.EmployeeCmd;
import com.cyan.employee.application.employee.convert.EmployeeAppConvert;
import com.cyan.employee.application.employee.service.EmployeeService;
import com.cyan.employee.domain.employee.Employee;
import com.cyan.employee.domain.employee.query.EmployeeListQuery;
import com.cyan.employee.domain.employee.query.EmployeeQuery;
import com.cyan.employee.domain.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 员工服务
 * @author cy.Y
 * @version 1.0.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * 获取员工列表
     *
     * @return 员工列表
     */
    @Override
    public List<EmployeeBO> list(EmployeeListQuery query) {
        List<Employee> employees = employeeRepository.list(query);
        return Optional.ofNullable(employees).orElse(List.of()).stream().map(EmployeeAppConvert.INSTANCE::toEmployeeBO).toList();
    }

    /**
     * 保存员工
     *
     * @param cmd 保存命令
     * @return 员工
     */
    @Override
    public EmployeeBO save(EmployeeCmd cmd) {
        Employee employee = EmployeeAppConvert.INSTANCE.toEmployee(cmd);
        employee = employee.save(employeeRepository);
        return EmployeeAppConvert.INSTANCE.toEmployeeBO(employee);
    }

    /**
     * 根据id查询员工
     *
     * @param id 员工id
     * @return 员工
     */
    @Override
    public EmployeeBO queryById(String id) {
        Employee employee = employeeRepository.findById(id);
        return EmployeeAppConvert.INSTANCE.toEmployeeBO(employee);
    }

    /**
     * 根据查询条件查询员工
     *
     * @param query 查询条件
     * @return 员工
     */
    @Override
    public EmployeeBO queryOne(EmployeeQuery query) {
        Employee one = employeeRepository.findOne(query);
        return EmployeeAppConvert.INSTANCE.toEmployeeBO(one);
    }
}
