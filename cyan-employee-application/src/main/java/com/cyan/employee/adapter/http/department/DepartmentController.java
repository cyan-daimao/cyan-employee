package com.cyan.employee.adapter.http.department;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.adapter.http.department.convert.DepartmentAdapterConvert;
import com.cyan.employee.adapter.http.department.dto.DepartmentDTO;
import com.cyan.employee.application.department.bo.DepartmentBO;
import com.cyan.employee.application.department.cmd.DepartmentCmd;
import com.cyan.employee.application.department.service.DepartmentService;
import com.cyan.employee.domain.department.query.DepartmentQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 部门接口
 *
 * @author cy.Y
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 创建部门
     *
     * @return 部门id
     */
    @PostMapping("/save")
    public Response<DepartmentDTO> create(@RequestBody @Validated DepartmentCmd cmd) {
        DepartmentBO departmentBO = departmentService.create(cmd);
        DepartmentDTO departmentDTO = DepartmentAdapterConvert.INSTANCE.toDepartmentDTO(departmentBO);
        return Response.success(departmentDTO);
    }

    /**
     * 删除部门
     *
     * @return 部门id
     */
    @PostMapping("/remove")
    public Response<Void> remove(@RequestBody DepartmentCmd cmd) {
        departmentService.remove(cmd);
        return Response.success();
    }

    /**
     * 查询部门
     *
     * @return 部门列表
     */
    @GetMapping("/query")
    public Response<DepartmentDTO> query(@RequestBody DepartmentQuery query) {
        DepartmentBO departmentBO = departmentService.queryOne(query);
        DepartmentDTO departmentDTO = DepartmentAdapterConvert.INSTANCE.toDepartmentDTO(departmentBO);
        return Response.success(departmentDTO);
    }

    /**
     * 查询部门列表
     */
    @GetMapping("/list")
    public Response<List<DepartmentDTO>> list(@RequestBody DepartmentQuery query) {
        List<DepartmentBO> departmentBOList = departmentService.list(query);
        List<DepartmentDTO> list = Optional.ofNullable(departmentBOList).orElse(List.of()).stream().map(DepartmentAdapterConvert.INSTANCE::toDepartmentDTO).toList();
        return Response.success(list);
    }

    /**
     * 根据id查询部门
     */
    @GetMapping
    public Response<DepartmentDTO> get(@RequestParam("id") String id) {
        DepartmentBO departmentBO = departmentService.queryOne(new DepartmentQuery().setId(id));
        DepartmentDTO departmentDTO = DepartmentAdapterConvert.INSTANCE.toDepartmentDTO(departmentBO);
        return Response.success(departmentDTO);
    }


}
