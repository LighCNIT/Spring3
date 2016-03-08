package com.ligh.spring3.chapter3.bean;

public class Singleton {
   
    //1.私有化构造器
    private Singleton() {}
    
    //2.静态内部类，单例缓存者，惰性初始化，第一次使用初始化
    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    //3.全局访问点
    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
    
    //4.提供计数器验证一个ClassLoader一个实例
    private int counter=0;
}
