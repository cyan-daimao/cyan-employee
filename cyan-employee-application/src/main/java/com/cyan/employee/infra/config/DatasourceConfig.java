package com.cyan.employee.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author cy.Y
 */
@Configuration
@MapperScan("com.cyan.employee.infra.persistence.*")
public class DatasourceConfig {
}
