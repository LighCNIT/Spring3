package com.ligh.spring3.chapter3.bean;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class PrinterReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("Print Replacer");
        
        //method.invoke(obj, new String[]{"hehe"});
        return null;
    }
}
