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
