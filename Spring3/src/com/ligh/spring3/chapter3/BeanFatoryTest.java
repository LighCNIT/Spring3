package com.ligh.spring3.chapter3;

import org.junit.Test;

import com.ligh.spring3.chapter2.HelloImpl2;



public class BeanFatoryTest {
    
    @Test
    public void testPrototype() throws Exception {
        
        DefaultBeanFactory bf = new DefaultBeanFactory();
        
        BeanDefinition bd = new BeanDefinition();
        bd.setId("bean");
        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        bd.setClazz(HelloImpl2.class.getName());
        bf.registerBeanDefinition(bd);
        
        System.out.println(bf.getBean("bean") != bf.getBean("bean"));
    }

    @Test
    public void testSingleton() throws Exception {
       
        DefaultBeanFactory bf = new DefaultBeanFactory();
        
        BeanDefinition bd = new BeanDefinition();
        bd.setId("bean");
        bd.setScope(BeanDefinition.SCOPE_SINGLETON);
        bd.setClazz(HelloImpl2.class.getName());
        bf.registerBeanDefinition(bd);
        
        System.out.println(bf.getBean("bean") == bf.getBean("bean"));
    }
}

