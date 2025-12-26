package com.cyan.employee.infra.persistence.department.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.employee.domain.department.Department;
import com.cyan.employee.infra.persistence.department.dos.DepartmentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author cy.Y
 */
@Mapper(uses = MapstructConvert.class)
public interface DepartmentInfraConvert {

   DepartmentInfraConvert INSTANCE = Mappers.getMapper(DepartmentInfraConvert.class);

   Department toDepartment(DepartmentDO departmentDO);

   DepartmentDO toDepartmentDO(Department department);
}
