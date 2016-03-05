package com.ligh.spring3.chapter3.bean;

import java.util.List;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

public class HelloApiDecorator implements HelloApi {
    
    private HelloApi helloApi;
    
	private String message;
    
    public HelloApiDecorator() {
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public HelloApiDecorator(HelloApi helloApi) {
        this.helloApi = helloApi;
    }
    
    public void setHelloApi(HelloApi helloApi) {
        this.helloApi = helloApi;
    }
    
    @Override
    public void sayHello() {
        System.out.println("========装饰一下============");
        helloApi.sayHello();
        System.out.println("========装饰一下============");
    }
    
}
