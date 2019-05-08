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

