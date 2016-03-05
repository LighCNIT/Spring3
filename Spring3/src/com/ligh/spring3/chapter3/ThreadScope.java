package com.ligh.spring3.chapter3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope {
    
    private final ThreadLocal<Map<String, Object>> THREAD_SCOPE = new ThreadLocal<Map<String, Object>>() {
      protected Map<String, Object> initialValue() {
          
          return new HashMap<String, Object>();
      }
    };
    
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        
        if(THREAD_SCOPE.get().containsKey(name)) {
           return THREAD_SCOPE.get().get(name); 
        }
        
        THREAD_SCOPE.get().put(name, objectFactory.getObject());
        return THREAD_SCOPE.get().get(name); 
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        
    }

    @Override
    public Object remove(String name) {
        return THREAD_SCOPE.get().remove(name);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
    
    
}
