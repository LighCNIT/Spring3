package com.ligh.spring3.chapter2;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

/**
 * ʹ�þ�̬������ʽʵ����Bean��ʹ�����ַ�ʽ����ָ�������class���ԣ�
 * ��Ҫָ��factory-method������ָ��ʵ����Bean�ķ�����
 * ����ʹ�þ�̬��������Ҳ����ָ������������spring IoC���������ô�����ָ���ķ�������ȡBean
 * @author Ligh
 *
 */
public class HelloApiStaticFactory {
	
	public static HelloApi newInstance(String message) {
		return new HelloImpl2(message);
	}
	
}
