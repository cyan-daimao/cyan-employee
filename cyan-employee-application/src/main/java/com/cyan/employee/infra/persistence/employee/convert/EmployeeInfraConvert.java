package com.cyan.employee.infra.persistence.employee.convert;

import com.cyan.arch.base.mapstruct.MapstructConvert;
import com.cyan.employee.domain.employee.Employee;
import com.cyan.employee.infra.persistence.employee.dos.EmployeeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 员工仓储服务转换
 *
 * @author cy.Y
 * @version 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface EmployeeInfraConvert {

    EmployeeInfraConvert INSTANCE = Mappers.getMapper(EmployeeInfraConvert.class);

    Employee toEmployee(EmployeeDO employeeDO);

    EmployeeDO toEmployeeDO(Employee employee);
}
