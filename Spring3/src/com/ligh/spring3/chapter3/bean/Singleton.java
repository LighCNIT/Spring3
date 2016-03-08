package com.ligh.spring3.chapter3.bean;

public class Singleton {
   
    //1.˽�л�������
    private Singleton() {}
    
    //2.��̬�ڲ��࣬���������ߣ����Գ�ʼ������һ��ʹ�ó�ʼ��
    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    //3.ȫ�ַ��ʵ�
    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
    
    //4.�ṩ��������֤һ��ClassLoaderһ��ʵ��
    private int counter=0;
}
