package com.cyan.employee.domain.employee;

import com.cyan.arch.common.api.SilentException;
import com.cyan.arch.common.util.StrUtils;
import com.cyan.employee.domain.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工表
 *
 * @author cy.Y
 * @version 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 职位
     */
    private String staffNumber;

    /**
     * 通行证
     */
    private String passport;

    /**
     * 员工所属部门
     */
    private List<Long> departmentId;

    /**
     * 姓名
     */
    private String cnName;

    /**
     * 姓名
     */
    private String enName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 职位
     */
    private String jobTitle;

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
    public Employee save(EmployeeRepository employeeRepository) {
        if (StrUtils.isBlank(cnName) || StrUtils.isBlank(enName)) {
            throw new SilentException("中文名,英文名不能为空");
        }
        Long count = employeeRepository.count(true);
        this.staffNumber = count + 1 + "";
        this.passport = enName + staffNumber;
        return employeeRepository.save(this);
    }

    /**
     * 更新
     */
    public Employee update(EmployeeRepository employeeRepository) {
        if (StrUtils.isBlank(this.id)) {
            throw new SilentException("更新时id不能为空");
        }
        return employeeRepository.update(this);
    }

    /**
     * 删除
     */
    public void delete(EmployeeRepository employeeRepository) {
        if (StrUtils.isBlank(this.id)) {
            throw new SilentException("删除时id不能为空");
        }
        employeeRepository.deleteById(this.id);
    }
}
