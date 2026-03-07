package com.cyan.employee.client;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.client.dto.EmployeeDTO;
import com.cyan.employee.client.query.EmployeeRPCListQuery;
import com.cyan.employee.client.query.EmployeeRPCQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 员工服务
 * @author cy.Y
 * @since 1.0.0
 */
@FeignClient(name = "cyan-employee",contextId = "employeeClient", path = "/rpc/v1/employees", url = "${feign.cyan-employee.url}")
public interface EmployeeClient {

    /**
     * 查询员工
     */
    @GetMapping("/query")
    Response<EmployeeDTO> query(EmployeeRPCQuery query);

    /**
     * 根据ID查询员工
     */
    @GetMapping("/{id}")
    Response<EmployeeDTO> findById(@PathVariable String id);

    /**
     * 查询所有员工
     */
    @PostMapping("/list")
    Response<List<EmployeeDTO>> list(@RequestBody EmployeeRPCListQuery query);
}
