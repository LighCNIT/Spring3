package com.ligh.spring3.chapter3;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

//ʵ��������
public class DependencyInjectByInstanceFactory {
    
    public HelloApi newInstance(String message, int index) {
        return new HelloImpl3(message, index);
    }
    
}
