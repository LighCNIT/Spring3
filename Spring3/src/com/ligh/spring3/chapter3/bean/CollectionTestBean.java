package com.ligh.spring3.chapter3.bean;

import java.util.Collection;

public class CollectionTestBean {
    
    private Collection<String> values;

    public void setValues(Collection<String> values) {
        this.values = values;
    }
    
    public Collection<String> getValues() {
        return values;
    }
}
