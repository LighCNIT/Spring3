package com.ligh.spring3.chapter6.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import com.ligh.spring3.chapter6.service.IIntroductionService;

/**
 * 1）使用@Aspect将POJO声明为切面；
   2）使用@Pointcut进行命名切入点声明，同时指定目标方法第一个参数类型必须是java.lang.String，
   对于其他匹配的方法但参数类型不一致的将也是不匹配的，通过argNames = "param"指定了将把该匹配的目标方法参数传递给通知同名的参数上；
   3）使用@Before进行前置通知声明，其中value用于定义切入点表达式或引用命名切入点；
   4）配置文件需要使用<aop:aspectj-autoproxy/>来开启注解风格的@AspectJ支持；
   5）需要将切面注册为Bean，如“aspect”Bean；
   6）测试代码完全一样。
 * @author Administrator
 *
 */
@Aspect
public class HelloWorldAspect2 {
	
	//定义切入点
	@Pointcut(value = "execution(* com.ligh.spring3..*.sayBefore(java.lang.String)) && args(param)", 
			argNames = "param")
	public void beforePointcut(String param) {

	}
	
	//定义通知
	@Before(value = "beforePointcut(param)", argNames = "param")  
	public void beforeAdvice(String param) {  
	    System.out.println("===========before advice param:" + param);  
	} 
	
	//后置返回通知
	@AfterReturning(  
		    value="execution(* com.ligh.spring3..*.sayBefore(..))",  
		    pointcut="execution(* com.ligh.spring3..*.sayAfterReturning(..))",  
		    argNames="retVal", returning="retVal")  
		public void afterReturningAdvice(Object retVal) {  
		    System.out.println("===========after returning advice retVal:" + retVal);  
		} 
	
	@AfterThrowing(  
		    value="execution(* com.ligh.spring3..*.sayAfterThrowing(..))",  
		    argNames="exception", throwing="exception")  
		public void afterThrowingAdvice(Exception exception) {  
		    System.out.println("===========after throwing advice exception:" + exception);  
		}  
	
    @After(value="execution(* com.ligh.spring3..*.sayAfterFinally(..))")
    public void afterFinallyAdvice() {
        System.out.println("===========after finally advice");
    }

    @Around(value="execution(* com.ligh.spring3..*.sayAround(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========around before advice");
        Object retVal = pjp.proceed(new Object[] {"replace"});
        System.out.println("===========around after advice");
        return retVal;
    }
    
    @DeclareParents(value="com.ligh.spring3..*.IHelloWorldService+", 
    		defaultImpl=com.ligh.spring3.chapter6.service.impl.IntroductiondService.class)
    private IIntroductionService introductionService;
    
    @Around(value="this(com.ligh.spring3.chapter6.service.IIntroductionService+)")
    public Object thisPointcut(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========around sssss advice");
        Object retVal = pjp.proceed();
        System.out.println("===========around ssss advice");
        return retVal;
    }
}
