package com.ligh.spring3.chapter3;


import com.ligh.spring3.chapter2.helloworld.HelloApi;

public class HelloImpl4 implements HelloApi {

    private String message;
    private int index;
    
    
    public HelloImpl4() {
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public void sayHello() {
        System.out.println(index + ":" + message);
    }
    
}
