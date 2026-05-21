package com.cyan.employee.application.employee.convert;

import com.cyan.arch.base.mapstruct.MapstructConvert;
import com.cyan.employee.application.department.bo.DepartmentBO;
import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.employee.cmd.EmployeeCmd;
import com.cyan.employee.domain.employee.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author cy.Y
 * @version 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface EmployeeAppConvert {
    EmployeeAppConvert INSTANCE = Mappers.getMapper(EmployeeAppConvert.class);

    EmployeeBO toEmployeeBO(Employee employee);

    @Mapping(target = "id", source = "id")
    DepartmentBO toDepartmentBO(Long id);


    Employee toEmployee(EmployeeCmd cmd);
}
