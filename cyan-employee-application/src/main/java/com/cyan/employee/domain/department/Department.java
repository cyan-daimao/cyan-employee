package com.cyan.employee.domain.department;

import com.cyan.arch.common.api.SilentException;
import com.cyan.employee.domain.department.query.DepartmentQuery;
import com.cyan.employee.domain.department.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 部门
 *
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Department {
    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 主管id
     */
    private Long leaderId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 保存
     */
    public Department save(DepartmentRepository departmentRepository) {
        Department department = departmentRepository.findOne(new DepartmentQuery().setName(this.name));
        if (department != null) {
            throw new SilentException("部门已存在");
        }
        this.parentId = this.parentId == null ? 0 : this.parentId;
        return departmentRepository.save(this);
    }

    /**
     * 更新
     */
    public Department update(DepartmentRepository departmentRepository) {
        if (this.id == null) {
            throw new SilentException("更新时id不能为空");
        }
        return departmentRepository.update(this);
    }

    /**
     * 删除
     */
    public void delete(DepartmentRepository departmentRepository) {
        if (this.id == null) {
            throw new SilentException("删除时id不能为空");
        }
        departmentRepository.deleteById(this.id + "");
    }
}
