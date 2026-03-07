package com.cyan.employee.adapter.rpc.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.employee.client.query.EmployeeRPCListQuery;
import com.cyan.employee.domain.employee.query.EmployeeListQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * 员工rpc转换
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface EmployeeRPCConvert {
    EmployeeRPCConvert INSTANCE = Mappers.getMapper(EmployeeRPCConvert.class);

    EmployeeListQuery toEmployeeListQuery(EmployeeRPCListQuery query);
}
