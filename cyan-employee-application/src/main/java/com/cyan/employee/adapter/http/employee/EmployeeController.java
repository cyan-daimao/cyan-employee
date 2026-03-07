package com.cyan.employee.adapter.http.employee;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.adapter.http.employee.convert.EmployeeAdapterConvert;
import com.cyan.employee.client.dto.EmployeeDTO;
import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.employee.cmd.EmployeeCmd;
import com.cyan.employee.application.employee.service.EmployeeService;
import com.cyan.employee.domain.employee.query.EmployeeListQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 员工接口
 *
 * @author cy.Y
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 创建员工
     */
    @PostMapping("/save")
    public Response<EmployeeDTO> save(@RequestBody @Validated EmployeeCmd cmd) {
        EmployeeBO employeeBO = employeeService.save(cmd);
        EmployeeDTO employeeDTO = EmployeeAdapterConvert.INSTANCE.toEmployeeDTO(employeeBO);
        return Response.success(employeeDTO);
    }

    /**
     * 获取员工列表
     */
    @GetMapping("/list")
    public Response<List<EmployeeDTO>> list(EmployeeListQuery query) {
        List<EmployeeBO> list = employeeService.list(query);
        List<EmployeeDTO> employeeDTOS = Optional.ofNullable(list).orElse(Collections.emptyList()).stream().map(EmployeeAdapterConvert.INSTANCE::toEmployeeDTO).toList();
        return Response.success(employeeDTOS);
    }

    /**
     * 根据id查询员工
     */
    @GetMapping
    public Response<EmployeeDTO> get(@RequestParam("id") String id) {
        EmployeeBO employeeBO = employeeService.queryById(id);
        EmployeeDTO employeeDTO = EmployeeAdapterConvert.INSTANCE.toEmployeeDTO(employeeBO);
        return Response.success(employeeDTO);
    }
}
