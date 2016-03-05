package com.ligh.spring3.chapter2;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

/**
 * 使用静态工厂方式实例化Bean，使用这种方式除了指定必须的class属性，
 * 还要指定factory-method属性来指定实例化Bean的方法，
 * 而且使用静态工厂方法也允许指定方法参数，spring IoC容器将调用此属性指定的方法来获取Bean
 * @author Ligh
 *
 */
public class HelloApiStaticFactory {
	
	public static HelloApi newInstance(String message) {
		return new HelloImpl2(message);
	}
	
}
