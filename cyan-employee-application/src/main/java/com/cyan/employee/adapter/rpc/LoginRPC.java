package com.cyan.employee.adapter.rpc;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.adapter.http.employee.convert.EmployeeAdapterConvert;
import com.cyan.employee.application.employee.bo.EmployeeBO;
import com.cyan.employee.application.login.LoginService;
import com.cyan.employee.client.LoginClient;
import com.cyan.employee.client.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

/**
 * 员工rpc服务
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/rpc/v1/login")
public class LoginRPC implements LoginClient {

    private final LoginService loginService;

    public LoginRPC(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 验证员工Token
     */
    @PostMapping("/verify")
    public Response<EmployeeDTO> verify(@RequestParam("token") String token) {
        EmployeeBO employeeBO = loginService.verify(token);
        EmployeeDTO employeeDTO = EmployeeAdapterConvert.INSTANCE.toEmployeeDTO(employeeBO);
        return Response.success(employeeDTO);
    }
}
