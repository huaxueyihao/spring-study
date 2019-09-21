时间管理器

#### 1.目的
    
    1.项目练习，学以致用
    2.利用工具，帮助自我合理分配管理时间
    
#### 2.目标功能

    1.登陆注册工能未完成(简单的登录管理，登录未做session管理)
    2.权限角色未做
    3.时间管理模块:完成了实际行为记录，简单统计功能，计划的功能有：年计划，季计划，月计划，周计划，以及计划关联实际行为记录、
    相关的统计功能
    4.其他待定
    
#### 3.项目工程简介

    jdk1.8,springboot,mybatis,h2(默认h2 或者mysql执行sql)
    
    gardenia-plan 
    ├── README.md
    ├── code-generator                      mybatis逆向工程包，主要生成mapper文件和实体，需要idea配合使用
    │   ├── pom.xml                         
    │   └── src
    ├── time-manager-parent                 父工程，依赖管理中心
    │   ├── README.md
    │   ├── pom.xml                         依赖管理的pom文件
    │   └── src
    └── time-manager01                      第一个模块，完成时间管理功能模块
        ├── pom.xml
        └── src
        
        
time-manager01 结构

      1.resources 配置文件
      
      time-manager01  
      ├── pom.xml
      └── src
          └── main
              ├── java
              │   └── com                       功能包
              └── resources
                  ├── application.yml           yml配置文件，springboot的配置文件，数据源和mybatis的mapper文件配置在这个里面配置
                  ├── db                        数据库的schema.sql和data.sql文件包，创建表和模拟数据的sql语句
                  ├── fdfs                      fdfs没用到，文件服务器的配置文件
                  ├── logback-boot.xml          日志配置文件，未启用
                  ├── mapper                    mybatis的映射文件
                  ├── mybatis-config.xml        mybatis的配置文件
                  ├── static                    js和css的静态资源
                  └── templates                 html页面静态资源

      2.mvc模块
      
      time-manager01
      ├── pom.xml
      └── src
          └── main
              └── java
                  └── com
                      └── time
                          └── plan
                              ├── Plan01Application.java   springboot 项目启动类 (项目启动该类，就能正常运行，访问localhost:8080)
                              ├── bean                     和前端交互的传输数据的dto
                              ├── common                   共用模块
                              ├── config                   注解配置模块，目前，主要配置了拦截器和静态资源映射配置表
                              ├── controller               controller层
                              ├── exception                统一异常处理模块
                              ├── interceptor              拦截器，登陆拦截器
                              ├── mapper                   mapper接口层(dao层)
                              ├── model                    实体
                              ├── service                  服务service层
                              └── util                     工具类

      
