package com.ligh.spring3.chapter5;

import org.springframework.beans.factory.annotation.Value;

public class SpELBean {
    @Value("#{'Hello' + world}")
    private String value; 
        
    public void setValue(String value) {
        this.value = value;
    }
        
    public String getValue() {
        return value;
    }
}
