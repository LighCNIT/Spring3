package com.ligh.spring3.chapter3.bean;

public class Printer {
    
    private int counter = 0;
    
    public void print(String type) {
        System.out.println(type + " printer: " + counter++);
    }
    
}
