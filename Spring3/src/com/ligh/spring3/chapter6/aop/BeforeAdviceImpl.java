package com.ligh.spring3.chapter6.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * ����ǰ��֪ͨAPI��ʵ��
 * @author Administrator
 *
 */
public class BeforeAdviceImpl implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("==================before");
	}

}