package com.ligh.spring3.chapter6.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 定义切面支持类
 * 此处HelloWorldAspect类不是真正的切面实现
 * 只是定义了通知实现的类，在此我们可以把它看作就是缺少了切入点的切面。
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
