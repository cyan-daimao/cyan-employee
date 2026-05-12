# cyan-employee

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.13-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 3.3.13">
  <img src="https://img.shields.io/badge/Spring%20Cloud-2023.x-6DB33F?logo=spring&logoColor=white" alt="Spring Cloud">
  <img src="https://img.shields.io/badge/MyBatis--Plus-3.x-FF6C00?logo=MyBatis&logoColor=white" alt="MyBatis-Plus">
  <img src="https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/MySQL-8.x-4479A1?logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Redis-7.x-DC382D?logo=redis&logoColor=white" alt="Redis">
  <img src="https://img.shields.io/badge/MapStruct-1.x-CA8319?logo=java&logoColor=white" alt="MapStruct">
  <img src="https://img.shields.io/badge/Lombok-1.x-BC2055?logo=java&logoColor=white" alt="Lombok">
</p>

<p align="center">
  <b>数据平台 · 员工管理与登录认证服务</b>
</p>

---

## 项目简介

`cyan-employee` 是 Cyan 数据平台的员工服务模块，负责企业员工的统一管理及登录认证能力。项目基于 **Java 21** 与 **Spring Boot 3** 构建，遵循 Maven 多模块架构，向上提供 RESTful API 与 Feign 客户端，向下沉淀领域模型与通用登录组件。

- **坐标**: `com.cyan:cyan-employee:1.0-SNAPSHOT`
- **JDK 版本**: Java 21
- **构建工具**: Apache Maven
- **基础依赖**: [cyan-arch](https://github.com/cyan-daimao/cyan-arch)（通用架构库）

---

## 模块说明

| 模块 | 坐标 | 职责 |
|------|------|------|
| `cyan-employee-application` | `com.cyan:cyan-employee-application` | **应用层 / 启动入口**。封装员工管理的核心业务逻辑与对外 RESTful API，集成 MyBatis-Plus、Redis、MySQL，最终打包为可执行 Spring Boot JAR。 |
| `cyan-employee-client` | `com.cyan:cyan-employee-client` | **客户端 SDK**。提供 DTO、Feign 接口与常量定义，供其他微服务通过 `@FeignClient` 远程调用员工服务。零业务逻辑，轻量可复用。 |
| `cyan-employee-login` | `com.cyan:cyan-employee-login` | **登录认证模块**。封装认证领域模型、Token 策略及登录通用逻辑，可被 application 层依赖复用，也可独立扩展为多端认证能力。 |

### 依赖关系

```
cyan-employee-application
├── cyan-employee-client
├── cyan-employee-login
│   └── cyan-employee-client
└── cyan-arch (arch-base)
```

---

## 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Java | 21 | 运行时与语言特性 |
| Spring Boot | 3.3.13 | Web 容器、自动配置、可执行 JAR |
| Spring Cloud OpenFeign | 2023.x | 声明式 HTTP 客户端 / 服务间调用 |
| MyBatis-Plus | 3.x | ORM 框架与分页增强 |
| MySQL Connector/J | 8.x | 关系型数据持久化 |
| Spring Data Redis | — | 缓存与会话存储 |
| MapStruct | 1.x | 编译期类型安全对象映射 |
| Jakarta Validation | — | 参数校验与约束注解 |
| Lombok | 1.x | 样板代码自动生成 |
| Maven | 3.x | 项目构建与依赖管理 |

---

## 快速开始

### 1. 环境准备

- JDK 21+
- Maven 3.9+
- MySQL 8.x（已创建对应数据库）
- Redis 7.x（可选，用于缓存/会话）
- 本地已安装并构建 [cyan-arch](https://github.com/cyan-daimao/cyan-arch) 基础库

### 2. 克隆与构建

```bash
git clone git@github.com:cyan-daimao/cyan-employee.git
cd cyan-employee

# 安装全部模块到本地仓库
mvn clean install
```

### 3. 启动应用

```bash
cd cyan-employee-application
mvn spring-boot:run
```

或直接使用已构建的可执行 JAR：

```bash
java -jar cyan-employee-application/target/cyan-employee.jar \
  --spring.profiles.active=dev
```

### 4. 在其他服务中引用 Client

```xml
<dependency>
  <groupId>com.cyan</groupId>
  <artifactId>cyan-employee-client</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

```java
@FeignClient(name = "cyan-employee", path = "/api/employee")
public interface EmployeeClient extends EmployeeApi {
}
```

---

## 部署说明

项目配置有 Nexus 私有仓库，执行以下命令可将快照或正式发布包推送至仓库：

```bash
# 部署快照 / 正式版本
mvn clean deploy
```

- **Release 仓库**: `http://nexus.cyan.com/repository/maven-releases/`
- **Snapshot 仓库**: `http://nexus.cyan.com/repository/maven-snapshots/`

---

## 项目结构

```
cyan-employee/
├── pom.xml                          # 父 POM：统一依赖管理与插件配置
├── cyan-employee-application/
│   └── pom.xml                      # 应用层 + 启动类
├── cyan-employee-client/
│   └── pom.xml                      # Feign 接口与 DTO
└── cyan-employee-login/
    └── pom.xml                      # 登录认证领域逻辑
```

---

## 开源协议

本项目内部使用，协议待定。

---

> 如有问题或建议，请联系数据平台研发团队。
