package com.ligh.spring3.chapter2;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

public class InstantiatingContainerTest {
    
    @Test
    public void testXmlBeanFactoryBaseOnFileSystem() {
        
        File file = new File("fileSystemConfig.xml");
        Resource resource = new FileSystemResource(file);
        
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        
        HelloApi helloApi = beanFactory.getBean("hello", HelloApi.class);
        
        helloApi.sayHello();
    }
    
    @Test
    public void testXmlBeanFactoryBaseOnClassPath() {
       
        Resource resource = new ClassPathResource("chapter2/classpath.xml");
        
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        
        HelloApi helloApi = beanFactory.getBean("hello", HelloApi.class);
        
        helloApi.sayHello();
    }
    
    @Test
    public void testClassPathXmlApplicationContext() {
        
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter2/classpath.xml");
        
        HelloApi helloApi = beanFactory.getBean("hello", HelloApi.class);
        
        helloApi.sayHello();
    }
    
    @Test
    public void testFileSystemApplicationContext() {
        
        BeanFactory beanFactory = new FileSystemXmlApplicationContext("fileSystemConfig.xml");
        
        HelloApi helloApi = beanFactory.getBean("hello", HelloApi.class);
        
        helloApi.sayHello();
    }
}
