package com.ligh.spring3.chapter3;

import org.springframework.beans.factory.config.SingletonBeanRegistry;

public class DefaultBeanFactory {
   
    
    private BeanDifinitionRegister DEFINITIONS = new BeanDifinitionRegister();
    
    
    private final SingletonBeanRegistry SINGLETONS = new SingletonBeanRegister();
    
    public Object getBean(String beanName) {
        
        if(!DEFINITIONS.containsBeanDefinition(beanName)) {
            throw new RuntimeException("" + beanName + "");
        }
        
        BeanDefinition bd = DEFINITIONS.getBeanDefinition(beanName);
        
        if(bd.getScope() == BeanDefinition.SCOPE_SINGLETON) {
            
            if(SINGLETONS.containsSingleton(beanName)) {
                return SINGLETONS.getSingleton(beanName);
            }
            
            
            SINGLETONS.registerSingleton(beanName, createBean(bd));
            return SINGLETONS.getSingleton(beanName);
        }
        
        if(bd.getScope() == BeanDefinition.SCOPE_PROTOTYPE) {
            return createBean(bd);
        }
        
        throw new RuntimeException("");
    }
    
   
    public void registerBeanDefinition(BeanDefinition bd) {
        DEFINITIONS.registerBeanDefinition(bd.getId(), bd);
    }
    
    private Object createBean(BeanDefinition bd) {
       
        try {
            Class clazz = Class.forName(bd.getClazz());
            
            return clazz.getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("" + bd.getId() + "");
        } catch (Exception e) {
            throw new RuntimeException("" + bd.getId() + "");
        }
    }
}
