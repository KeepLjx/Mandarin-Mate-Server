# **项目简介**
&emsp;&emsp;本项目是一个基于 Spring Boot 框架开发的后端服务

# 数据库设计

## **系统功能概要**
* **进度表**：存储用户的学习进度
* **词书表**：存储平台所有可供学习的词书
* **词语表**：存储词语信息
* **用户表**：存储用户登录、收藏、是否为会员等信息，并且与评估表和收藏表关联
* **评估表**：包含用户每次语音输入后系统的得分和词语ID
* **收藏表**：包含用户ID以及用户收藏的单词ID。
## **数据库关系图**
<div align=center>
<img src="image/er.png" width="60%">
</div>

## **数据库表结构**
* **user[用户表]**
  
| 字段     | 名称     | 数据类型 | 主键    | 非空     | 默认值  | 
| -------- | -------- | -------- |-------- | -------- | -------- |
| user_id | 用户ID | BIGINT | √ | √ |  | 
| nick_name | 用户昵称| VARCHAR |
| password | 密码 | VARCHAR |
| isVIP | 是否会员 | TINYINT |  | √ |  |
| avatar_path | 头像路径 | VARCHAR |
| learning_level | 学习等级| TINYINT | 
| phone | 用户电话 | VARCHAR |
| user_mail | 用户邮箱 | VARCHAR | 
| open_id | 微信小程序用户ID | VARCHAR |
| create_time | 注册时间 | DETETIME | 


* **schedule[进度表]**
  
| 字段     | 名称     | 数据类型    | 主键   | 非空    |默认值   | 
| -------- | -------- | -------- |-------- | -------- | -------- |
| user_id | 用户ID | BIGINT | √ | √ |
| book_id | 词书ID | BIGINT | √ | √ |
| is_delete | 是否删除 | INT |
| completed | 完成进度 | BIGINT |

* **book[词书表]**
  

| 字段     | 名称     | 数据类型    | 主键   | 非空    |默认值   | 
| -------- | -------- | -------- |-------- | -------- | -------- |
| book_id | 词书ID| BIGINT | √ | √ |   | 
| book_img  | 词书封面 | VARCHAR|   |  |  | 
| book_intro  | 词书简介 | VARCHAR |   |  |  | 
| book_name  | 词书名称 | VARCHAR |   |  |  |
| book_level  | 词书难易等级 | VARCHAR |   |  |  | 


* **words[词语表]**
  

| 字段     | 名称     | 数据类型    | 主键   | 非空    |默认值   | 
| -------- | -------- | -------- |-------- | -------- | -------- |
| words_id | 词语ID | BIGINT | √ | √ |   | 
| book_id | 词书ID | BIGINT | √ | √ |   | 
| words_spell | 拼音拼写 | VARCHAR |
| example | 例句 | VARCHAR |
| content | 词表内容| VARCHAR |  | √ |  |
| type_tag | 词语种类 |VARCHAR |
| voice_path |  | VARCHAR |


* **evaluation[评估表]**
  

| 字段     | 名称     | 数据类型    | 主键   | 非空    |默认值   | 
| -------- | -------- | -------- |-------- | -------- | -------- |
| user_id | 用户ID | BIGINT | √ | √ |   | 
| words_id | 词语ID | BIGINT |
| score | 得分 | BIGINT |


* **collection[收藏表]**
  

| 字段     | 名称     | 数据类型    | 主键   | 非空    |默认值   | 
| -------- | -------- | -------- |-------- | -------- | -------- |
| user_id | 用户ID | BIGINT |   | √ |   |
| words_id | 词语ID | BIGINT |   | √ |   |


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
1. 用户注册(register)：通过 @Autowired 和 @Value 注解注入依赖项和配置信息。 

2. 用户登录(login)：使用了Spring的注解 @GetMapping、@PostMapping 和 @RequestHeader 进行处理HTTP请求  

3. 微信登录(weChatLogin)：生成JWT令牌用于用户标识和安全校验  

4. 获取用户信息(getUserInfo)：通过调用 userService 和 mailService 这两个服务的方法实现业务逻辑。  

5. 用户头像自定义上传(upload)：用户头像上传时，逻辑包括文件大小限制、格式校验、文件保存等。   

6. 用户邮箱验证码发送(userMail)：使用了 @RestController 和 @RequestMapping 注解标识RESTful API接口。
# **项目部署**
