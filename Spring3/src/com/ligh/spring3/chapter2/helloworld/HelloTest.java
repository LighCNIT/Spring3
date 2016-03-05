package com.ligh.spring3.chapter2.helloworld;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {
	@Test
	public void testHelloWorld(){
		//1.��ȡ�����ļ�ʵ����һ��IOC����
		ApplicationContext context = new ClassPathXmlApplicationContext("chapter2/helloworld.xml");
		//2.�������л�ȡBean  ����ӿھ���
		HelloApi helloApi = context.getBean("hello",HelloApi.class);
		//3.zִ��ҵ���߼�
		helloApi.sayHello();
	}

}
