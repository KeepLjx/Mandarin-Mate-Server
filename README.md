# **项目简介**
&emsp;&emsp;本项目是一款专注于汉语自主学习的软件,以纠正用户发音为核心，通过深度卷积神经网络、语音识别等技术，为用户提供个性化学习建议和丰富的学习资源，实现了一个便捷高效的多功能语言学习平台。  
&emsp;&emsp;语伴使用Spring Boot和uniapp进行开发，并利用vue3技术与vite项目包配置工具对项目进行业务开发以及项目管理。
# **项目开发**
## **开发环境**
## **目录结构和重要文件说明**

* src/main/java/com/mandarin_mate/controller: 包含用户控制器代码。
* src/main/resources: 存放配置文件和静态资源。  
* MandarinMateServerApplication.java: 项目启动类。  

## **登陆注册**
### **技术栈**
* Java
* Spring
* Framework
* Hutool
* JWT
* Spring Boot
### **功能实现**
1. 用户注册：通过 @Autowired 和 @Value 注解注入依赖项和配置信息。 

2. 用户登录：使用了Spring的注解 @GetMapping、@PostMapping 和 @RequestHeader 进行处理HTTP请求  

3. 微信登录：生成JWT令牌用于用户标识和安全校验  

4. 获取用户信息：通过调用 userService 和 mailService 这两个服务的方法实现业务逻辑。  

5. 用户头像自定义上传：用户头像上传时，逻辑包括文件大小限制、格式校验、文件保存等。   

6. 用户邮箱验证码发送：使用了 @RestController 和 @RequestMapping 注解标识RESTful API接口。
# **项目部署**
