package com.cyan.employee.client;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.client.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 员工RPC客户端
 *
 * @author cy.Y
 * @since 1.0.0
 */
@FeignClient(name = "cyan-employee", path = "/rpc/v1/login", url = "${feign.cyan-employee.url}")
public interface LoginClient {

    /**
     * 验证员工Token
     */
    @PostMapping("/verify")
    Response<EmployeeDTO> verify(String token);
}
