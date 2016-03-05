package com.ligh.spring3.chapter2;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

/**
 * 使用实例工厂方法实例化Bean，使用这种方式不能指定class属性，
 * 此时必须使用factory-bean属性来指定工厂Bean，factory-method属性指定实例化Bean的方法，
 * 而且使用实例工厂方法允许指定方法参数，方式和使用构造器方式一样
 * @author Administrator
 *
 */
public class HelloApiInstanceFactory {
    
    public HelloApi newInstance(String message) {
        return new HelloImpl2(message);
    }
    
}
