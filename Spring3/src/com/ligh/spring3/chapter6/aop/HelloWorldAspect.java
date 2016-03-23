package com.ligh.spring3.chapter6.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * ��������֧����
 * �˴�HelloWorldAspect�಻������������ʵ��
 * ֻ�Ƕ�����֪ͨʵ�ֵ��࣬�ڴ����ǿ��԰�����������ȱ�������������档
 * @author Administrator
 *
 */
public class HelloWorldAspect {
	
	public void beforeAdvice(){
		System.out.println("==========before advice");
	}
	
	public void beforeAdvice(String param){
		System.out.println("---before advice---"+param);
	}
	
	public void afterReturningAdvice(Object retVal) {  
	    System.out.println("===========after returning advice retVal:" + retVal);  
	}  
	
	public void afterFinallyAdvice(){
		System.out.println("=========after advice");
	}
	
	public void afterThrowingAdvice(Exception exception) {  
		  System.out.println("===========after throwing advice exception:" + exception);  
		} 
	
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {  
	    System.out.println("===========around before advice");  
	    Object retVal = pjp.proceed(new Object[] {"replace"});  
	    System.out.println("===========around after advice");  
	    return retVal;  
	} 
}
