package com.ligh.spring3.chapter3;

import org.junit.Test;

import com.ligh.spring3.chapter2.HelloImpl2;



public class BeanFatoryTest {
    
    @Test
    public void testPrototype() throws Exception {
        //1.创建Bean工厂
        DefaultBeanFactory bf = new DefaultBeanFactory();
        //2.创建原型，Bean定义
        BeanDefinition bd = new BeanDefinition();
        bd.setId("bean");
        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        bd.setClazz(HelloImpl2.class.getName());
        bf.registerBeanDefinition(bd);
        //对于原型Bean每次应该返回一个全新的Bean
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
        //对于单例bean会去缓存池中查找是都有该Bean
        System.out.println(bf.getBean("bean") == bf.getBean("bean"));
    }
}

