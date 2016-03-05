package com.ligh.spring3.chapter3.bean;

public class CircleA {

    private CircleB circleB;
    
    public CircleA() {
    }
    
    public CircleA(CircleB circleB) {
        this.circleB = circleB;
    }
    
    public void setCircleB(CircleB circleB) {
        this.circleB = circleB;
    }
    
    public void a() {
        circleB.b();
    }
}
