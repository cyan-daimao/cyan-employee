package com.cyan.employee.application.login.cmd;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 登陆命令
 * @author cy.Y
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class LoginCmd {

    /**
     * passport
     */
    @NotBlank(message = "passport不能为空")
    private String passport;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
