package com.ligh.spring3.chapter2;

import com.ligh.spring3.chapter2.helloworld.HelloApi;

/**
 * ʹ��ʵ����������ʵ����Bean��ʹ�����ַ�ʽ����ָ��class���ԣ�
 * ��ʱ����ʹ��factory-bean������ָ������Bean��factory-method����ָ��ʵ����Bean�ķ�����
 * ����ʹ��ʵ��������������ָ��������������ʽ��ʹ�ù�������ʽһ��
 * @author Administrator
 *
 */
public class HelloApiInstanceFactory {
    
    public HelloApi newInstance(String message) {
        return new HelloImpl2(message);
    }
    
}
