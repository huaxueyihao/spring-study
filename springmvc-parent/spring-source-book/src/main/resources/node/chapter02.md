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
    
    XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/chapter02/applicationContext.xml"));
   
#### 2.5.1 配置文件封装
    
    
    InputStreamSource封装任何能返回InputStream的类，如File，Classpath下的资源和Byte Array。
    
    package org.springframework.core.io;
    
    import java.io.IOException;
    import java.io.InputStream;
    
    public interface InputStreamSource {
        InputStream getInputStream() throws IOException;
    }
   
    
    
    Resouce接口抽象了所有Spring内部使用到的底层资源：File、URL、Classpath等。
    
    package org.springframework.core.io;
    
    import java.io.File;
    import java.io.IOException;
    import java.net.URI;
    import java.net.URL;
    
    public interface Resource extends InputStreamSource {
        boolean exists();
        
        // 可读性
        boolean isReadable();
    
        // 是否处于打开
        boolean isOpen();
    
        URL getURL() throws IOException;
    
        URI getURI() throws IOException;
    
        File getFile() throws IOException;
    
        long contentLength() throws IOException;
    
        long lastModified() throws IOException;
    
        Resource createRelative(String var1) throws IOException;
    
        String getFilename();
    
        String getDescription();
    }
    
    对不同来源的资源文件都有相应的Resource实现：
    文件(FileSystemResource),
    Classpath资源(ClassPathResource),
    URL资源(UrlResource),
    InputStream资源(InputStreamResource),
    Byte数组(ByteArrayResouce)
    
    ClassPathResource获得InputStream
    
    public InputStream getInputStream() throws IOException {
        InputStream is;
        if (this.clazz != null) {
            is = this.clazz.getResourceAsStream(this.path);
        } else if (this.classLoader != null) {
            is = this.classLoader.getResourceAsStream(this.path);
        } else {
            is = ClassLoader.getSystemResourceAsStream(this.path);
        }

        if (is == null) {
            throw new FileNotFoundException(this.getDescription() + " cannot be opened because it does not exist");
        } else {
            return is;
        }
    }
    
    FileSystemResource获得InputStream
    
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
    
    Resource完成对配置文件的封装工作，之后配置文件的读取工作交给XmlBeanDefinitionReader
    
    
#### 2.5.2 加载Bean
    
    (1)封装资源文件。进入XmlBeanDefinitionReader后首先对参数Resource使用EncodedResource类进行封装
    (2)获取输入流。从Resource中获取对应的InputStream并构造InputSource
    (3)通过构造的InputSource实例和Resource实例继续调用函数doLoadBeanDefinitions。
    
    
    XmlBeanFactory 代码
    
    package org.springframework.beans.factory.xml;
    
    import org.springframework.beans.BeansException;
    import org.springframework.beans.factory.BeanFactory;
    import org.springframework.beans.factory.support.DefaultListableBeanFactory;
    import org.springframework.core.io.Resource;
    
    /** @deprecated */
    @Deprecated
    public class XmlBeanFactory extends DefaultListableBeanFactory {
        private final XmlBeanDefinitionReader reader;
    
        public XmlBeanFactory(Resource resource) throws BeansException {
            this(resource, (BeanFactory)null);
        }
    
        public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException {
            super(parentBeanFactory);
            this.reader = new XmlBeanDefinitionReader(this);
            // loadBeanDefinition(resource) 方法
            this.reader.loadBeanDefinitions(resource);
        }
    }
    
    
    XmlBeanDefinitionReader的loadBeanDefinition方法
    
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        // EncodedResource 的作用，主要是对文件的编码进行处理的
        return this.loadBeanDefinitions(new EncodedResource(resource));
    }
    
    public int loadBeanDefinitions(EncodedResource encodedResource) throws BeanDefinitionStoreException {
        Assert.notNull(encodedResource, "EncodedResource must not be null");
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Loading XML bean definitions from " + encodedResource);
        }
        // 通过属性来记录已经加载的资源
        Set<EncodedResource> currentResources = (Set)this.resourcesCurrentlyBeingLoaded.get();
        if (currentResources == null) {
            currentResources = new HashSet(4);
            this.resourcesCurrentlyBeingLoaded.set(currentResources);
        }
        
        if (!((Set)currentResources).add(encodedResource)) {
            throw new BeanDefinitionStoreException("Detected cyclic loading of " + encodedResource + " - check your import definitions!");
        } else {
            int var5;
            try {
                InputStream inputStream = encodedResource.getResource().getInputStream();

                try {
                    // 从encodedResource中获取已经封装的Resource对象并在此从Resouce中获取其中的InputStream
                    InputSource inputSource = new InputSource(inputStream);
                    if (encodedResource.getEncoding() != null) {
                        inputSource.setEncoding(encodedResource.getEncoding());
                    }
                    // 真正的进入逻辑核心部分
                    var5 = this.doLoadBeanDefinitions(inputSource, encodedResource.getResource());
                } finally {
                    inputStream.close();
                }
            } catch (IOException var15) {
                throw new BeanDefinitionStoreException("IOException parsing XML document from " + encodedResource.getResource(), var15);
            } finally {
                ((Set)currentResources).remove(encodedResource);
                if (((Set)currentResources).isEmpty()) {
                    this.resourcesCurrentlyBeingLoaded.remove();
                }

            }

            return var5;
        }
    }
    
    protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource) throws BeanDefinitionStoreException {
        // (1) 获取对XML文件的验证模式
        // (2) 加载XML文件，并得到对应的Document
        // (3) 根据返回的Document注册Bean
        try {
            Document doc = this.doLoadDocument(inputSource, resource);
            return this.registerBeanDefinitions(doc, resource);
        } catch (BeanDefinitionStoreException var4) {
            throw var4;
        } catch (SAXParseException var5) {
            throw new XmlBeanDefinitionStoreException(resource.getDescription(), "Line " + var5.getLineNumber() + " in XML document from " + resource + " is invalid", var5);
        } catch (SAXException var6) {
            throw new XmlBeanDefinitionStoreException(resource.getDescription(), "XML document from " + resource + " is invalid", var6);
        } catch (ParserConfigurationException var7) {
            throw new BeanDefinitionStoreException(resource.getDescription(), "Parser configuration exception parsing XML from " + resource, var7);
        } catch (IOException var8) {
            throw new BeanDefinitionStoreException(resource.getDescription(), "IOException parsing XML document from " + resource, var8);
        } catch (Throwable var9) {
            throw new BeanDefinitionStoreException(resource.getDescription(), "Unexpected exception parsing XML document from " + resource, var9);
        }
    }
        
    
    EncodedResource的getReader方法
    public Reader getReader() throws IOException {
        if (this.charset != null) {
            // 处理字符集
            return new InputStreamReader(this.resource.getInputStream(), this.charset);
        } else {
            return this.encoding != null ? new InputStreamReader(this.resource.getInputStream(), this.encoding) : new InputStreamReader(this.resource.getInputStream());
        }
    }
    
    
    
    
    
    
    
    
    
