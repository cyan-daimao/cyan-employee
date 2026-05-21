package com.cyan.employee.adapter.http.employee.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.employee.client.dto.EmployeeDTO;
import com.cyan.employee.application.employee.bo.EmployeeBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author cy.Y
 * @version 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface EmployeeAdapterConvert {

    EmployeeAdapterConvert INSTANCE = Mappers.getMapper(EmployeeAdapterConvert.class);

    EmployeeDTO toEmployeeDTO(EmployeeBO employeeBO);
}