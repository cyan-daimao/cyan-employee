package com.cyan.employee.infra.persistence.employee.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyan.arch.common.util.CollUtils;
import com.cyan.arch.common.util.StrUtils;
import com.cyan.employee.domain.employee.Employee;
import com.cyan.employee.domain.employee.query.EmployeeListQuery;
import com.cyan.employee.domain.employee.query.EmployeeQuery;
import com.cyan.employee.domain.employee.repository.EmployeeRepository;
import com.cyan.employee.infra.persistence.employee.convert.EmployeeInfraConvert;
import com.cyan.employee.infra.persistence.employee.dos.EmployeeDO;
import com.cyan.employee.infra.persistence.employee.mapper.EmployeeMapper;
import com.cyan.employee.infra.util.BCryptUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author cy.Y
 * @version 1.0.0
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeMapper employeeMapper;

    public EmployeeRepositoryImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 保存员工
     *
     * @param employee 员工
     * @return 员工
     */
    @Override
    public Employee save(Employee employee) {
        EmployeeDO employeeDO = EmployeeInfraConvert.INSTANCE.toEmployeeDO(employee);
        employeeDO.setPassword(BCryptUtil.encode("12345"));
        employeeMapper.insert(employeeDO);
        employeeDO = employeeMapper.selectById(employeeDO.getId());
        return EmployeeInfraConvert.INSTANCE.toEmployee(employeeDO);
    }

    /**
     * 获取员工
     *
     * @param id 员工id
     * @return 员工
     */
    @Override
    public Employee findById(String id) {
        EmployeeDO employeeDO = employeeMapper.selectById(id);
        return EmployeeInfraConvert.INSTANCE.toEmployee(employeeDO);
    }

    /**
     * 获取1个员工
     *
     * @param query 查询条件
     */
    @Override
    public Employee findOne(EmployeeQuery query) {
        LambdaQueryWrapper<EmployeeDO> queryWrapper = new LambdaQueryWrapper<EmployeeDO>()
                .eq(StrUtils.isNotBlank(query.getEmail()), EmployeeDO::getEmail, query.getEmail())
                .eq(StrUtils.isNotBlank(query.getPhone()), EmployeeDO::getPhone, query.getPhone());
        EmployeeDO employeeDO = employeeMapper.selectOne(queryWrapper);
        return EmployeeInfraConvert.INSTANCE.toEmployee(employeeDO);
    }

    /**
     * 获取员工列表
     */
    @Override
    public List<Employee> list(EmployeeListQuery query) {
        LambdaQueryWrapper<EmployeeDO> queryWrapper = new LambdaQueryWrapper<EmployeeDO>()
                .in(CollUtils.isNotEmpty(query.getIds()), EmployeeDO::getId, query.getIds())
                .in(CollUtils.isNotEmpty(query.getPassports()), EmployeeDO::getPassport, query.getPassports());
        List<EmployeeDO> employeeDOS = employeeMapper.selectList(queryWrapper);
        return Optional.ofNullable(employeeDOS).orElse(List.of()).stream().map(EmployeeInfraConvert.INSTANCE::toEmployee).toList();
    }
}
