package com.cyan.employee.application.department.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.employee.application.department.bo.DepartmentBO;
import com.cyan.employee.application.department.cmd.DepartmentCmd;
import com.cyan.employee.domain.department.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 部门业务层转换
 * @author cy.Y
 * @version 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface DepartmentAppConvert {
    DepartmentAppConvert INSTANCE = Mappers.getMapper(DepartmentAppConvert.class);

    Department toDepartment(DepartmentCmd cmd);

    DepartmentBO toDepartmentBO(Department department);
}
