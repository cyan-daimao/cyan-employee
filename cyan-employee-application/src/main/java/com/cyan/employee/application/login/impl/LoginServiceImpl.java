package com.cyan.employee.application.login.impl;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.SilentException;
import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.employee.convert.EmployeeAppConvert;
import com.cyan.employee.application.login.LoginService;
import com.cyan.employee.domain.employee.Employee;
import com.cyan.employee.domain.employee.query.EmployeeQuery;
import com.cyan.employee.domain.employee.repository.EmployeeRepository;
import com.cyan.employee.infra.util.BCryptUtil;
import com.cyan.employee.infra.util.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 登陆服务
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final EmployeeRepository employeeRepository;
    private final TokenUtils tokenUtils;
    @Resource
    private RedisTemplate<String, Employee> redisTemplate;

    public LoginServiceImpl(EmployeeRepository employeeRepository, TokenUtils tokenUtils) {
        this.employeeRepository = employeeRepository;
        this.tokenUtils = tokenUtils;
    }

    /**
     * 登陆
     *
     * @param passport    邮箱
     * @param password 密码
     * @return token
     */
    @Override
    public String login(String passport, String password) {
        Employee employee = employeeRepository.findOne(new EmployeeQuery().setPassport(passport));
        if (employee == null){
            throw new SilentException("用户不存在");
        }
        boolean matches = BCryptUtil.matches(password, employee.getPassword());
        if (!matches) {
            throw new SilentException("用户名密码错误");
        }
        try {
            String token = tokenUtils.generateToken(employee.getId());
            redisTemplate.opsForValue().set("cyan-employee:"+employee.getId(), employee, 30, TimeUnit.DAYS);
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证
     *
     * @param token token
     * @return 员工
     */
    @Override
    public EmployeeBO verify(String token) {
        try {
            TokenUtils.TokenParseResult tokenParseResult = tokenUtils.parseAndVerifyToken(token);
            Assert.isTrue(tokenParseResult.isValid(), new SilentException(tokenParseResult.getMsg()));
            String userId = tokenParseResult.getUserId();
            Employee employee = getEmployeeFromCache(userId);
            Assert.notNull(employee, new SilentException("登陆信息失效,请重新登录"));
            return EmployeeAppConvert.INSTANCE.toEmployeeBO(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从缓存获取员工，兼容序列化版本变更：反序列化失败时自动从数据库重新加载并刷新缓存
     */
    private Employee getEmployeeFromCache(String userId) {
        String cacheKey = "cyan-employee:" + userId;
        try {
            return redisTemplate.opsForValue().get(cacheKey);
        } catch (Exception e) {
            // 序列化异常（如类结构变更导致 serialVersionUID 不匹配），删除旧缓存并从数据库重新加载
            redisTemplate.delete(cacheKey);
            Employee employee = employeeRepository.findById(userId);
            if (employee != null) {
                redisTemplate.opsForValue().set(cacheKey, employee, 30, TimeUnit.DAYS);
            }
            return employee;
        }
    }
}
