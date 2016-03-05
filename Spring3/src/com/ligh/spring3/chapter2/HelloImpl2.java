package com.ligh.spring3.chapter2;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

public class HelloImpl2 implements HelloApi {

    private String message;
    
    public HelloImpl2() {
        this.message = "Hello World!";
    }

    public HelloImpl2(String message) {
        this.message = message;
    }
    
    @Override
    public void sayHello() {
        System.out.println(message);
    }
    
}
