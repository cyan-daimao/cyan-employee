package com.cyan.employee.adapter.http.department.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.employee.adapter.http.department.dto.DepartmentDTO;
import com.cyan.employee.application.department.bo.DepartmentBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author cy.Y
 */
@Mapper(uses = MapstructConvert.class)
public interface DepartmentAdapterConvert {

    DepartmentAdapterConvert INSTANCE = Mappers.getMapper(DepartmentAdapterConvert.class);

    DepartmentDTO toDepartmentDTO(DepartmentBO bo);
}
