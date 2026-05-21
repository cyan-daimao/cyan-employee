package com.cyan.employee.infra.persistence.department.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyan.arch.common.util.Convert;
import com.cyan.arch.common.util.StrUtils;
import com.cyan.employee.domain.department.Department;
import com.cyan.employee.domain.department.query.DepartmentQuery;
import com.cyan.employee.domain.department.repository.DepartmentRepository;
import com.cyan.employee.infra.persistence.department.convert.DepartmentInfraConvert;
import com.cyan.employee.infra.persistence.department.dos.DepartmentDO;
import com.cyan.employee.infra.persistence.department.mapper.DepartmentMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 部门仓储服务
 *
 * @author cy.Y
 * @version 1.0.0
 */
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final DepartmentMapper departmentMapper;

    public DepartmentRepositoryImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 根据id查询部门
     *
     * @param id 部门id
     * @return 部门
     */
    @Override
    public Department findById(String id) {
        DepartmentDO departmentDO = departmentMapper.selectById(id);
        return DepartmentInfraConvert.INSTANCE.toDepartment(departmentDO);
    }

    /**
     * 保存部门
     *
     * @param department 部门
     * @return 部门
     */
    @Override
    public Department save(Department department) {
        DepartmentDO departmentDO = DepartmentInfraConvert.INSTANCE.toDepartmentDO(department);
        departmentMapper.insert(departmentDO);
        departmentDO = departmentMapper.selectById(departmentDO.getId());
        return DepartmentInfraConvert.INSTANCE.toDepartment(departmentDO);
    }

    /**
     * 更新部门
     *
     * @param department 部门
     * @return 部门
     */
    @Override
    public Department update(Department department) {
        DepartmentDO departmentDO = DepartmentInfraConvert.INSTANCE.toDepartmentDO(department);
        departmentMapper.updateById(departmentDO);
        departmentDO = departmentMapper.selectById(departmentDO.getId());
        return DepartmentInfraConvert.INSTANCE.toDepartment(departmentDO);
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    @Override
    public void deleteById(String id) {
        departmentMapper.deleteById(Convert.toLong(id));
    }

    /**
     * 根据名称查询部门
     *
     * @param query 查询条件
     * @return 部门
     */
    @Override
    public Department findOne(DepartmentQuery query) {
        LambdaQueryWrapper<DepartmentDO> queryWrapper = new LambdaQueryWrapper<DepartmentDO>()
                .eq(StrUtils.isNotBlank(query.getId()), DepartmentDO::getId, query.getId())
                .eq(StrUtils.isNotBlank(query.getName()), DepartmentDO::getName, query.getName());
        DepartmentDO departmentDO = departmentMapper.selectOne(queryWrapper);
        return DepartmentInfraConvert.INSTANCE.toDepartment(departmentDO);
    }

    /**
     * 查询部门
     *
     * @param query 查询条件
     * @return 部门
     */
    @Override
    public List<Department> list(DepartmentQuery query) {
        LambdaQueryWrapper<DepartmentDO> queryWrapper = new LambdaQueryWrapper<DepartmentDO>()
                .eq(StrUtils.isNotBlank(query.getName()), DepartmentDO::getName, query.getName());
        List<DepartmentDO> departmentDOS = departmentMapper.selectList(queryWrapper);
        return Optional.ofNullable(departmentDOS).orElse(Collections.emptyList()).stream().map(DepartmentInfraConvert.INSTANCE::toDepartment).toList();
    }
}
