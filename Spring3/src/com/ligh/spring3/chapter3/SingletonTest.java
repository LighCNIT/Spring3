package com.ligh.spring3.chapter3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

import com.ligh.spring3.chapter2.HelloImpl2;



public class SingletonTest {
    
    @Test
    public void testSingleton() throws Exception {
        
        ClassLoader classLoader = new SingletonClassLoader();
        
        Class clazz = classLoader.loadClass("cn.javass.spring.chapter3.bean.Singleton");
        
        Method getInstance = clazz.getDeclaredMethod("getInstance");
        Object singletonObj = getInstance.invoke(clazz);
        
        Field counterField = clazz.getDeclaredField("counter");
        counterField.setAccessible(true);
        Integer counter = (Integer) counterField.get(singletonObj);
        
        counterField.set(singletonObj, counter + 1);
        
        Assert.assertEquals(1, counterField.get(singletonObj));
        System.out.println(counterField.get(singletonObj));
        
        
        ClassLoader classLoader2 = new SingletonClassLoader();
        
        Class clazz2 = classLoader2.loadClass("cn.javass.spring.chapter3.bean.Singleton");
        
        Method getInstance2 = clazz2.getDeclaredMethod("getInstance");
        Object singletonObj2 = getInstance2.invoke(clazz2);
        
        Field counterField2 = clazz2.getDeclaredField("counter");
       
        counterField2.setAccessible(true);
        Integer counter2 = (Integer) counterField2.get(singletonObj2);
        counterField2.set(singletonObj2, counter2 + 1);
        
        Assert.assertEquals(1, counterField2.get(singletonObj2));
        System.out.println(counterField2.get(singletonObj2));
        
        
        
        
    }
    
    @Test
    public void testRegister() {
        SingletonBeanRegister register = new SingletonBeanRegister();
        register.registerSingleton("bean1", new HelloImpl2());
        HelloImpl2 bean1 = (HelloImpl2) register.getSingleton("bean1");
        bean1.sayHello();
        
        try {
            register.registerSingleton("bean1", new HelloImpl2());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}

