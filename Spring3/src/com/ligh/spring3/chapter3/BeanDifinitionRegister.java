package com.ligh.spring3.chapter3;

import java.util.HashMap;
import java.util.Map;

public class BeanDifinitionRegister {
    
    
    private final Map<String, BeanDefinition> DEFINITIONS = new HashMap<String, BeanDefinition>();

    
    public void registerBeanDefinition(String beanName, BeanDefinition bd) {
        
        if(DEFINITIONS.containsKey(bd.getId())) {
            throw new RuntimeException("-------");
        }
        
        DEFINITIONS.put(bd.getId(), bd);
    }

    
    public BeanDefinition getBeanDefinition(String beanName) {
        return DEFINITIONS.get(beanName);
    }

    
    public boolean containsBeanDefinition(String beanName) {
        return DEFINITIONS.containsKey(beanName);
    }
    
    
}
