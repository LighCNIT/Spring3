package com.ligh.spring3.chapter3.bean;

import java.io.IOException;

public class DependentBean {
    
    ResourceBean resourceBean;
    
    public void write(String ss) throws IOException {
        System.out.println("DependentBean:=========");
        resourceBean.getFos().write(ss.getBytes());
    }

    
    public void init() throws IOException {
        System.out.println("DependentBean:=========");
    }
    
    public void destroy() throws IOException {
        System.out.println("DependentBean:========");
        
        resourceBean.getFos().write("DependentBean:==============".getBytes());
    }
    
    public void setResourceBean(ResourceBean resourceBean) {
        this.resourceBean = resourceBean;
    }
}
