package com.ligh.spring3.chapter3;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ligh.spring3.chapter3.bean.DependentBean;



public class MoreDependencyInjectTest {
    
    @Test
    public void testDependOn() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/depends-on.xml");
      //一点要注册销毁回调，否则我们定义的销毁方法不执行  
        context.registerShutdownHook();
        DependentBean dependentBean = context.getBean("dependentBean", DependentBean.class);
        dependentBean.write("aaa");
    }
    
   
}

