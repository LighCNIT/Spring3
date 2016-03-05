package com.ligh.spring3.chapter3;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ligh.spring3.chapter2.helloworld.HelloApi;



public class ThreadScopeTest {
    
    @Test
    public void testSingleThread() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/threadScope.xml");
        HelloApi bean1 = beanFactory.getBean("helloApi", HelloApi.class);
        HelloApi bean2 = beanFactory.getBean("helloApi", HelloApi.class);
        
        Assert.assertEquals(bean1, bean2);
    }

    @Test
    public void testTwoThread() throws InterruptedException {
        final BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/threadScope.xml");
        final HelloApi[] beans = new HelloApi[2];
        Thread thread1 = new Thread() {
            public void run() {
                beans[0] = beanFactory.getBean("helloApi", HelloApi.class);
            };
        };
        Thread thread2 = new Thread() {
            public void run() {
                beans[1] = beanFactory.getBean("helloApi", HelloApi.class);
            };
        };
        
        thread1.start(); thread1.sleep(1000);
        thread2.start(); thread2.sleep(1000);
        
        Assert.assertNotSame(beans[0], beans[1]);
    }
    
}

