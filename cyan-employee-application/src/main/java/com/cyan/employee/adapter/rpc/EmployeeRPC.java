package com.cyan.employee.adapter.rpc;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.adapter.http.employee.convert.EmployeeAdapterConvert;
import com.cyan.employee.adapter.rpc.convert.EmployeeRPCConvert;
import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.employee.service.EmployeeService;
import com.cyan.employee.client.EmployeeClient;
import com.cyan.employee.client.dto.EmployeeDTO;
import com.cyan.employee.client.query.EmployeeRPCListQuery;
import com.cyan.employee.client.query.EmployeeRPCQuery;
import com.cyan.employee.domain.employee.query.EmployeeListQuery;
import com.cyan.employee.domain.employee.query.EmployeeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * 员工rpc服务
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/rpc/v1/employees")
public class EmployeeRPC implements EmployeeClient {

    private final EmployeeService employeeService;

    public EmployeeRPC(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 查询员工
     *
     */
    @Override
    @GetMapping("/query")
    public Response<EmployeeDTO> query(EmployeeRPCQuery query) {
        EmployeeQuery employeeQuery = new EmployeeQuery()
                .setPassport(query.getPassport());
        EmployeeBO employeeBO = employeeService.queryOne(employeeQuery);
        EmployeeDTO employeeDTO = EmployeeAdapterConvert.INSTANCE.toEmployeeDTO(employeeBO);
        return Response.success(employeeDTO);
    }

    /**
     * 根据ID查询员工
     *
     */
    @Override
    @GetMapping("/{id}")
    public Response<EmployeeDTO> findById(@PathVariable String id) {
        EmployeeBO employeeBO = employeeService.queryById(id);
        EmployeeDTO employeeDTO = EmployeeAdapterConvert.INSTANCE.toEmployeeDTO(employeeBO);
        return Response.success(employeeDTO);
    }

    /**
     * 查询所有员工
     *
     */
    @Override
    @PostMapping("/list")
    public Response<List<EmployeeDTO>> list(@RequestBody EmployeeRPCListQuery query) {
        EmployeeListQuery employeeListQuery = EmployeeRPCConvert.INSTANCE.toEmployeeListQuery(query);
        List<EmployeeBO> list = employeeService.list(employeeListQuery);
        List<EmployeeDTO> data = Optional.ofNullable(list).orElse(List.of()).stream().map(EmployeeAdapterConvert.INSTANCE::toEmployeeDTO).toList();
        return Response.success(data);
    }
}
