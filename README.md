# **项目简介**
&emsp;&emsp;本项目是一个基于 Spring Boot 框架开发的后端服务

# **项目开发**
## **技术栈**
* Java
* Spring
* Framework
* Hutool
* Fastjson
* JWT：使用 JSON Web Token 进行用户身份验证和安全控制。
* MySQL：数据存储
* Lombok：简化 Java 代码，通过注解减少样板代码的编写
* Spring Boot：使用 Spring Boot 构建后端服务，简化了项目配置和开发。
* Redis: 用于在项目中处理缓存和存储数据。
* Aliyun OSS SDK: 阿里云 OSS 对象存储服务的 Java SDK。

## **目录结构和重要文件说明**

* src/main/java/com/mandarin_mate/controller: 包含用户控制器代码。
* src/main/resources: 存放配置文件和静态资源。  
* MandarinMateServerApplication.java: 项目启动类。
* /pom.xml：Maven 项目配置文件，定义了项目的依赖和构建配置。

## **登陆注册相关接口实现**


### **功能实现**
1. 用户注册：通过 @Autowired 和 @Value 注解注入依赖项和配置信息。 

2. 用户登录：使用了Spring的注解 @GetMapping、@PostMapping 和 @RequestHeader 进行处理HTTP请求  

3. 微信登录：生成JWT令牌用于用户标识和安全校验  

4. 获取用户信息：通过调用 userService 和 mailService 这两个服务的方法实现业务逻辑。  

5. 用户头像自定义上传：用户头像上传时，逻辑包括文件大小限制、格式校验、文件保存等。   

6. 用户邮箱验证码发送：使用了 @RestController 和 @RequestMapping 注解标识RESTful API接口。
# **项目部署**
