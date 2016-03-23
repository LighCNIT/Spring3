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
 * 1��ʹ��@Aspect��POJO����Ϊ���棻
   2��ʹ��@Pointcut�������������������ͬʱָ��Ŀ�귽����һ���������ͱ�����java.lang.String��
   ��������ƥ��ķ������������Ͳ�һ�µĽ�Ҳ�ǲ�ƥ��ģ�ͨ��argNames = "param"ָ���˽��Ѹ�ƥ���Ŀ�귽���������ݸ�֪ͨͬ���Ĳ����ϣ�
   3��ʹ��@Before����ǰ��֪ͨ����������value���ڶ����������ʽ��������������㣻
   4�������ļ���Ҫʹ��<aop:aspectj-autoproxy/>������ע�����@AspectJ֧�֣�
   5����Ҫ������ע��ΪBean���硰aspect��Bean��
   6�����Դ�����ȫһ����
 * @author Administrator
 *
 */
@Aspect
public class HelloWorldAspect2 {
	
	//���������
	@Pointcut(value = "execution(* com.ligh.spring3..*.sayBefore(java.lang.String)) && args(param)", 
			argNames = "param")
	public void beforePointcut(String param) {

	}
	
	//����֪ͨ
	@Before(value = "beforePointcut(param)", argNames = "param")  
	public void beforeAdvice(String param) {  
	    System.out.println("===========before advice param:" + param);  
	} 
	
	//���÷���֪ͨ
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
