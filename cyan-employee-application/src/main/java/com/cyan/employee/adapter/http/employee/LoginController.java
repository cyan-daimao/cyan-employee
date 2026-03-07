package com.cyan.employee.adapter.http.employee;

import com.cyan.arch.common.api.Response;
import com.cyan.employee.application.login.LoginService;
import com.cyan.employee.application.login.cmd.LoginCmd;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登陆
     */
    @PostMapping("/login")
    public Response<String> login(@RequestBody @Validated LoginCmd cmd) {
        String token = loginService.login(cmd.getPassport(), cmd.getPassword());
        return Response.success(token);
    }
}
