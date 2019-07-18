## 第一章 Spring整体架构
### 1.1 Spring的整体架构

    (1) Core container(核心容器)：Core，Beans，Context和Expression Language模块，Core和Beans提供Ioc和以来注入特性
    (2) Data Access/Integration：包括含有JDBC、ORM、OXM、JMS和Transaction模块。
    (3) Web上下文模块建立在应用程序上下文模块智商，为基于Web的应用程序提供了上下文，包括：Web、Web-Servlet、Web-Struts和Web-Prolet模块。
     
## 第二章 容器的基本实现
### 2.1 容器的基本用法
    
#### 2.1.1 示例代码
    package com.springmvc.study.chapter02.bean;
    
    public class MyTestBean
    {
    
        private String testStr = "testStr";
    
        public String getTestStr(){
            return testStr;
        }
    
        public void setTestStr(String testStr) {
            this.testStr = testStr;
        }
    }
    
#### 2.1.2 配置文件
    
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    
        <bean id="myTestBean" class="com.springmvc.study.chapter02.bean.MyTestBean" ></bean>
    
    
    </beans>
    
#### 2.1.3 测试类
    
    package com.springmvc.study.chapter02.bean;
    
    import org.junit.Test;
    import org.springframework.beans.factory.xml.XmlBeanFactory;
    import org.springframework.core.io.ClassPathResource;
    import static org.junit.Assert.*;
    
    @SuppressWarnings("deprecation")
    public class BeanFactoryTest {
    
        @Test
        public void testSimpleLoad(){
    
            XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/chapter02/applicationContext.xml"));
            MyTestBean bean = (MyTestBean)bf.getBean("myTestBean");
            assertEquals("testStr",bean.getTestStr());
    
        }
    
    
    }

### 2.2 功能分析
    
    这段代码完成的功能：
    （1）读取配置文件applicationContext.xml
    （2）根据配置文件找到对应的类，并实例化
    （3）调用示例化后的实例

### 2.4 Spring的结构组成
#### 2.4.2 核心类介绍

    DefaultListableBeanFactory、XmlBeanFactory关系
    1.1 XmlBeanFactory继承DefaultListableBeanFactory
    1.2 DefaultListableBeanFactory是Spring注册及加载bean的默认实现。
    1.3 XmlBeanFactory自定义XML读取器XmlBeanDefinitionReader，实现了个性化的BeanDefinitionReader。
    1.4 DefaultListableBeanFactory继承AbstractAutowireCapableBeeanFactory并实现了COnfigurableBeanFactory以及BeanDefinitionRegistry接口。
    
    类图src/main/resources/spring/chapter02/DefaultListableBeanFactory.pdf
    1、ConfigurableBeanFactory:提供配置factory的各种法法。
    2、ListableBeanFactory:根据各种添加获取bean的配置清单。
    3、AbstractBeanFactory:综合FactoryBeanRegistrySupport和ConfigurableBeanFactory的功能
    4、AutowireCapableBeanFactory:提供创建bean、自动注入、初始化以及应用bean的处理器
    5、AbstractAutowireCapableBeanFactory:综合AbstractBeanFactory并对接口AutowireCapableBeanFactory进行实现
    6、ConfigurableListableBeanFactory:BeanFactory配置清单
    7、DefaultListableBeanFactory:综合上面的功能，主要是对bean注册后的处理
    
    XmlBeanDefinitionReader(读取、解析、注册)
    1、ResourceLoader:定义资源加载器，主要应用于根绝给定的资源文件地址返回对应的Resource
    2、BeanDefinitionReader：主要定义资源文件读取并转换为BeanDefinition的各个功能
    3、EnvironmentCapable：定义获取Environment方法
    4、DocumentLoader：定义从资源文件加载带装换为Document功能
    5、AbstractBeanDefinitionReader：对EnvironmentCapable、BeanDefinitionReader类定义功能进行实现。
    6、BeanDefinitionDocumentReader：定义读取Document并注册BeanDefinition功能
    7、BeanDefinitionParserDelegate：定义解析Element的各种方法。
    
    ![avatar](http://pus7zrxh4.bkt.clouddn.com/XmlBeanDefinitionReader.png)
    
    (1)通过继承自AbstractBenaDefinitionReader中的方法，来使用ResourceLoader将资源文件路径转换为对应的Resource文件
    (2)通过DocumentLoader对Resource文件进行转换，将Resource文件转为Document文件。
    (3)通过实现接口BeanDefinitionDocumentReader的DefaultBeanDefinitionDocumentReader类对Document进行解析，并使用BeanDefinitionParserDelegate对Element进行解析。
    
### 2.5 容器的基础XmlBeanFactory
    
    BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
    
    
    
    
    
    
    
    
    
    
